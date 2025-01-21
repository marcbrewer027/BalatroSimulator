package States;

import Cards.PlayingCard;
import Misc.Utils;

import java.util.LinkedList;

/**
 * Class representing Blinds. Unfinished as of Jan 21, 2025.
 */
public class Blind {

    private final double scoreMin;
    private double currentScore;
    LinkedList<PlayingCard> held;
    LinkedList<PlayingCard> play;

    /**
     * Constructor for Blind object.
     * @param state GameState object, to get hand info, decks, etc
     * @param aScore Double value, for the minimum score
     */
    public Blind(GameState state, double aScore) {

        scoreMin = aScore;
        currentScore = 0;
        held = Utils.pullHand(state);
        play = new LinkedList<PlayingCard>();

    }

    /**
     * Getter for scoreMin.
     * @return double
     */
    public double getScoreMin() {
        return scoreMin;
    }

    @Override
    public String toString() {
        String start = String.format("Score to beat is %s, you currently have %s.\nCards held in hand are as follows:\n", scoreMin, currentScore); // TODO Math.floor the scores
        StringBuilder end = new StringBuilder();

        for (PlayingCard card : held) {
            end.append(card);
        }

        return start.concat(String.valueOf(end));
    }
}
