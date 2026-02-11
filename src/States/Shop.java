package States;

import Cards.Card;
import Cards.ConsumableCard;
import Cards.JokerCard;
import Misc.Voucher;
import Packs.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Shop state: packs for sale with prices, reroll cost.
 */
public class Shop {

    private static final int REROLL_COST = 1;
    private static final int DEFAULT_PACK_PRICE = 5;

    private final GameState state;
    private final List<Pack> packs;
    private final List<Integer> prices;
    private final Random rng;

    public Shop(GameState state) {
        this.state = state;
        this.rng = state.getRng();
        this.packs = new ArrayList<>();
        this.prices = new ArrayList<>();
    }

    /** Add a pack for sale at default price. */
    public void addPack(Pack pack) {
        addPack(pack, DEFAULT_PACK_PRICE);
    }

    /** Add a pack for sale at the given price. */
    public void addPack(Pack pack, int price) {
        packs.add(pack);
        prices.add(price);
    }

    /** Number of slots. */
    public int getSlotCount() {
        return packs.size();
    }

    /** Get pack at slot (0-based). */
    public Pack getPack(int slot) {
        return packs.get(slot);
    }

    /** Get base price at slot (0-based). */
    public int getPrice(int slot) {
        return prices.get(slot);
    }

    /** Get price at slot after voucher effects (e.g. discount). */
    public int getAdjustedPrice(int slot) {
        int p = prices.get(slot);
        for (Voucher v : state.getVouchers()) {
            p = v.applyToPrice(p);
        }
        return p;
    }

    /**
     * Buy the pack at the given slot. Deducts money and adds drawn cards to state.
     * @param slot 0-based slot index
     * @return true if purchase succeeded
     */
    public boolean buy(int slot) {
        if (slot < 0 || slot >= packs.size()) return false;
        int price = getAdjustedPrice(slot);
        if (state.getBal() < price) return false;

        Pack pack = packs.get(slot);
        List<Card> cards = pack.pick(rng);

        for (Card c : cards) {
            if (c instanceof JokerCard j) {
                state.addJoker(j);
            } else if (c instanceof ConsumableCard cons) {
                state.addConsumable(cons);
            }
        }

        state.setBal(state.getBal() - price);
        packs.remove(slot);
        prices.remove(slot);
        return true;
    }

    /**
     * Reroll the shop: deduct cost and clear slots. Caller must repopulate via addPack.
     * @param cost dollars to deduct (e.g. 5 + number of previous rerolls this shop)
     * @return true if reroll succeeded
     */
    public boolean reroll(int cost) {
        if (cost < 0 || state.getBal() < cost) return false;
        state.setBal(state.getBal() - cost);
        packs.clear();
        prices.clear();
        return true;
    }

    /** Base reroll cost when not using custom cost (legacy). */
    public int getRerollCost() {
        return REROLL_COST;
    }
}
