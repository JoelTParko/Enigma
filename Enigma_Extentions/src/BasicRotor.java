/*
The BasicRotor class inherits from Rotor
It overrides the initialise and substitute methods, as well as making use of the methods and variables defined in Rotor
 */
public class BasicRotor extends Rotor
{

    //These are the different versions of the rotor map. The map used by a rotor is chosen in the initialise method
    private static final int[] I = { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
    private static final int[] II = { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
    private static final int[] III = { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
    private static final int[] IV = { 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
    private static final int[] V = { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };

    private int[] inverseMap;

    //Initialises the object by choosing a map for the rotor, and setting up an inverse map
    public BasicRotor(String rotorName)
    {
        int mapOutput;
        initialise(rotorName);
        inverseMap = new int[getMapping().length];
        for (int i = 0; i < getMapping().length; i++) //Creates an inverse map from the rotor map
        {
            mapOutput = getMapping()[i];
            inverseMap[mapOutput] = i;
        }
    }

    //Increases the value of the rotors position by 1
    //If the new position is larger than the rotors max position, the position number is reset
    public void rotate()
    {
        int newPosition;

        newPosition = getPosition() + 1;
        if (newPosition == ROTORSIZE)
        {
            newPosition = 0;
        }
        setPosition(newPosition);

    }

    //Takes an integer as an input, and returns a alternative integer that is found by sending the input through the inverse map
    public int substituteBack(int plainVal)
    {
        int mappedVal;

        //Adjusts the value of the input so that the position of the rotor is accounted for
        plainVal = plainVal - getPosition();
        if(plainVal < 0)
        {
            plainVal = plainVal + ROTORSIZE;
        }

        mappedVal = inverseMap[plainVal];
        mappedVal = mappedVal + getPosition();

        if(mappedVal > ROTORSIZE - 1)
        {
            mappedVal = mappedVal - ROTORSIZE;
        }

        return mappedVal;
    }

    //Takes an integer as an input, and returns a different integer that is found by sending the input through the rotor map
    @Override
    public int substitute(int plainVal)
    {
        int mappedVal;

        //Adjusts the value of the input so that the position of the rotor is accounted for
        plainVal = plainVal - getPosition();
        if(plainVal < 0)
        {
            plainVal = plainVal + ROTORSIZE;
        }

        mappedVal = getMapping()[plainVal];

        mappedVal = mappedVal + getPosition();
        if(mappedVal > ROTORSIZE - 1)
        {
            mappedVal = mappedVal - ROTORSIZE;
        }

        return mappedVal;
    }

    //Takes the name of the rotor map as an input, and chooses which map setting to use for the rotor
    @Override
    public void initialise(String rotorName)
    {
        super.initialise(rotorName);
        switch (rotorName)
        {
            case "I":
                setMapping(I);
                break;
            case "II":
                setMapping(II);
                break;
            case "III":
                setMapping(III);
                break;
            case "IV":
                setMapping(IV);
                break;
            case "V":
                setMapping(V);
                break;
            default:
                throw new IllegalArgumentException("Invalid rotor name input");
        }
    }
}