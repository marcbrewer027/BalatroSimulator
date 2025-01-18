package Packs;

public class Pack
{

    //TODO add logic for picking cards (may be useful for this class to be abstract)

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum Size { Normal, Jumbo, Mega }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Packs.Pack Attributes
    private Size size;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Pack(Size aSize)
    {
        size = aSize;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setSize(Size aSize)
    {
        boolean wasSet = false;
        size = aSize;
        wasSet = true;
        return wasSet;
    }

    public Size getSize()
    {
        return size;
    }

    public void delete()
    {}


    public String toString()
    {
        return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "size" + "=" + (getSize() != null ? !getSize().equals(this)  ? getSize().toString().replaceAll("  ","    ") : "this" : "null");
    }
}