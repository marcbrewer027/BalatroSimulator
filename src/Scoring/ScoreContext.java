package Scoring;

import Cards.PlayingCard;

import java.util.List;

/**
 * Mutable context for scoring a single hand. Jokers with Played/Scored triggers
 * receive this and can add chips or mult.
 */
public class ScoreContext {

    private int chips;
    private int mult;
    private final HandRank handRank;
    private final int handLevel;
    private final List<PlayingCard> playedCards;

    public ScoreContext(int chips, int mult, HandRank handRank, int handLevel, List<PlayingCard> playedCards) {
        this.chips = chips;
        this.mult = mult;
        this.handRank = handRank;
        this.handLevel = handLevel;
        this.playedCards = playedCards;
    }

    public void addChips(int delta) {
        this.chips += delta;
    }

    public void addMult(int delta) {
        this.mult += delta;
    }

    public int getChips() {
        return chips;
    }

    public int getMult() {
        return mult;
    }

    public HandRank getHandRank() {
        return handRank;
    }

    public int getHandLevel() {
        return handLevel;
    }

    public List<PlayingCard> getPlayedCards() {
        return playedCards;
    }

    /**
     * Final score: chips * mult (Balatro formula).
     */
    public int getScore() {
        return chips * mult;
    }
}
