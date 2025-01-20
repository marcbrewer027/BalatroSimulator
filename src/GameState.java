import java.util.LinkedList;

import Cards.ConsumableCard;
import Cards.JokerCard;
import Cards.PlayingCard;

public class GameState {

    private LinkedList<JokerCard> jokers;
    private LinkedList<ConsumableCard> consumables;
    private LinkedList<Voucher> vouchers;
    private LinkedList<PlayingCard> deckCards;
    private int stake;
    private int jokerSpace;
    private int consumableSpace;
    private int bal;
    private int baseHands;
    private int baseDiscards;
    private int ante;
    private int round;
    private Voucher shopVoucher; // initialize this

    // --- STAKE GUIDE --- // TODO implement stakes
    // 1 - White - Base
    // 2 - Red - Small blind gives no reward money
    // 3 - Green - Required score scales faster for each ante
    // 4 - Black - Jokers may be eternal
    // 5 - Blue - -1 Discard
    // 6 - Purple - Required score scales faster for each ante
    // 7 - Orange - Jokers may be perishable
    // 8 - Gold - Jokers can be rental
    // -------------------


    public GameState(int aBal, int aBaseHands, int aBaseDiscards, int aJokerSpace, int aConsumableSpace, int aStake) {
        jokers = new LinkedList<JokerCard>();
        consumables = new LinkedList<ConsumableCard>();
        vouchers = new LinkedList<Voucher>();
        deckCards = new LinkedList<PlayingCard>();
        stake = aStake;
        jokerSpace = aJokerSpace;
        consumableSpace = aConsumableSpace;
        bal = aBal;
        baseHands = aBaseHands;
        baseDiscards = aBaseDiscards;
        ante = 1;
        round = 0;
    }

    public LinkedList<JokerCard> getJokers() {
        return jokers;
    }

    public boolean addJoker(JokerCard joker) {
        if (jokerSpace-1 < jokers.size()) {
            jokers.add(joker);
            return true;
        }
        return false;
    }

    public void removeJoker(int ind) {
        jokers.remove(ind);
    }

    public void removeJoker(JokerCard joker) {
        jokers.remove(joker); // keep an eye on using exact same object.
    }

    public void moveJoker(int oInd, int delta) {
        JokerCard joker = jokers.get(oInd);
        removeJoker(joker);
        jokers.add(oInd+delta, joker); // may cause problems with the removal of joker first, keep an eye on this
    }

    public JokerCard getJokerHead() {
        return jokers.getFirst();
    }

    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }

    public int getBaseHands() {
        return baseHands;
    }

    public void setBaseHands(int baseHands) {
        this.baseHands = baseHands;
    }

    public int getBaseDiscards() {
        return baseDiscards;
    }

    public void setBaseDiscards(int baseDiscards) {
        this.baseDiscards = baseDiscards;
    }

    public int getAnte() {
        return ante;
    }

    public void setAnte(int ante) {
        this.ante = ante;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public LinkedList<ConsumableCard> getConsumables() {
        return consumables;
    }

    public boolean addConsumable(ConsumableCard consumable) {
        if (consumableSpace-1 < consumables.size()) {
            consumables.add(consumable);
            return true;
        }
        return false;
    }

    public void removeConsumable(ConsumableCard consumable) {
        consumables.remove(consumable);
    }

    public void removeConsumable(int ind) {
        consumables.remove(ind);
    }

    public LinkedList<Voucher> getVouchers() {
        return vouchers;
    }

    public void addVoucher(Voucher voucher) {
        vouchers.add(voucher);
    }

    public LinkedList<PlayingCard> getDeckCards() {
        return deckCards;
    }

    public void addDeckCard(PlayingCard card) {
        deckCards.add(card);
    }

    public int getDeckSize() {
        return deckCards.size();
    }

    public int getJokerSpace() {
        return jokerSpace;
    }

    public void setJokerSpace(int jokerSpace) {
        this.jokerSpace = jokerSpace;
    }

    public int getConsumableSpace() {
        return consumableSpace;
    }

    public void setConsumableSpace(int consumableSpace) {
        this.consumableSpace = consumableSpace;
    }

    public int getStake() {
        return stake;
    }
}
