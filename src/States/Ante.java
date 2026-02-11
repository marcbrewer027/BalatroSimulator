package States;

import Misc.Tag;

/**
 * Class representing antes. Each ante has three stages: small blind (0), big blind (1), boss blind (2).
 */
public class Ante {

    private final double base;
    private int stage;
    private final GameState state;
    private final BossBlind bossBlind;
    private final Tag smallBlindTag;
    private final Tag bigBlindTag;

    /**
     * Constructor with default tags and no boss modifier.
     */
    public Ante(int aBase, GameState aState) {
        this(aBase, aState, BossBlind.NONE, Tag.SKIP_SMALL, Tag.SKIP_BIG);
    }

    /**
     * Constructor for States.Ante object.
     * @param aBase Base chip value for small blind
     * @param aState Game state
     * @param boss Boss blind for stage 2 (affects score and display)
     * @param smallTag Tag for small blind (e.g. skip cost)
     * @param bigTag Tag for big blind
     */
    public Ante(int aBase, GameState aState, BossBlind boss, Tag smallTag, Tag bigTag) {
        base = aBase;
        stage = 0;
        state = aState;
        bossBlind = boss != null ? boss : BossBlind.NONE;
        smallBlindTag = smallTag != null ? smallTag : Tag.NONE;
        bigBlindTag = bigTag != null ? bigTag : Tag.NONE;
    }

    /**
     * Move to the next stage (small -> big -> boss). Call after beating the current blind.
     * @return true if there is a next stage, false if ante is complete (boss beaten)
     */
    public boolean nextStage() {
        if (stage < 2) {
            stage += 1;
            return true;
        }
        return false;
    }

    /** Whether this ante is complete (boss blind stage reached). */
    public boolean isComplete() {
        return stage >= 2;
    }

    /** Current stage (0 = small, 1 = big, 2 = boss). */
    public int getStage() {
        return stage;
    }

    /** Score to beat for the current stage. Boss blind multiplier applied at stage 2. */
    public double getScore() {
        return switch (stage) {
            case 0 -> base;
            case 1 -> base * 1.5;
            case 2 -> base * 2 * bossBlind.getScoreMultiplier();
            default -> -1;
        };
    }

    public BossBlind getBossBlind() {
        return bossBlind;
    }

    /** Skip cost in dollars for the current stage (0 = small, 1 = big). */
    public int getSkipCostDollars(int stage) {
        return stage == 0 ? smallBlindTag.getSkipCostDollars() : bigBlindTag.getSkipCostDollars();
    }

    /**
     * Start the blind for the current stage. Resets hands/discards and draws a new hand.
     */
    public Blind startBlind() {
        state.resetHandsAndDiscards();
        return new Blind(state, getScore());
    }

    /** Call when ante is finished (boss beaten). Advances game to next ante, resets round and deck. */
    public void closeAndAdvance() {
        state.setAnte(state.getAnte() + 1);
        state.setRound(1);
        state.resetDeck();
    }

    public String toString() {
        String s1 = formatScore(base);
        String s2 = formatScore(base * 1.5);
        String s3 = formatScore(base * 2 * bossBlind.getScoreMultiplier());
        int smallCost = smallBlindTag.getSkipCostDollars();
        int bigCost = bigBlindTag.getSkipCostDollars();
        return String.format("""
                You are currently at stage %s.
                Stage 1 - Small Blind - Score: %s - can be skipped for $%s.
                Stage 2 - Big Blind - Score: %s - can be skipped for $%s.
                Stage 3 - Boss Blind - Score: %s - %s - %s.
                """,
                stage + 1, s1, smallCost, s2, bigCost, s3,
                bossBlind.getName(), bossBlind.getDescription());
    }

    /** Format score with scientific notation for large values. */
    private static String formatScore(double score) {
        long fl = (long) Math.floor(score);
        if (fl >= 1_000_000) {
            return String.format("%.2e", score);
        }
        return String.valueOf(fl);
    }

}
