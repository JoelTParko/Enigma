/*
The rotor class is abstract, and used to specify methods and properties common to many of the parts in the enigma machine
 */
public abstract class Rotor
{
    private String name;
    private int position;
    private int[] mapping;
    protected final int ROTORSIZE = 26;

    //Sets the position of the Rotor to an int between 0 and 25
    public void setPosition(int position)
    {
        if(position >= 0 && position <= 25)
        {
            this.position = position;
        }
        else
        {
            throw new IllegalArgumentException("Position must be between 0 and 25");
        }
    }

    //Returns the current position of the rotor
    public int getPosition()
    {
        return position;
    }

    //In order to use the mapping variable defined in rotor, a setter method was needed to set the values in the mapping array
    public void setMapping(int[] mapping)
    {
        this.mapping = mapping;
    }

    //In order to use the mapping variable defined in rotor, a getter method was needed to access the mapping array
    public int[] getMapping()
    {
        return mapping;
    }

    //Sets the name of the rotor
    public void initialise(String name)
    {
        this.name = name;
    }

    //Abstract method that is implemented in sub-classes
    public abstract int substitute(int value);
}