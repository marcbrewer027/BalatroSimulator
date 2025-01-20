package Cards;

public class JokerCard extends Card
{

    public enum TriggerType {Played, Scored, Held, Indep, Discard, Perkeo}
    //------------------------
    // Trigger types outline, occurs in order:
    // Played - triggered on hand played
    // Discard - Triggered on discard
    // Scored - triggered on card scored
    // Held - triggered while card held in hand
    // Perkeo - end of shop (perkeo)
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

    public JokerCard(Edition aEdition, boolean aIsEternal, boolean aIsRental, boolean aIsPerishable, TriggerType[] aTriggerTypes)
    {
        super(aEdition);
        isEternal = aIsEternal;
        isRental = aIsRental;
        isPerishable = aIsPerishable;
        triggerTypes = aTriggerTypes;
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

    public boolean setTriggerTypes(TriggerType[] aTriggerTypes)
    {
        boolean wasSet = false;
        triggerTypes = aTriggerTypes;
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