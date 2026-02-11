package Packs;

import Cards.Card;
import Cards.JokerCard;
import Misc.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StandardPack extends Pack {

    public StandardPack(Size aSize) {
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