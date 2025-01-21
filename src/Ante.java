/**
 * Class representing antes.
 */
public class Ante {

    // TODO add a way of 'closing' or 'deleting' antes when they're finished.

    private final double base;
    private int stage;
    private final int bossCode; // may change this to enum or some other secret third thing

    /**
     * Constructor for Ante object.
     * @param aBase Base chip value
     */
    public Ante(int aBase) {
        base = aBase;
        stage = 0;
        bossCode = -1; //TODO figure out how this works
    }

    /**
     * Method for moving to the next stage of the ante.
     * @return boolean. True if successful. If false, new ante must be created.
     */
    public boolean nextStage() {
        if (stage < 2) {
            stage += 1;
            return true;
        }
        return false; // if got this, new ante required
    }

    /**
     * Method for finding score to beat for blind.
     * @return double. Based on base score.
     */
    public double getScore() {
        return switch (stage) {
            case 0 -> base;
            case 1 -> base * 1.5;
            case 2 -> base * 2; //TODO figure out The Wall, The Needle, Violet Vessel, etc
            default -> -1;
        };
    }

    public String toString(){
        return String.format("""
                You are currently at stage 1. \n
                Stage 1 - Small Blind - Score: %s - can be skipped for %s. \r
                Stage 2 - Big Blind - Score %s - can be skipped for %s. \r
                Stage 3 - Boss Blind - Score %s - %s - %s.
                """, ((int) Math.floor(base)),"[tag placeholder]",((int) Math.floor(base * 1.5)),"[tag placeholder]",((int) Math.floor(base * 2)),"[boss name]","[boss description]"); // NOTE: when using Math.floor, we cannot use it for scientific notation as it is a floating point. TODO figure out scientific notation
    }

}
