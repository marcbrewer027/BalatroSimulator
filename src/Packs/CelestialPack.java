package Packs;

import Cards.Card;
import Cards.PlanetCard;

import java.util.List;
import java.util.Random;

public class CelestialPack extends ConsumablePack {

    public CelestialPack(Size aSize) {
        super(aSize);
    }

    @Override
    public List<Card> pick(Random rng) {
        return List.of(new PlanetCard(Card.Edition.Poly, false));
    }
}