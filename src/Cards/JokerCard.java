package Cards;

/**
 * Class representing Joker cards.
 */
public class JokerCard extends Card
{
    /**
     * Enum specifying joker trigger type. Joker may have more than one.</br>
     * </br>
     * Trigger types outline, occurs in order: </br>
     * </br>
     * Played - triggered on hand played. </br>
     * Discard - Triggered on discard. </br>
     * Scored - triggered on card scored. </br>
     * Held - triggered while card held in hand. </br>
     * Perkeo - end of shop (Perkeo).
     */
    public enum TriggerType {Played, Scored, Held, Indep, Discard, Perkeo}

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Cards.JokerCard Attributes
    private boolean isEternal;
    private boolean isRental;
    private boolean isPerishable;
    private TriggerType[] triggerTypes;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    /**
     * Constructor for Joker cards.
     * @param aEdition Edition enum
     * @param aIsEternal Boolean value for eternal status
     * @param aIsRental  Boolean value for rental status
     * @param aIsPerishable Boolean value for perishable status
     * @param aTriggerTypes TriggerType enum array for trigger types
     */
    public JokerCard(Edition aEdition, boolean aIsEternal, boolean aIsRental, boolean aIsPerishable, TriggerType[] aTriggerTypes)
    {
        // TODO add error handling for incompatible status (i.e., perishable + eternal)
        super(aEdition);
        isEternal = aIsEternal;
        isRental = aIsRental;
        isPerishable = aIsPerishable;
        triggerTypes = aTriggerTypes;
    }

    //------------------------
    // INTERFACE
    //------------------------

    /**
     * Setter for isEternal.
     * @param aIsEternal Boolean value for eternal status
     * @return boolean. True if successful.
     */
    public boolean setIsEternal(boolean aIsEternal)
    {
        boolean wasSet = false;
        isEternal = aIsEternal;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setter for isRental.
     * @param aIsRental Boolean value for rental status
     * @return boolean. True if successful.
     */
    public boolean setIsRental(boolean aIsRental)
    {
        boolean wasSet = false;
        isRental = aIsRental;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setter for isPerishable.
     * @param aIsPerishable Boolean value for perishable status
     * @return boolean. True if successful.
     */
    public boolean setIsPerishable(boolean aIsPerishable)
    {
        boolean wasSet = false;
        isPerishable = aIsPerishable;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setter for triggerTypes.
     * @param aTriggerTypes TriggerType enum array for trigger types
     * @return boolean. True if successful.
     */
    public boolean setTriggerTypes(TriggerType[] aTriggerTypes)
    {
        boolean wasSet = false;
        triggerTypes = aTriggerTypes;
        wasSet = true;
        return wasSet;
    }

    /**
     * Getter for isEternal.
     * @return boolean, isEternal.
     */
    public boolean getIsEternal()
    {
        return isEternal;
    }

    /**
     * Getter for isRental.
     * @return boolean, isRental.
     */
    public boolean getIsRental()
    {
        return isRental;
    }

    /**
     * Getter for isPerishable.
     * @return boolean, isPerishable.
     */
    public boolean getIsPerishable()
    {
        return isPerishable;
    }

    /**
     * Getter for TriggerType.
     * @return TriggerType[]
     */
    public TriggerType[] getTriggerTypes() {
        return triggerTypes;
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