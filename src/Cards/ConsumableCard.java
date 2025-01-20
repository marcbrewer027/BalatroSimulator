package Cards;

public abstract class ConsumableCard extends Card
{

    //------------------------
    // STATIC VARIABLES
    //------------------------

    public static final Edition edition = null;

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Cards.ConsumableCard Attributes
    private boolean isNegative;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public ConsumableCard(Edition aEdition, boolean aIsNegative)
    {
        super(aEdition);
        isNegative = aIsNegative;
    }

    public abstract boolean useCard();


    //------------------------
    // INTERFACE
    //------------------------

    public boolean setIsNegative(boolean aIsNegative)
    {
        boolean wasSet = false;
        isNegative = aIsNegative;
        wasSet = true;
        return wasSet;
    }

    public boolean getIsNegative()
    {
        return isNegative;
    }

    public void delete()
    {
        super.delete();
    }


    public String toString()
    {
        return super.toString();
    }
}