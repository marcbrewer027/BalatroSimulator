package Misc;

import Cards.JokerCard;
import Cards.PlayingCard;
import States.Ante;
import States.GameState;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 * Utility class for various game functions.
 */
public class Utils {

    /**
     * Static method for creating States.Ante.
     * @param state States.GameState object to create ante for.
     * @return States.Ante object newly created.
     */
    public static Ante createAnte(GameState state) {

        int ante = state.getAnte();
        int stake = state.getStake();
        int modifier;

        if (stake < 3) {
            modifier = 0;
        } else if (stake < 6) {
            modifier = 1;
        } else {
            modifier = 2;
        }

        int[][] earlyBases = {
                {100, 100, 100}, //0
                {300, 300, 300}, //1
                {800, 900, 1000}, //2
                {2000, 2600, 3200}, //3
                {5000, 8000, 9000}, //4
                {11000, 20000, 25000}, //5
                {20000, 36000, 60000}, //6
                {35000, 60000, 110000}, //7
                {50000, 100000, 200000}, //8
        };

        if (ante <= 8) {
            return new Ante(earlyBases[ante][modifier], state);
        } else {
            return new Ante(100, state); //TODO implement endless scaling - https://www.desmos.com/calculator/fsvcr75cdx
        }

    }

    /**
     * Initializes the set of jokers for the joker pool. Unfinished and unused as of Jan 21, 2025.
     * @return Set of JokerCard objects.
     */
    public static Set<JokerCard> initializeJokers() {
        Set<JokerCard> toRet = new HashSet<>();

        // Joker
        toRet.add(new JokerCard(null, false, false,false, new JokerCard.TriggerType[]{JokerCard.TriggerType.Indep}, (o) -> {System.out.println("added 5 to mult");}));


        return toRet;
    }

    /**
     * Pulls a hand from deck via GameState.
     * @param state GameState object to find deck.
     * @return LinkedList object of PlayingCard objects
     */
    public static LinkedList<PlayingCard> pullHand(GameState state) {

        Random rand = new Random(); // TODO accept seeds

        LinkedList<PlayingCard> toRet = new LinkedList<>();

        LinkedList<PlayingCard> tempDeck = (LinkedList<PlayingCard>) state.getDeckCards().clone(); // May need to move this to GameState

        for (int i = state.getHandSize(); i > 0; i--) {

            int ind = rand.nextInt(tempDeck.size());

            toRet.add(tempDeck.get(ind));
            tempDeck.remove(ind);

        }

        return toRet;
    }

    /**
     * Creates a basic deck of 52 cards.
     * @return LinkedList object of PlayingCard objects
     */
    public static LinkedList<PlayingCard> basicDeck() {
        LinkedList<PlayingCard> toRet = new LinkedList<PlayingCard>();
        PlayingCard.Suit[] suits = {PlayingCard.Suit.Club, PlayingCard.Suit.Diamond, PlayingCard.Suit.Heart, PlayingCard.Suit.Spade};

        for (PlayingCard.Suit suit : suits) {
            for (int i = 2; i < 15; i++) {
                toRet.add(new PlayingCard(null,null,null, i, suit) );
            }
        }

        return toRet;
    }

}
