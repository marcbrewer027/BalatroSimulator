package Cards;

public class PlanetCard extends ConsumableCard
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public PlanetCard(Edition aEdition, boolean aIsNegative)
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