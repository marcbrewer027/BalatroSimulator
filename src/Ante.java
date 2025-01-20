public class Ante {

    private double base;
    private int stage;
    private int bossCode; // may change this to enum or some other secret third thing

    public Ante(int aBase) {
        base = aBase;
        stage = 0;
        bossCode = -1; //TODO figure out how this works
    }

    public boolean nextStage() {
        if (stage < 2) {
            stage += 1;
            return true;
        }
        return false; // if got this, new ante required
    }

    public double getScore() {
        return switch (stage) {
            case 0 -> base;
            case 1 -> base * 1.5;
            case 2 -> base * 2; //TODO figure out The Wall, The Needle, Violet Vessel, etc
            default -> -1;
        };
    }

}
