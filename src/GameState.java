import java.util.LinkedList;

import Cards.JokerCard;

public class GameState {

    private LinkedList<JokerCard> jokers;
    private Voucher[] vouchers = {};
    private int bal;
    private int baseHands;
    private int baseDiscards;
    private int ante;
    private int round;
    private Voucher shopVoucher; // initialize this


    public GameState(int aBal, int aBaseHands, int aBaseDiscards) {
        jokers = new LinkedList<JokerCard>();
        bal = aBal;
        baseHands = aBaseHands;
        baseDiscards = aBaseDiscards;
        ante = 1;
        round = 0;
    }
    
}
