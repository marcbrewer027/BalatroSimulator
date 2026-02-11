package Scoring;

import Cards.JokerCard;
import Cards.PlayingCard;
import States.GameState;

import java.util.LinkedList;
import java.util.List;

/**
 * Computes hand score from played cards, hand level, and joker effects.
 */
public final class Scorer {

    private Scorer() {}

    /**
     * Level multiplier for hand rank (Balatro: level 1 = 1x, level 2 = 2x mult, etc.).
     * Chips from hand rank scale with level in some games; here we apply level to mult.
     */
    private static int levelMult(int level) {
        return Math.max(1, level);
    }

    /**
     * Compute total chips from played cards (chip values + hand base chips).
     */
    public static int computeChips(List<PlayingCard> played, HandRank rank) {
        int cardChips = 0;
        for (PlayingCard c : played) {
            cardChips += c.getChipValue();
        }
        return rank.getBaseChips() + cardChips;
    }

    /**
     * Compute base mult from hand rank and level (before jokers).
     */
    public static int computeBaseMult(HandRank rank, int handLevel) {
        return rank.getBaseMult() * levelMult(handLevel);
    }

    /**
     * Score a played hand: evaluate rank, build chips/mult from state hand level, run joker triggers, return final score.
     *
     * @param state  current game state (for jokers and hand levels)
     * @param played cards played this hand (1-5)
     * @return total score (chips * mult) after joker effects
     */
    public static int scoreHand(GameState state, List<PlayingCard> played) {
        if (played == null || played.isEmpty()) return 0;

        HandRank rank = HandEvaluator.evaluate(played);
        int handLevel = state.getHandLevel(rank);
        int chips = computeChips(played, rank);
        int mult = computeBaseMult(rank, handLevel);

        ScoreContext ctx = new ScoreContext(chips, mult, rank, handLevel, List.copyOf(played));

        for (JokerCard joker : state.getJokers()) {
            for (JokerCard.TriggerType t : joker.getTriggerTypes()) {
                if (t == JokerCard.TriggerType.Played || t == JokerCard.TriggerType.Scored) {
                    if (joker.getFunction() != null) {
                        joker.getFunction().accept(ctx);
                    }
                }
            }
        }

        return ctx.getScore();
    }
}
