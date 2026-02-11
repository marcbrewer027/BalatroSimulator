package States;

/**
 * Boss blind types that modify the boss stage (stage 2) of an ante.
 */
public enum BossBlind {
    NONE("", "", 1.0),
    THE_WALL("The Wall", "4x base score", 4.0),
    THE_NEEDLE("The Needle", "1 hand per round", 1.0),
    THE_GARGOYLE("The Gargoyle", "1 discard per round", 1.0),
    THE_HEAD("The Head", "1 hand per round", 1.0),
    VIOLET_VESSEL("Violet Vessel", "2x base score", 2.0);

    private final String name;
    private final String description;
    private final double scoreMultiplier;

    BossBlind(String name, String description, double scoreMultiplier) {
        this.name = name;
        this.description = description;
        this.scoreMultiplier = scoreMultiplier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getScoreMultiplier() {
        return scoreMultiplier;
    }
}
