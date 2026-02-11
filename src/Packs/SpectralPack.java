package Packs;

import Cards.Card;
import Cards.SpectralCard;

import java.util.List;
import java.util.Random;

public class SpectralPack extends ConsumablePack {

    public SpectralPack(Size aSize) {
        super(aSize);
    }

    @Override
    public List<Card> pick(Random rng) {
        return List.of(new SpectralCard(Card.Edition.Poly, false));
    }
}