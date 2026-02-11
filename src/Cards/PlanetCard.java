package Cards;

import Scoring.HandRank;
import States.GameState;

/**
 * Planet cards level up a hand rank (increases mult for that hand type).
 */
public class PlanetCard extends ConsumableCard {

    private static final HandRank[] LEVELABLE = {
            HandRank.PAIR, HandRank.TWO_PAIR, HandRank.THREE_OF_A_KIND,
            HandRank.STRAIGHT, HandRank.FLUSH, HandRank.FULL_HOUSE,
            HandRank.FOUR_OF_A_KIND, HandRank.STRAIGHT_FLUSH
    };

    public PlanetCard(Edition aEdition, boolean aIsNegative) {
        super(aEdition, aIsNegative);
    }

    @Override
    public boolean useCard(GameState state) {
        HandRank r = LEVELABLE[state.getRng().nextInt(LEVELABLE.length)];
        state.setHandLevel(r, state.getHandLevel(r) + 1);
        return true;
    }

    public void delete() {
        super.delete();
    }
}