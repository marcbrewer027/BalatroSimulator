package Packs;

import Cards.Card;

import java.util.List;
import java.util.Random;

/**
 * Base for card packs. Subclasses implement pick() to draw cards from the pack.
 */
public abstract class Pack {

    public enum Size { Normal, Jumbo, Mega }

    private Size size;

    public Pack(Size aSize) {
        size = aSize;
    }

    public boolean setSize(Size aSize) {
        size = aSize;
        return true;
    }

    public Size getSize() {
        return size;
    }

    /** Number of cards to pick based on size (override in subclasses if different). */
    protected int getPickCount() {
        return switch (size) {
            case Normal -> 1;
            case Jumbo -> 2;
            case Mega -> 3;
        };
    }

    /**
     * Open the pack and pick cards using the given RNG.
     * @param rng random number generator
     * @return list of cards from the pack (caller adds to game state)
     */
    public abstract List<Card> pick(Random rng);

    public void delete() {}

    public String toString() {
        return getClass().getSimpleName() + "[size=" + size + "]";
    }
}