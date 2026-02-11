package Packs;

import Cards.Card;
import Cards.TarotCard;

import java.util.List;
import java.util.Random;

public class TarotPack extends ConsumablePack {

    public TarotPack(Size aSize) {
        super(aSize);
    }

    @Override
    public List<Card> pick(Random rng) {
        return List.of(new TarotCard(Card.Edition.Poly, false));
    }
}