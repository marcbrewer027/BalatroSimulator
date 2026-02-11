package Scoring;

/**
 * Poker hand ranks as used in Balatro (low to high).
 * High Card is not a scored hand in Balatro but is used for comparison.
 */
public enum HandRank {
    HIGH_CARD,
    PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
    FIVE_OF_A_KIND,
    FLUSH_HOUSE,
    FLUSH_FIVE,
    ROYAL_FLUSH;

    /**
     * Base chips for this hand at level 1 (Balatro values).
     */
    public int getBaseChips() {
        return switch (this) {
            case HIGH_CARD -> 0;
            case PAIR -> 10;
            case TWO_PAIR -> 20;
            case THREE_OF_A_KIND -> 30;
            case STRAIGHT -> 30;
            case FLUSH -> 35;
            case FULL_HOUSE -> 40;
            case FOUR_OF_A_KIND -> 60;
            case STRAIGHT_FLUSH -> 100;
            case FIVE_OF_A_KIND -> 120;
            case FLUSH_HOUSE -> 140;
            case FLUSH_FIVE -> 160;
            case ROYAL_FLUSH -> 100;
        };
    }

    /**
     * Base mult for this hand at level 1 (Balatro values).
     */
    public int getBaseMult() {
        return switch (this) {
            case HIGH_CARD -> 1;
            case PAIR -> 2;
            case TWO_PAIR -> 2;
            case THREE_OF_A_KIND -> 3;
            case STRAIGHT -> 4;
            case FLUSH -> 4;
            case FULL_HOUSE -> 4;
            case FOUR_OF_A_KIND -> 7;
            case STRAIGHT_FLUSH -> 8;
            case FIVE_OF_A_KIND -> 12;
            case FLUSH_HOUSE -> 14;
            case FLUSH_FIVE -> 16;
            case ROYAL_FLUSH -> 8;
        };
    }
}
