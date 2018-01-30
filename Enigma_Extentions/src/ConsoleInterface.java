import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
The ConsoleInterface class contains all the extensions to the coursework
It allows a user to create an enigma machine with custom settings, and lets users input text into the machine in 3 different ways
 */
public class ConsoleInterface
{
    private EnigmaMachine enigmaMachine;
    private int[] rotorStartPositions;

    //The constructor initialises the objects member variables
    public ConsoleInterface()
    {
        enigmaMachine = new EnigmaMachine();
        rotorStartPositions = new int[3];
    }

    //Allows the user to configure the settings on the enigma machine
    //Displays the menu and runs the method corresponding with the user's input
    public void run()
    {
        String input;

        System.out.println("Welcome to the Digital Enigma");
        System.out.println("Please configure the settings of the enigma machine");
        System.out.println();
        setUpEnigmaMachine();
        do
        {
            System.out.println("********DIGITAL ENIGMA MACHINE********");
            System.out.println("1. Encrypt/decrypt file");
            System.out.println("2. Encrypt/decrypt full string");
            System.out.println("3. Encrypt/decrypt input string by character");
            System.out.println("4. Change enigma settings");
            System.out.println("5. Quit");
            System.out.println("Enter 1, 2, 3, or 4");
            input = getInput();
            System.out.println();
            switch (input)
            {
                case "1":
                    encryptFile();
                    break;
                case "2":
                    encryptString();
                    break;
                case "3":
                    String string = "";
                    inputByChar(string);
                    break;
                case "4":
                    setUpEnigmaMachine();
                    break;
                case "5":
                    System.out.println("Thank-you for using the Digital Enigma Machine");
                default:
                    System.out.println("Please enter 1, 2, 3, 4, 5");
                    break;
            }
            resetRotorPositions();
        }while(!input.equals("5"));
    }

    //Sets the rotor positions back to their initial points
    private void resetRotorPositions()
    {
        for (int i = 0; i < rotorStartPositions.length; i++)
        {
            enigmaMachine.getRotor(i).setPosition(rotorStartPositions[i]);
        }
    }

    //Sets up the enigma machine by adding all the necessary parts, and saving the rotor start positions
    private void setUpEnigmaMachine()
    {
        addPlugs();
        addRotors();
        addReflector();

        for (int i = 0; i < rotorStartPositions.length; i++)
        {
            rotorStartPositions[i] = enigmaMachine.getRotor(i).getPosition();
        }


    }

    //Encodes a string one character at a time
    //The method is calls itself recursively until the entire string has been encoded
    private void inputByChar(String encryptedText)
    {
        char nextChar;
        try
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a character to encrypt, or press '.' to finish");
            nextChar = input.readLine().toUpperCase().toCharArray()[0];
            if(nextChar != '.')
            {
                encryptedText += enigmaMachine.encodeLetter(nextChar);
                System.out.println(encryptedText);
                System.out.println();
                inputByChar(encryptedText);
            }
            else
            {
                System.out.println("Final Encryption: " + encryptedText);
                System.out.println();
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

    }


    //Creates a new enigma file and gives it a file location specified by the user
    private void encryptFile()
    {
        String fileLocation;

        System.out.println("Enter the location of the text file you wish to encrypt/decrypt");
        fileLocation = getInput() + ".txt";
        EnigmaFile enigmaFile = new EnigmaFile(fileLocation, enigmaMachine);
        enigmaFile.run();
        System.out.println();
    }

    //Gets the user to enter a string into the console, and then encodes it using the enigma machine
    private void encryptString()
    {
        String plainText, encryptedText = "";

        System.out.println("Enter the string you wish to encrypt/decrypt");
        plainText = getInput().toUpperCase();
        for (char c: plainText.toCharArray())
        {
            encryptedText += enigmaMachine.encodeLetter(c);
        }
        System.out.println("Output:");
        System.out.println(encryptedText);
        System.out.println();
    }

    //Allows the user to add plugs to the enigma machine
    private void addPlugs()
    {
        int plugCount;
        char end1, end2;
        System.out.println("How many plugs do you wish to add?");
        plugCount = Integer.parseInt(getInput());

        for (int i = 0; i < plugCount; i++)
        {
            System.out.println("Enter the first end of the plug");
            end1 = getInput().toCharArray()[0];
            System.out.println("Enter the second end of the plug");
            end2 = getInput().toCharArray()[0];
            enigmaMachine.addPlug(end1, end2);
        }
    }

    //Allows a user to choose a reflector mapping for the enigma machine
    private void addReflector()
    {
        Reflector reflector;
        int mapping;

        System.out.println("Choose a reflector mapping");
        System.out.println("Enter 1 for mapping one, or 2 for mapping two");
        mapping = Integer.parseInt(getInput());
        if(mapping == 1)
        {
            reflector = new Reflector("reflector1");
        }
        else
        {
            reflector = new Reflector("reflector2");
        }
        System.out.println();
        enigmaMachine.addReflector(reflector);
    }

    //Creates and adds three turnover rotors to the enigma machine
    private void addRotors()
    {
        TurnoverRotor rotor1, rotor2, rotor3;

        System.out.println("First rotor");
        rotor1 = createRotor();
        System.out.println();
        System.out.println("Second rotor");
        rotor2 = createRotor();
        System.out.println();
        System.out.println("Third rotor");
        rotor3 = createRotor();
        System.out.println();

        rotor1.setNextRotor(rotor2);
        rotor2.setNextRotor(rotor3);
        enigmaMachine.addRotor(rotor1, 0);
        enigmaMachine.addRotor(rotor2, 1);
        enigmaMachine.addRotor(rotor3, 2);
    }

    //Receives user input for rotor type and rotor position
    //The method uses these variables to create a new turnover rotor
    private TurnoverRotor createRotor()
    {
        String type;
        int position;
        TurnoverRotor rotor;

        System.out.println("Enter the rotor type");
        type = getInput();
        System.out.println("Enter the rotor position");
        position = Integer.parseInt(getInput());
        rotor = new TurnoverRotor(type, null);
        rotor.setPosition(position);
        return rotor;
    }

    //Returns the next line the user enters into the console as a string
    private String getInput()
    {
        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            input = reader.readLine();
        }
        catch (IOException e)
        {
            System.out.println("Error, invalid input");
            System.err.println(e);
        }
        return input;
    }
}
