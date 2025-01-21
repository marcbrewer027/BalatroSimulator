package States;

import java.util.LinkedList;
import java.util.Set;

import Cards.ConsumableCard;
import Cards.JokerCard;
import Cards.PlayingCard;
import Misc.Utils;
import Misc.Voucher;

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

    // --- states unrelated to the player ---

    //private Set<JokerCard> jokerPool;

    /**
     * Constructor for States.GameState.
     * @param aBal Starting money balance.
     * @param aBaseHands Starting base hands per round.
     * @param aBaseDiscards Starting base discards per round.
     * @param aJokerSpace Starting joker slots.
     * @param aConsumableSpace Starting consumable slots.
     * @param aHandSize Starting hand size.
     * @param aStake Stake
     */
    public GameState(int aBal, int aBaseHands, int aBaseDiscards, int aJokerSpace, int aConsumableSpace, int aHandSize, int aStake) {

        // TODO add decks, and their functionality.
        jokers = new LinkedList<JokerCard>();
        consumables = new LinkedList<ConsumableCard>();
        vouchers = new LinkedList<Voucher>();
        deckCards = Utils.basicDeck(); // TODO add initialization for default deck
        stake = aStake;
        jokerSpace = aJokerSpace;
        consumableSpace = aConsumableSpace;
        handSize = aHandSize;
        bal = aBal;
        baseHands = aBaseHands;
        baseDiscards = aBaseDiscards;
        ante = 1;
        round = 1;

        //jokerPool = Utils.initializeJokers();

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

    /**
     * Method for getting the head of the jokers LinkedList.
     * @return JokerCard, head of the object.
     */
    public JokerCard getJokerHead() {
        return jokers.getFirst();
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
