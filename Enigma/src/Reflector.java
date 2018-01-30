/*
The Reflector class inherits from Rotor
It overrides the substitute and initialise methods, as well as making use of the methods and variables defined in rotor
 */
public class Reflector extends Rotor
{
    private static final int[] REFLECTOR1 = {24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
    private static final int[] REFLECTOR2 = {5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };

    //Initialise the reflector by choosing which mapping variation to use
    public Reflector(String name)
    {
        initialise(name);
    }

    //Takes an integer as an input, and substitutes it with a new int based on the value found at the the input map location
    @Override
    public int substitute(int mapIndex)
    {
        return getMapping()[mapIndex];
    }

    //Selects a map based on the name that is passed to it
    @Override
    public void initialise(String name)
    {
        super.initialise(name);
        if(name.equals("reflector1"))
        {
            setMapping(REFLECTOR1);
        }
        else if(name.equals("reflector2"))
        {
            setMapping(REFLECTOR2);
        }
    }
}