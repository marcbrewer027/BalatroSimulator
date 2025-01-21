package Cards;

/**
 * Class representing playing cards.
 */
public class PlayingCard extends Card
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    /**
     * Enum representing seals, can be as follows: </br>
     * </br>
     * - Red </br>
     * - Gold </br>
     * - Blue </br>
     * - Purple
     */
    public enum Seal { Red, Gold, Blue, Purple }

    /**
     * Enum representing enhancement, can be as follows: </br>
     * </br>
     * - Mult </br>
     * - Bonus </br>
     * - Lucky </br>
     * - Wild </br>
     * - Glass </br>
     * - Steel </br>
     * - Stone </br>
     * - Gold
     */
    public enum Enhancement { Mult, Bonus, Lucky, Wild, Glass, Steel, Stone, Gold }

    /**
     * Enum representing suit, can be as follows: </br>
     * </br>
     * - Spade </br>
     * - Heart </br>
     * - Club </br>
     * - Diamonds
     */
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

    /**
     * Constructor for PlayingCard object.
     * @param aEdition
     * @param aSeal
     * @param aEnhancement
     * @param aRank
     * @param aSuit
     */
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

    /**
     * Setter for seal.
     * @param aSeal
     * @return boolean. True if successful.
     */
    public boolean setSeal(Seal aSeal)
    {
        boolean wasSet = false;
        seal = aSeal;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setter for enhancement.
     * @param aEnhancement
     * @return boolean. True if successful.
     */
    public boolean setEnhancement(Enhancement aEnhancement)
    {
        boolean wasSet = false;
        enhancement = aEnhancement;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setter for rank.
     * @param aRank
     * @return boolean. True if successful.
     */
    public boolean setRank(int aRank)
    {
        boolean wasSet = false;
        rank = aRank;
        wasSet = true;
        return wasSet;
    }

    /**
     * Setter for suit.
     * @param aSuit
     * @return boolean. True if successful.
     */
    public boolean setSuit(Suit aSuit) {
        boolean wasSet = false;
        suit = aSuit;
        wasSet = true;
        return wasSet;
    }

    /**
     * Getter for seal.
     * @return Seal
     */
    public Seal getSeal()
    {
        return seal;
    }

    /**
     * Getter for enhancement.
     * @return Enhancement
     */
    public Enhancement getEnhancement()
    {
        return enhancement;
    }

    /**
     * Getter for rank.
     * @return int
     */
    public int getRank() {
        return rank;
    }

    /**
     * Getter for suit.
     * @return Suit
     */
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