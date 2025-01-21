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
     * - Diamond
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
     * Constructor for PlayingCard object. </br>
     * </br>
     * Rank values are as follows: </br>
     * </br>
     * 2 - Two</br>
     * 3 - Three</br>
     * 4 - Four</br>
     * 5 - Five</br>
     * 6 - Six</br>
     * 7 - Seven</br>
     * 8 - Eight</br>
     * 9 - Nine</br>
     * 10 - Ten</br>
     * 11 - Jack</br>
     * 12 - Queen</br>
     * 13 - King</br>
     * 14 - Ace
     *
     * @param aEdition Edition enum for edition
     * @param aSeal Seal enum for seal
     * @param aEnhancement Enhancement enum for enhancement
     * @param aRank integer value representing rank
     * @param aSuit Suit enum for suit
     */
    public PlayingCard(Edition aEdition, Seal aSeal, Enhancement aEnhancement, int aRank, Suit aSuit)
    {
        // TODO add error handling for invalid rank values (2 <= rank <= 14)
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
     * @param aSeal Seal enum for seal
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
     * @param aEnhancement Enhancement enum for enhancement
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
     * Setter for rank. </br>
     * </br>
     * Rank values are as follows: </br>
     * </br>
     * 2 - Two</br>
     * 3 - Three</br>
     * 4 - Four</br>
     * 5 - Five</br>
     * 6 - Six</br>
     * 7 - Seven</br>
     * 8 - Eight</br>
     * 9 - Nine</br>
     * 10 - Ten</br>
     * 11 - Jack</br>
     * 12 - Queen</br>
     * 13 - King</br>
     * 14 - Ace
     *
     * @param aRank integer value representing rank
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
     * @param aSuit Suit enum for suit
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
     * Returns rank based on rank int value.
     * @return String object
     */
    public String getCharRank() {
        return switch (rank) {
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            case 14 -> "A";
            default -> ((Integer) rank).toString();
        };
    }

    /**
     * Getter for suit.
     * @return Suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns Unicode character for suit of a card.
     * @return char
     */
    public char getCharSuit() {
        return switch (suit) {
            case Spade -> '♠';
            case Heart -> '♥';
            case Club -> '♣';
            case Diamond -> '♦';
            default -> '⌀';
        };
    }

    /**
     * Returns chip value of card based on rank. </br></br>
     * Does not take Hiker into consideration as of Jan 21st, 2025.
     * @return int
     */
    public int getChipValue() {
        return switch (rank) {
            case 11, 12, 13 -> 10;
            case 14 -> 11;
            default -> rank;
        };
    }

    public void delete()
    {
        super.delete();
    }


    public String toString()
    {
        return String.format("[%s%s, worth %s chips.]",getCharRank(),getCharSuit(),getChipValue()); // TODO add specifying for enhancements, seals, editions
    }
}