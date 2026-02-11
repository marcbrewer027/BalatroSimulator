package Packs;

import Cards.Card;
import Cards.JokerCard;
import Misc.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Joker pack (same as StandardPack for now). */
public class BuffoonPack extends Pack {

    public BuffoonPack(Size aSize) {
        super(aSize);
    }

    @Override
    public List<Card> pick(Random rng) {
        List<JokerCard> pool = Utils.getJokerPoolList();
        List<Card> result = new ArrayList<>();
        if (pool.isEmpty()) return result;
        int count = getPickCount();
        for (int i = 0; i < count; i++) {
            result.add(pool.get(rng.nextInt(pool.size())));
        }
        return result;
    }
}
