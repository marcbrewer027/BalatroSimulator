package Cards;

/**
 * Class representing Spectral Cards, unimplemented as of Jan 20, 2025
 */
public class SpectralCard extends ConsumableCard
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public SpectralCard(Edition aEdition, boolean aIsNegative)
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