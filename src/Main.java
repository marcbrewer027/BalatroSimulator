public class Main {

    public static void main(String[] args) {

        GameState state = new GameState(4, 3, 3, 5,2,1); // This would be set by parameters later.

        Ante currentAnte = Utils.createAnte(state);

        System.out.println(state);
        System.out.println(currentAnte);
    }

}
