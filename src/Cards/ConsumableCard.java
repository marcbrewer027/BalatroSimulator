package Cards;

/**
 * Class representing consumable cards. Inherited by different classes of consumables.
 */
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

    /**
     * Constructor for consumable cards. includes isNegative specifically for Perkeo.
     * @param aEdition
     * @param aIsNegative
     */
    public ConsumableCard(Edition aEdition, boolean aIsNegative)
    {
        super(aEdition);
        isNegative = aIsNegative;
    }

    /**
     * Method to use consumable card.
     * @return Boolean if successful.
     */
    public abstract boolean useCard();


    //------------------------
    // INTERFACE
    //------------------------

    /**
     * Setter for isNegative.
     * @param aIsNegative
     * @return boolean if successful.
     */
    public boolean setIsNegative(boolean aIsNegative)
    {
        boolean wasSet = false;
        isNegative = aIsNegative;
        wasSet = true;
        return wasSet;
    }

    /**
     * Getter for isNegative.
     * @return boolean specifying negative
     */
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