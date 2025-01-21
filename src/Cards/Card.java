package Cards;

        /**
         * Card object, keeps track of editions. inherited by specific card types.
         */
public class Card
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    /**
     * Enumerator specifying card edition, including:</br>
     * </br>
     * Poly,</br>
     * Holo,</br>
     * Foil,</br>
     * Neg
     */
    public enum Edition { Poly, Holo, Foil, Neg }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Cards.Card Attributes
    private Edition edition;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    /**
     * Constructor for Card object. Probably won't have to use this in base game.
     * @param aEdition Edition enum
     */
    public Card(Edition aEdition)
    {
        edition = aEdition;
    }

    //------------------------
    // INTERFACE
    //------------------------

    /**
     * Setter for edition
     * @param aEdition Edition enum
     * @return boolean stating successful change.
     */
    public boolean setEdition(Edition aEdition)
    {
        boolean wasSet = false;
        edition = aEdition;
        wasSet = true;
        return wasSet;
    }

    /**
     * Getter for edition
     * @return edition of object
     */
    public Edition getEdition()
    {
        return edition;
    }

    public void delete() // TODO implement delete (maybe rename to destroy?)
    {}


    public String toString()
    {
        return "card, fix me (in Card.java)";
    }
}