package Cards;

/**
 * Class representing Tarot Cards, unimplemented as of Jan 20, 2025.
 */
public class TarotCard extends ConsumableCard
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public TarotCard(Edition aEdition, boolean aIsNegative)
    {
        super(aEdition, aIsNegative);
    }

    @Override
    public boolean useCard() {
        //TODO implement this
        return false;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public void delete()
    {
        super.delete();
    }

}