package States;

import Cards.PlayingCard;
import Misc.Utils;
import Scoring.Scorer;

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing Blinds. Tracks held cards, played cards, and score during a blind.
 */
public class Blind {

    private final GameState state;
    private final double scoreMin;
    private double currentScore;
    private LinkedList<PlayingCard> held;
    private LinkedList<PlayingCard> play;

    /**
     * Constructor for Blind object. Draws initial hand from deck (removes cards from state's deck).
     *
     * @param state  GameState object
     * @param aScore Minimum score to beat this blind
     */
    public Blind(GameState state, double aScore) {
        this.state = state;
        this.scoreMin = aScore;
        this.currentScore = 0;
        this.held = Utils.pullHand(state);
        this.play = new LinkedList<>();
    }

    /**
     * Play the given cards as a hand: move them from held to play, score them, add to current score,
     * consume one hand, then refill held from deck if hands remain.
     *
     * @param cardsToPlay cards from held to play (must be a subset of held)
     * @return score added this hand, or 0 if invalid
     */
    public int playHand(List<PlayingCard> cardsToPlay) {
        if (cardsToPlay == null || cardsToPlay.isEmpty() || state.getBaseHands() <= 0) return 0;
        for (PlayingCard c : cardsToPlay) {
            if (!held.contains(c)) return 0;
        }
        for (PlayingCard c : cardsToPlay) {
            held.remove(c);
            play.add(c);
        }
        int score = Scorer.scoreHand(state, play);
        currentScore += score;
        play.clear();
        state.setBaseHands(state.getBaseHands() - 1);
        if (state.getBaseHands() > 0 && !state.getDeckCards().isEmpty()) {
            held = Utils.pullHand(state);
        }
        return score;
    }

    /**
     * Discard the given cards (no score). Consumes one discard and refills held from deck if possible.
     *
     * @param cardsToDiscard cards from held to discard
     * @return true if discard was valid
     */
    public boolean discard(List<PlayingCard> cardsToDiscard) {
        if (cardsToDiscard == null || state.getBaseDiscards() <= 0) return false;
        for (PlayingCard c : cardsToDiscard) {
            if (!held.contains(c)) return false;
        }
        for (PlayingCard c : cardsToDiscard) {
            held.remove(c);
        }
        state.setBaseDiscards(state.getBaseDiscards() - 1);
        if (!state.getDeckCards().isEmpty()) {
            held = Utils.pullHand(state);
        }
        return true;
    }

    /**
     * Discard all held cards (uses one discard) and refill from deck.
     */
    public boolean discardAll() {
        if (state.getBaseDiscards() <= 0) return false;
        held.clear();
        state.setBaseDiscards(state.getBaseDiscards() - 1);
        if (!state.getDeckCards().isEmpty()) {
            held = Utils.pullHand(state);
        }
        return true;
    }

    public double getScoreMin() {
        return scoreMin;
    }

    public double getCurrentScore() {
        return currentScore;
    }

    public LinkedList<PlayingCard> getHeld() {
        return held;
    }

    public LinkedList<PlayingCard> getPlay() {
        return play;
    }

    public GameState getState() {
        return state;
    }

    /** True if current score meets or exceeds the blind target. */
    public boolean isBeat() {
        return currentScore >= scoreMin;
    }

    @Override
    public String toString() {
        long scoreMinF = (long) Math.floor(scoreMin);
        long currentF = (long) Math.floor(currentScore);
        String start = String.format("Score to beat is %s, you currently have %s.\nCards held in hand are as follows:\n", scoreMinF, currentF);
        StringBuilder end = new StringBuilder();
        for (PlayingCard card : held) {
            end.append(card);
        }
        return start.concat(end.toString());
    }
}
