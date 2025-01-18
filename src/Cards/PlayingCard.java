package Cards;

public class PlayingCard extends Card
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Seal { Red, Gold, Blue, Purple }
    public enum Enhancement { Mult, Bonus, Lucky, Wild, Glass, Steel, Stone, Gold }
    public enum Suit { Spade, Heart, Club, Diamond }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Cards.PlayingCard Attributes
    private Seal seal;
    private Enhancement enhancement;
    private int rank;
    private Suit suit;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public PlayingCard(Edition aEdition, Seal aSeal, Enhancement aEnhancement, int aRank, Suit aSuit)
    {
        super(aEdition);
        seal = aSeal;
        enhancement = aEnhancement;
        rank = aRank;
        suit = aSuit;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setSeal(Seal aSeal)
    {
        boolean wasSet = false;
        seal = aSeal;
        wasSet = true;
        return wasSet;
    }

    public boolean setEnhancement(Enhancement aEnhancement)
    {
        boolean wasSet = false;
        enhancement = aEnhancement;
        wasSet = true;
        return wasSet;
    }

    public boolean setRank(int aRank)
    {
        boolean wasSet = false;
        rank = aRank;
        wasSet = true;
        return wasSet;
    }

    public boolean setSuit(Suit aSuit) {
        boolean wasSet = false;
        suit = aSuit;
        wasSet = true;
        return wasSet;
    }

    public Seal getSeal()
    {
        return seal;
    }

    public Enhancement getEnhancement()
    {
        return enhancement;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
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