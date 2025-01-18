package Cards;

public class Card
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Edition { Poly, Holo, Foil, Neg }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Cards.Card Attributes
    private Edition edition;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Card(Edition aEdition)
    {
        edition = aEdition;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setEdition(Edition aEdition)
    {
        boolean wasSet = false;
        edition = aEdition;
        wasSet = true;
        return wasSet;
    }

    public Edition getEdition()
    {
        return edition;
    }

    public void delete()
    {}


    public String toString()
    {
        return "card, fix me (in Card.java)";
    }
}