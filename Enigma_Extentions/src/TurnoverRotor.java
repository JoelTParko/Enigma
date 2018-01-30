/*
TurnoverRotor inherits from BasicRotor, and makes use of the methods and variable defined there
It also extends the rotate method, allowing this rotor to rotate the next one in the machine if the turnover point has been reached
 */
public class TurnoverRotor extends BasicRotor
{
    private int turnoverPosition;
    private BasicRotor nextRotor;

    //Initialise the object, using the parent class' constructor
    //The constructor also establishes the rotors turnover position, as well as the rotor next to it in the machine
    public TurnoverRotor(String rotorName, BasicRotor nextRotor)
    {
        super(rotorName);
        switch (rotorName)
        {
            case "I":
                turnoverPosition = 24;
                break;
            case "II":
                turnoverPosition = 12;
                break;
            case "III":
                turnoverPosition = 3;
                break;
            case "IV":
                turnoverPosition = 17;
                break;
            case "V":
                turnoverPosition = 7;
                break;
            default:
                System.out.println("Something's gone wrong");
                break;
        }
        this.nextRotor = nextRotor;
    }

    public void setNextRotor(BasicRotor nextRotor)
    {
        this.nextRotor = nextRotor;
    }
    //Extends the rotate method by causing the next rotor to rotate is this rotor has reached its turnover position
    @Override
    public void rotate()
    {
        super.rotate();

        if (getPosition() == turnoverPosition && nextRotor != null)
        {
            nextRotor.rotate();
        }
    }
}
