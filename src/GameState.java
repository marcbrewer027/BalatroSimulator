import java.util.LinkedList;

import Cards.ConsumableCard;
import Cards.JokerCard;
import Cards.PlayingCard;

/**
 * Class representing a GameState object, used to keep track of the games state.
 */
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

    /**
     * Constructor for GameState.
     * @param aBal Starting money balance.
     * @param aBaseHands Starting base hands per round.
     * @param aBaseDiscards Starting base discards per round.
     * @param aJokerSpace Starting joker slots.
     * @param aConsumableSpace Starting consumable slots.
     * @param aStake Stake
     */
    public GameState(int aBal, int aBaseHands, int aBaseDiscards, int aJokerSpace, int aConsumableSpace, int aStake) {
        // TODO add decks, and their functionality.
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
        // TODO ensure negative jokers can be added.
        if (jokerSpace-1 < jokers.size()) {
            jokers.add(joker);
            return true;
        }
        return false;
    }

    /**
     * Method for removing Joker at set index.
     * @param ind Index of joker to be removed
     * @return JokerCard
     */
    public JokerCard removeJoker(int ind) {
        // TODO error handling for index out of range
        return jokers.remove(ind);
    }

    /**
     * Method for removing specific joker object.
     * @param joker JokerCard object to be removed.
     */
    public void removeJoker(JokerCard joker) {
        // TODO error handling for object not found.
        jokers.remove(joker); // keep an eye on using exact same object.
    }

    /**
     * Method for moving joker within LinkedList object.
     * @param oInd Index of joker to be moved.
     * @param delta Delta of where it will be moved to (can be negative).
     */
    public void moveJoker(int oInd, int delta) {
        // TODO add error handling for index out of range
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

    /**
     * Method for adding consumable to consumable list. Ensures the player has enough space.
     * @param consumable ConsumableCard to be added.
     * @return boolean. True if successful, false otherwise.
     */
    public boolean addConsumable(ConsumableCard consumable) {
        // TODO make sure Perkeo works correctly
        if (consumableSpace-1 < consumables.size()) {
            consumables.add(consumable);
            return true;
        }
        return false;
    }

    /**
     * Method for removing consumable from consumable list.
     * @param consumable ConsumableCard to be removed.
     */
    public void removeConsumable(ConsumableCard consumable) {
        // TODO add error handling for object not found.
        consumables.remove(consumable);
    }

    /**
     * Method for removing consumable from consumable list.
     * @param ind index of consumable
     */
    public void removeConsumable(int ind) {
        // TODO add error handling for index of range
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
