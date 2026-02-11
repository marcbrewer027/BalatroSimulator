package States;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Cards.ConsumableCard;
import Cards.JokerCard;
import Cards.PlayingCard;
import Misc.Utils;
import Misc.Voucher;
import Scoring.HandRank;

/**
 * Class representing a States.GameState object, used to keep track of the games state.
 */
public class GameState {

    // --- states related to the player ---

    private final LinkedList<JokerCard> jokers;
    private final LinkedList<ConsumableCard> consumables;
    private final LinkedList<Voucher> vouchers;
    private final LinkedList<PlayingCard> deckCards;
    private final int stake;
    private int jokerSpace;
    private int consumableSpace;
    private int handSize;
    private int bal;
    private int baseHands;
    private int baseDiscards;
    private int ante;
    private int round;
    private Voucher shopVoucher;
    private final Random rng;
    private final int initialBaseHands;
    private final int initialBaseDiscards;
    private final Map<HandRank, Integer> handLevels;

    // --- STAKE GUIDE ---
    // 1 - White - Base
    // 2 - Red - Small blind gives no reward money
    // 3 - Green - Required score scales faster for each ante
    // 4 - Black - Jokers may be eternal
    // 5 - Blue - -1 Discard
    // 6 - Purple - Required score scales faster for each ante
    // 7 - Orange - Jokers may be perishable
    // 8 - Gold - Jokers can be rental
    // -------------------

    // --- states unrelated to the player ---

    //private Set<JokerCard> jokerPool;

    /**
     * Constructor for States.GameState with default random seed.
     */
    public GameState(int aBal, int aBaseHands, int aBaseDiscards, int aJokerSpace, int aConsumableSpace, int aHandSize, int aStake) {
        this(aBal, aBaseHands, aBaseDiscards, aJokerSpace, aConsumableSpace, aHandSize, aStake, null);
    }

    /**
     * Constructor for States.GameState with optional seed for reproducible runs.
     * @param aBal Starting money balance.
     * @param aBaseHands Starting base hands per round.
     * @param aBaseDiscards Starting base discards per round.
     * @param aJokerSpace Starting joker slots.
     * @param aConsumableSpace Starting consumable slots.
     * @param aHandSize Starting hand size.
     * @param aStake Stake
     * @param seed Random seed; if null, uses system-dependent randomness.
     */
    public GameState(int aBal, int aBaseHands, int aBaseDiscards, int aJokerSpace, int aConsumableSpace, int aHandSize, int aStake, Long seed) {
        jokers = new LinkedList<JokerCard>();
        consumables = new LinkedList<ConsumableCard>();
        vouchers = new LinkedList<Voucher>();
        deckCards = Utils.basicDeck();
        stake = aStake;
        jokerSpace = aJokerSpace;
        consumableSpace = aConsumableSpace;
        handSize = aHandSize;
        bal = aBal;
        baseHands = aBaseHands;
        baseDiscards = aBaseDiscards;
        ante = 1;
        round = 1;
        rng = seed != null ? new Random(seed) : new Random();
        initialBaseHands = aBaseHands;
        // Stake 5 (Blue): -1 Discard
        initialBaseDiscards = (aStake == 5) ? Math.max(0, aBaseDiscards - 1) : aBaseDiscards;
        baseDiscards = initialBaseDiscards;
        handLevels = new EnumMap<>(HandRank.class);
        for (HandRank r : HandRank.values()) {
            handLevels.put(r, 1);
        }
    }

    /** Hand level for a given hand rank (used in scoring). Default 1. */
    public int getHandLevel(HandRank rank) {
        return handLevels.getOrDefault(rank, 1);
    }

    public void setHandLevel(HandRank rank, int level) {
        handLevels.put(rank, Math.max(1, level));
    }

    /**
     * Reset hands and discards to initial values for the start of a new blind.
     */
    public void resetHandsAndDiscards() {
        baseHands = initialBaseHands;
        baseDiscards = initialBaseDiscards;
    }

    /**
     * Reset deck to a full basic deck. Call when starting a new ante.
     */
    public void resetDeck() {
        deckCards.clear();
        deckCards.addAll(Utils.basicDeck());
    }

    /**
     * Returns the shared RNG for reproducible game simulation.
     * @return Random instance
     */
    public Random getRng() {
        return rng;
    }

    /**
     * Getter for jokers in joker slots.
     * @return LinkedList containing JokerCard objects (or nothing if empty).
     */
    public LinkedList<JokerCard> getJokers() {
        return jokers;
    }

    /**
     * Method for adding JokerCard object to joker slot. Ensures player has enough open slots first.
     * @param joker JokerCard wished to add.
     * @return boolean. True if successful, false otherwise (no open slots).
     */
    public boolean addJoker(JokerCard joker) {
        if (joker == null) return false;
        if (jokers.size() >= jokerSpace) return false;
        jokers.add(joker);
        return true;
    }

    /**
     * Remove joker at index.
     * @param ind index of joker to remove
     * @return the removed joker, or null if index out of range
     */
    public JokerCard removeJoker(int ind) {
        if (ind < 0 || ind >= jokers.size()) return null;
        return jokers.remove(ind);
    }

    /**
     * Remove the given joker from the list.
     * @param joker joker to remove
     * @return true if the joker was found and removed
     */
    public boolean removeJoker(JokerCard joker) {
        if (joker == null) return false;
        return jokers.remove(joker);
    }

    /**
     * Move joker at oInd by delta positions.
     * @param oInd current index
     * @param delta offset (can be negative)
     * @return true if move was valid
     */
    public boolean moveJoker(int oInd, int delta) {
        if (oInd < 0 || oInd >= jokers.size()) return false;
        int newInd = oInd + delta;
        if (newInd < 0 || newInd >= jokers.size()) return false;
        JokerCard joker = jokers.remove(oInd);
        jokers.add(newInd, joker);
        return true;
    }

    /**
     * Get the first joker in the list.
     * @return the first joker, or null if list is empty
     */
    public JokerCard getJokerHead() {
        return jokers.isEmpty() ? null : jokers.getFirst();
    }

    /**
     * Getter for balance.
     * @return int
     */
    public int getBal() {
        return bal;
    }

    /**
     * Setter for balance.
     * @param bal integer value
     */
    public void setBal(int bal) {
        this.bal = bal;
    }

    /**
     * Getter for baseHands.
     * @return int
     */
    public int getBaseHands() {
        return baseHands;
    }

    /**
     * Setter for baseHands.
     * @param baseHands integer value
     */
    public void setBaseHands(int baseHands) {
        this.baseHands = baseHands;
    }

    /**
     * Getter for baseDiscards.
     * @return int
     */
    public int getBaseDiscards() {
        return baseDiscards;
    }

    /**
     * Setter for BaseDiscards.
     * @param baseDiscards integer value
     */
    public void setBaseDiscards(int baseDiscards) {
        this.baseDiscards = baseDiscards;
    }

    /**
     * Getter for ante.
     * @return int
     */
    public int getAnte() {
        return ante;
    }

    /**
     * Setter for ante.
     * @param ante integer value
     */
    public void setAnte(int ante) {
        this.ante = ante;
    }

    /**
     * Getter for round.
     * @return int
     */
    public int getRound() {
        return round;
    }

    /**
     * Setter of round.
     * @param round integer value
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Getter for consumables.
     * @return LinkedList
     */
    public LinkedList<ConsumableCard> getConsumables() {
        return consumables;
    }

    /**
     * Method for adding consumable to consumable list. Ensures the player has enough space.
     * @param consumable ConsumableCard to be added.
     * @return boolean. True if successful, false otherwise.
     */
    public boolean addConsumable(ConsumableCard consumable) {
        if (consumable == null) return false;
        if (consumables.size() >= consumableSpace) return false;
        consumables.add(consumable);
        return true;
    }

    /**
     * Remove the given consumable from the list.
     * @return true if the consumable was found and removed
     */
    public boolean removeConsumable(ConsumableCard consumable) {
        if (consumable == null) return false;
        return consumables.remove(consumable);
    }

    /**
     * Remove consumable at index.
     * @param ind index of consumable
     * @return the removed consumable, or null if index out of range
     */
    public ConsumableCard removeConsumable(int ind) {
        if (ind < 0 || ind >= consumables.size()) return null;
        return consumables.remove(ind);
    }

    /**
     * Getter for vouchers.
     * @return LinkedList
     */
    public LinkedList<Voucher> getVouchers() {
        return vouchers;
    }

    /**
     * Adds a voucher to owned vouchers.
     * @param voucher Misc.Voucher to be added.
     */
    public void addVoucher(Voucher voucher) {
        vouchers.add(voucher);
    }

    /**
     * Getter for deckCards.
     * @return LinkedList
     */
    public LinkedList<PlayingCard> getDeckCards() {
        return deckCards;
    }

    /**
     * Adds PlayingCard object to deckCards.
     * @param card PlayingCard to be added
     */
    public void addDeckCard(PlayingCard card) {
        deckCards.add(card);
    }

    /**
     * Getter for size of deckCards.
     * @return int
     */
    public int getDeckSize() {
        return deckCards.size();
    }

    /**
     * Getter for jokerSpace.
     * @return int
     */
    public int getJokerSpace() {
        return jokerSpace;
    }

    /**
     * Setter for jokerSpace.
     * @param jokerSpace Integer to set jokerSpace to.
     */
    public void setJokerSpace(int jokerSpace) {
        this.jokerSpace = jokerSpace;
    }

    /**
     * Getter for consumableSpace.
     * @return int
     */
    public int getConsumableSpace() {
        return consumableSpace;
    }

    /**
     * Setter for consumableSpace.
     * @param consumableSpace Integer to set consumableSpace to.
     */
    public void setConsumableSpace(int consumableSpace) {
        this.consumableSpace = consumableSpace;
    }

    /**
     * Getter for stake.
     * @return int
     */
    public int getStake() {
        return stake;
    }

    public String toString(){
        return String.format("""
                It is currently ante %s, round %s. \r
                You currently start each round with %s hands and %s discards. \r
                You have $%s, %s Jokers, and %s available joker slots. \r
                You have %s available consumable slots and %s consumables. \r
                you have %s cards in your deck and %s vouchers.
                """,ante,round,baseHands,baseDiscards,bal,jokers.size(),jokerSpace,consumableSpace,consumables.size(),getDeckSize(),vouchers.size());
    }

    /**
     * Getter for handSize.
     * @return int
     */
    public int getHandSize() {
        return handSize;
    }

    /**
     * Setter for handSize.
     * @param handSize Integer to set handSize to.
     */
    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }
}
