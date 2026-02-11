package Cards;

import States.GameState;

import java.util.LinkedList;

/**
 * Spectral cards modify the deck (e.g. duplicate a card).
 */
public class SpectralCard extends ConsumableCard {

    public SpectralCard(Edition aEdition, boolean aIsNegative) {
        super(aEdition, aIsNegative);
    }

    @Override
    public boolean useCard(GameState state) {
        LinkedList<PlayingCard> deck = state.getDeckCards();
        if (deck.isEmpty()) return false;
        PlayingCard original = deck.get(state.getRng().nextInt(deck.size()));
        PlayingCard copy = new PlayingCard(
                original.getEdition(),
                original.getSeal(),
                original.getEnhancement(),
                original.getRank(),
                original.getSuit()
        );
        deck.add(copy);
        return true;
    }

    public void delete() {
        super.delete();
    }
}