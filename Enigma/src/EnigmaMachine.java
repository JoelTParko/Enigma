public class EnigmaMachine
{
    private Plugboard plugboard;
    private BasicRotor[] rotors = new BasicRotor[3];
    private Reflector reflector;

    //Creates an empty plugboard when the machine is created
    public EnigmaMachine()
    {
        plugboard = new Plugboard();
    }

    //Adds a plug to the machines plugboard
    public boolean addPlug(char end1, char end2)
    {
        return plugboard.addPlug(end1, end2);
    }

    //Removes all plugs from the plugboard
    public void clearPlugBoard()
    {
        plugboard.clear();
    }

    //Adds a rotor to the machine
    public void addRotor(BasicRotor newRotor, int slot)
    {
        rotors[slot] = newRotor;
    }

    //Returns the rotor that corresponds with the given slot
    public BasicRotor getRotor(int slot)
    {
        return rotors[slot];
    }

    //Adds a reflector to the machine
    public void addReflector(Reflector reflector)
    {
        this.reflector = reflector;
    }

    //Returns the reflector used by the machine
    public Reflector getReflector()
    {
        return reflector;
    }

    //Sets the current position of the rotor in the given slot
    public void setPoisiton (int slot, int position)
    {
        rotors[slot].setPosition(position);
    }

    //Receives a character as an input, and encodes it by sending it through the components of the machine
    public char encodeLetter(char plainChar)
    {
        char encryptedChar;
        int encryptedInt;

        //The char given in the parameter is sent through the plugboard and then converted to an integer
        encryptedChar = plugboard.substitute(plainChar);
        encryptedInt = encryptedChar - 'A';

        for (int i = 0; i < rotors.length; i++)
            encryptedInt = rotors[i].substitute(encryptedInt);


        encryptedInt = reflector.substitute(encryptedInt);

        for (int i = rotors.length - 1; i >=0 ; i--)
            encryptedInt = rotors[i].substituteBack(encryptedInt);

        rotors[0].rotate();
        encryptedChar = (char)(encryptedInt + 'A');
        encryptedChar = plugboard.substitute(encryptedChar);
        return encryptedChar;
    }

    //Runs two tests that check that the machine encodes strings correctly
    public void start()
    {
        String message1 = "GFWIQH";
        String message2 = "GACIG";
        String output = "";

        //Test 1
        clearPlugBoard();
        addPlug('A', 'M');
        addPlug('G', 'L');
        addPlug('E', 'T');
        addRotor(new BasicRotor("I"), 0);
        addRotor(new BasicRotor("II"), 1);
        addRotor(new BasicRotor("III"), 2);
        setPoisiton(0, 6);
        setPoisiton(1, 12);
        setPoisiton(2,5);
        addReflector(new Reflector("reflector1"));
        for (char c: message1.toCharArray())
        {
            output += encodeLetter(c);
        }
        System.out.println(output);

        //Test 2
        clearPlugBoard();
        addPlug('B', 'C');
        addPlug('R', 'I');
        addPlug('S', 'M');
        addPlug('A', 'F');
        addRotor(new BasicRotor("IV"), 0);
        addRotor(new BasicRotor("V"), 1);
        addRotor(new BasicRotor("II"), 2);
        setPoisiton(0, 23);
        setPoisiton(1, 4);
        setPoisiton(2, 9);
        addReflector(new Reflector("reflector2"));
        output = "";
        for (char c: message2.toCharArray())
        {
            output += encodeLetter(c);
        }
        System.out.println(output);
    }
}
