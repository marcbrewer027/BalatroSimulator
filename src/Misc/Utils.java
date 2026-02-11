package Misc;

import Cards.JokerCard;
import Cards.PlayingCard;
import Misc.Tag;
import Scoring.ScoreContext;
import States.Ante;
import States.BossBlind;
import States.GameState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Utility class for various game functions.
 */
public class Utils {

    /**
     * Create an Ante for the current game state. Uses stake for score modifier and optional endless scaling for ante > 8.
     * @param state current game state
     * @return new Ante
     */
    public static Ante createAnte(GameState state) {
        return createAnte(state, null);
    }

    /**
     * Create an Ante with optional boss blind (if null, NONE is used).
     */
    public static Ante createAnte(GameState state, BossBlind boss) {
        int ante = state.getAnte();
        int stake = state.getStake();
        int modifier = stake < 3 ? 0 : (stake < 6 ? 1 : 2);

        int[][] earlyBases = {
                {100, 100, 100},
                {300, 300, 300},
                {800, 900, 1000},
                {2000, 2600, 3200},
                {5000, 8000, 9000},
                {11000, 20000, 25000},
                {20000, 36000, 60000},
                {35000, 60000, 110000},
                {50000, 100000, 200000},
        };

        int base;
        if (ante <= 8) {
            base = earlyBases[ante][modifier];
        } else {
            // Endless scaling: base grows from ante 8 value by ~1.5 per ante
            int baseAnte8 = earlyBases[8][modifier];
            base = (int) (baseAnte8 * Math.pow(1.5, ante - 8));
        }

        BossBlind b = boss != null ? boss : BossBlind.NONE;
        return new Ante(base, state, b, Tag.SKIP_SMALL, Tag.SKIP_BIG);
    }

    /**
     * Joker pool for pack selection. Used by StandardPack/BuffoonPack.
     */
    public static Set<JokerCard> initializeJokers() {
        Set<JokerCard> toRet = new HashSet<>();
        toRet.add(new JokerCard(null, false, false, false,
                new JokerCard.TriggerType[]{JokerCard.TriggerType.Played},
                o -> {
                    if (o instanceof Scoring.ScoreContext ctx) ctx.addMult(4);
                }));
        return toRet;
    }

    /**
     * Returns joker pool as a list for random picking (with replacement).
     */
    public static List<JokerCard> getJokerPoolList() {
        return new ArrayList<>(initializeJokers());
    }

    /**
     * Pulls a hand from deck via GameState.
     * @param state GameState object to find deck.
     * @return LinkedList object of PlayingCard objects
     */
    public static LinkedList<PlayingCard> pullHand(GameState state) {
        Random rand = state.getRng();
        LinkedList<PlayingCard> toRet = new LinkedList<>();
        LinkedList<PlayingCard> deck = state.getDeckCards();
        int toDraw = Math.min(state.getHandSize(), deck.size());

        for (int i = 0; i < toDraw; i++) {
            int ind = rand.nextInt(deck.size());
            toRet.add(deck.remove(ind));
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
