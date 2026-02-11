package Cards;

import States.GameState;

import java.util.LinkedList;

/**
 * Tarot cards modify the deck (e.g. enhance a card).
 */
public class TarotCard extends ConsumableCard {

    public TarotCard(Edition aEdition, boolean aIsNegative) {
        super(aEdition, aIsNegative);
    }

    @Override
    public boolean useCard(GameState state) {
        LinkedList<PlayingCard> deck = state.getDeckCards();
        if (deck.isEmpty()) return false;
        PlayingCard target = deck.get(state.getRng().nextInt(deck.size()));
        target.setEnhancement(PlayingCard.Enhancement.Mult);
        return true;
    }

    public void delete() {
        super.delete();
    }
}