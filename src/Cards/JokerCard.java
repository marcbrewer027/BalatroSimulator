package Cards;

public class JokerCard extends Card
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Cards.JokerCard Attributes
    private boolean isEternal;
    private boolean isRental;
    private boolean isPerishable;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public JokerCard(Edition aEdition, boolean aIsEternal, boolean aIsRental, boolean aIsPerishable)
    {
        super(aEdition);
        isEternal = aIsEternal;
        isRental = aIsRental;
        isPerishable = aIsPerishable;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setIsEternal(boolean aIsEternal)
    {
        boolean wasSet = false;
        isEternal = aIsEternal;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsRental(boolean aIsRental)
    {
        boolean wasSet = false;
        isRental = aIsRental;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsPerishable(boolean aIsPerishable)
    {
        boolean wasSet = false;
        isPerishable = aIsPerishable;
        wasSet = true;
        return wasSet;
    }

    public boolean getIsEternal()
    {
        return isEternal;
    }

    public boolean getIsRental()
    {
        return isRental;
    }

    public boolean getIsPerishable()
    {
        return isPerishable;
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