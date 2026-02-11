import Cards.PlayingCard;
import Misc.Utils;
import Packs.Pack;
import Packs.StandardPack;
import Packs.TarotPack;
import Packs.CelestialPack;
import Scoring.HandEvaluator;
import Scoring.HandRank;
import States.Ante;
import States.Blind;
import States.GameState;
import States.Shop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int REROLL_BASE_COST = 5;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Standard Balatro start: $4, 3 hands, 3 discards, 5 joker slots, 2 consumable, hand size 8, stake 1
        GameState state = new GameState(4, 3, 3, 5, 2, 8, 1);
        int highestAnte = state.getAnte();
        int highestRound = state.getRound();
        int highestHandScore = 0;

        Ante currentAnte = Utils.createAnte(state);
        Blind currentBlind = currentAnte.startBlind();

        while (true) {
            // --- Blind phase ---
            while (true) {
                printBlindState(currentBlind, currentAnte);

                if (currentBlind.isBeat()) {
                    System.out.println(">>> Blind beaten!");
                    break;
                }

                if (isBlindLost(state, currentBlind)) {
                    printLoss(state, currentAnte, highestAnte, highestRound, highestHandScore);
                    in.close();
                    return;
                }

                String line = prompt(in, "Play hand (e.g. play 0 1 2), discard (e.g. discard 3 4 or discard all), or 'status': ");
                if (line == null || line.isBlank()) continue;

                String[] parts = line.trim().split("\\s+");
                String cmd = parts[0].toLowerCase();

                if (cmd.equals("status")) {
                    printFullState(state, currentAnte, currentBlind);
                    continue;
                }

                if (cmd.equals("play")) {
                    List<Integer> indices = parseIndices(parts, 1, currentBlind.getHeld().size());
                    if (indices == null) {
                        System.out.println("Invalid indices. Use 0-based positions (e.g. play 0 1 2).");
                        continue;
                    }
                    List<PlayingCard> toPlay = getCardsByIndices(currentBlind.getHeld(), indices);
                    if (toPlay.size() > 5) {
                        System.out.println("Maximum 5 cards per hand.");
                        continue;
                    }
                    HandRank rank = HandEvaluator.evaluate(toPlay);
                    if (rank == HandRank.HIGH_CARD && toPlay.size() > 1) {
                        System.out.println("Not a valid scoring hand (need at least a pair).");
                        continue;
                    }
                    int added = currentBlind.playHand(toPlay);
                    if (added == 0) {
                        System.out.println("Could not play that hand (check hands left and cards).");
                        continue;
                    }
                    highestHandScore = Math.max(highestHandScore, added);
                    System.out.println("Scored " + added + " (hand: " + rank + "). Total: " + (long) currentBlind.getCurrentScore());
                    continue;
                }

                if (cmd.equals("discard")) {
                    if (parts.length > 1 && parts[1].equalsIgnoreCase("all")) {
                        if (!currentBlind.discardAll()) {
                            System.out.println("No discards left or invalid.");
                        } else {
                            System.out.println("Discarded all. New hand drawn.");
                        }
                    } else {
                        List<Integer> indices = parseIndices(parts, 1, currentBlind.getHeld().size());
                        if (indices == null) {
                            System.out.println("Invalid indices. Use 0-based positions or 'discard all'.");
                            continue;
                        }
                        List<PlayingCard> toDiscard = getCardsByIndices(currentBlind.getHeld(), indices);
                        if (!currentBlind.discard(toDiscard)) {
                            System.out.println("Could not discard (check discards left and cards).");
                        } else {
                            System.out.println("Discarded. New hand drawn.");
                        }
                    }
                    continue;
                }

                System.out.println("Unknown command. Use: play <indices>, discard <indices>, discard all, or status.");
            }

            // Blind beaten: payout then next stage or shop (Stake 2 Red: small blind gives no $ for hands)
            int payout = computePayout(state, currentAnte.getStage());
            state.setBal(state.getBal() + payout);
            System.out.println("Payout: $" + payout + " (remaining hands + interest). Balance: $" + state.getBal());

            if (currentAnte.nextStage()) {
                currentBlind = currentAnte.startBlind();
                continue;
            }

            // Ante complete (boss beaten) -> shop
            currentAnte.closeAndAdvance();
            highestAnte = Math.max(highestAnte, state.getAnte());
            highestRound = Math.max(highestRound, state.getRound());

            Shop shop = new Shop(state);
            int rerollCount = 0;
            populateShop(shop);

            while (true) {
                printShop(shop, state, rerollCount);
                String action = prompt(in, "Buy <slot>, reroll, or continue: ");
                if (action == null) continue;
                action = action.trim().toLowerCase();

                if (action.startsWith("buy ")) {
                    String num = action.substring(4).trim();
                    try {
                        int slot = Integer.parseInt(num);
                        if (shop.buy(slot)) {
                            System.out.println("Bought slot " + slot + ".");
                        } else {
                            System.out.println("Could not buy (invalid slot or not enough money).");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Usage: buy <slot number>");
                    }
                    continue;
                }

                if (action.equals("reroll")) {
                    int cost = REROLL_BASE_COST + rerollCount;
                    if (shop.reroll(cost)) {
                        rerollCount++;
                        populateShop(shop);
                        System.out.println("Rerolled for $" + cost + ". New items in shop.");
                    } else {
                        System.out.println("Not enough money (need $" + cost + ") or invalid.");
                    }
                    continue;
                }

                if (action.equals("continue")) {
                    break;
                }

                System.out.println("Unknown. Use: buy <slot>, reroll, or continue.");
            }

            // After shop: next ante, then skip or play first blind
            currentAnte = Utils.createAnte(state);
            int skipCost = currentAnte.getSkipCostDollars(0);
            String skip = prompt(in, "Skip small blind for $" + skipCost + "? (y/n): ");
            if (skip != null && skip.trim().equalsIgnoreCase("y")) {
                if (state.getBal() >= skipCost) {
                    state.setBal(state.getBal() - skipCost);
                    currentAnte.nextStage();
                    currentBlind = currentAnte.startBlind();
                    System.out.println("Skipped. Starting big blind.");
                } else {
                    System.out.println("Not enough money. Playing small blind.");
                    currentBlind = currentAnte.startBlind();
                }
            } else {
                currentBlind = currentAnte.startBlind();
            }
        }
    }

    private static boolean isBlindLost(GameState state, Blind blind) {
        if (blind.isBeat()) return false;
        boolean noHandsLeft = state.getBaseHands() <= 0;
        boolean noCards = blind.getHeld().isEmpty() && state.getDeckCards().isEmpty();
        return noHandsLeft || noCards;
    }

    /** Payout: $1 per remaining hand (except Stake 2 Red: $0 for small blind) + interest ($1 per $5, max $5). */
    private static int computePayout(GameState state, int stageJustBeat) {
        int handsReward = state.getBaseHands() * 1;
        if (state.getStake() == 2 && stageJustBeat == 0) handsReward = 0; // Red stake: no $ for small blind
        int interest = Math.min(5, state.getBal() / 5);
        return handsReward + interest;
    }

    private static void populateShop(Shop shop) {
        if (shop.getSlotCount() > 0) return;
        shop.addPack(new StandardPack(Pack.Size.Normal), 5);
        shop.addPack(new TarotPack(Pack.Size.Normal), 4);
        shop.addPack(new CelestialPack(Pack.Size.Normal), 5);
    }

    private static void printBlindState(Blind blind, Ante ante) {
        System.out.println("--- Blind (Stage " + (ante.getStage() + 1) + ") ---");
        System.out.println("Score to beat: " + (long) blind.getScoreMin() + "  Current: " + (long) blind.getCurrentScore());
        System.out.println("Hands left: " + blind.getState().getBaseHands() + "  Discards left: " + blind.getState().getBaseDiscards());
        System.out.println("Deck size: " + blind.getState().getDeckSize());
        System.out.println("Held cards (index: card):");
        LinkedList<PlayingCard> held = blind.getHeld();
        for (int i = 0; i < held.size(); i++) {
            System.out.println("  " + i + ": " + held.get(i));
        }
    }

    private static void printFullState(GameState state, Ante ante, Blind blind) {
        System.out.println("=== Full state ===");
        System.out.println(state);
        System.out.println(ante);
        System.out.println("Current blind: " + blind);
        System.out.println("Held: " + blind.getHeld());
        System.out.println("Deck size: " + state.getDeckSize());
        System.out.println("Jokers: " + state.getJokers().size());
        System.out.println("Consumables: " + state.getConsumables().size());
    }

    private static void printShop(Shop shop, GameState state, int rerollCount) {
        System.out.println("--- Shop ---");
        System.out.println("Balance: $" + state.getBal());
        System.out.println("Reroll cost: $" + (REROLL_BASE_COST + rerollCount) + " (base 5 + " + rerollCount + " rerolls)");
        for (int i = 0; i < shop.getSlotCount(); i++) {
            int price = shop.getAdjustedPrice(i);
            System.out.println("  " + i + ": " + shop.getPack(i) + " - $" + price);
        }
    }

    private static void printLoss(GameState state, Ante ante, int highestAnte, int highestRound, int highestHandScore) {
        System.out.println("\n=== RUN OVER ===");
        System.out.println("You did not beat the blind.");
        System.out.println("Target score: " + (long) ante.getScore() + "  Your score: (see blind state above)");
        System.out.println("--- Stats ---");
        System.out.println("Highest ante reached: " + highestAnte);
        System.out.println("Highest round reached: " + highestRound);
        System.out.println("Highest single hand score: " + highestHandScore);
        System.out.println("Final balance: $" + state.getBal());
        System.out.println("Jokers owned: " + state.getJokers().size());
        System.out.println("Consumables: " + state.getConsumables().size());
    }

    private static String prompt(Scanner in, String message) {
        System.out.print(message);
        if (!in.hasNextLine()) return null;
        return in.nextLine();
    }

    private static List<Integer> parseIndices(String[] parts, int from, int maxSize) {
        List<Integer> indices = new ArrayList<>();
        for (int i = from; i < parts.length; i++) {
            try {
                int idx = Integer.parseInt(parts[i]);
                if (idx < 0 || idx >= maxSize) return null;
                indices.add(idx);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return indices;
    }

    private static List<PlayingCard> getCardsByIndices(LinkedList<PlayingCard> held, List<Integer> indices) {
        List<PlayingCard> out = new ArrayList<>();
        for (int idx : indices) {
            if (idx >= 0 && idx < held.size()) {
                out.add(held.get(idx));
            }
        }
        return out;
    }
}
