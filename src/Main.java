import Misc.Utils;
import States.Ante;
import States.Blind;
import States.GameState;

public class Main {

    public static void main(String[] args) {

        GameState state = new GameState(4, 3, 3, 5,2, 8,1); // This would be set by parameters later.
        System.out.println(state);

        Ante currentAnte = Utils.createAnte(state);
        System.out.println(currentAnte);

        Blind currentBlind = currentAnte.startBlind();
        System.out.println(currentBlind);

    }

}
