package Misc;

/**
 * Tags that can appear at the start of a blind (e.g. skip cost for small/big blind).
 */
public enum Tag {
    NONE(0, 0),
    /** Skip small blind for a cost in dollars */
    SKIP_SMALL(3, 0),
    /** Skip big blind for a cost in dollars */
    SKIP_BIG(5, 1);

    private final int skipCostDollars;
    private final int stage; // 0 = small, 1 = big (used to know which blind can be skipped)

    Tag(int skipCostDollars, int stage) {
        this.skipCostDollars = skipCostDollars;
        this.stage = stage;
    }

    public int getSkipCostDollars() {
        return skipCostDollars;
    }

    public int getStage() {
        return stage;
    }
}
