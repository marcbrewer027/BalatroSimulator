package Packs;

import Cards.Card;

import java.util.List;
import java.util.Random;

/**
 * Base for consumable packs (Tarot, Planet, Spectral). Always picks 1 card.
 */
public abstract class ConsumablePack extends Pack {

    public ConsumablePack(Size aSize) {
        super(aSize);
    }

    @Override
    protected int getPickCount() {
        return 1;
    }

    @Override
    public abstract List<Card> pick(Random rng);
}