public class Utils {

    public static Ante createAnte(GameState state) {

        int ante = state.getAnte();
        int stake = state.getStake();
        int modifier;

        if (stake < 3) {
            modifier = 0;
        } else if (stake < 6) {
            modifier = 1;
        } else {
            modifier = 2;
        }

        int[][] earlyBases = {
                {100, 100, 100}, //0
                {300, 300, 300}, //1
                {800, 900, 1000}, //2
                {2000, 2600, 3200}, //3
                {5000, 8000, 9000}, //4
                {11000, 20000, 25000}, //5
                {20000, 36000, 60000}, //6
                {35000, 60000, 110000}, //7
                {50000, 100000, 200000}, //8
        };

        if (ante <= 8) {
            return new Ante(earlyBases[ante][modifier]);
        } else {
            return new Ante(100); //TODO implement endless scaling - https://www.desmos.com/calculator/fsvcr75cdx
        }

    }

}
