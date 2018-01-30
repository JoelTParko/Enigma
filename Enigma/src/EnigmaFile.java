import java.io.*;

/*
This class will read a text file, and convert the contents into a single string
The string is then sent through an enigma machine that is used to encode or decode the message contained in the file
The result is then written to a second text file
 */
public class EnigmaFile
{
    private String fileLocation;
    private EnigmaMachine enigmaMachine;

    //Initialises the file location and the enigma machine
    public EnigmaFile(String fileLocation)
    {
        this.fileLocation = fileLocation;
        enigmaMachine = new EnigmaMachine();

    }

    //This method sets up the enigma machine so that the third test specified in the coursework can be completed
    public void test3()
    {
        enigmaMachine.addPlug('Q', 'F');
        enigmaMachine.addRotor(new TurnoverRotor("III", null), 2);
        enigmaMachine.addRotor(new TurnoverRotor("II", enigmaMachine.getRotor(2)), 1);
        enigmaMachine.addRotor(new TurnoverRotor("I", enigmaMachine.getRotor(1)), 0);
        enigmaMachine.setPoisiton(0, 23);
        enigmaMachine.setPoisiton(1, 11);
        enigmaMachine.setPoisiton(2, 7);
        enigmaMachine.addReflector(new Reflector("reflector1"));

        run();
    }

    //Reads a string from a text file, encodes it using the enigma machine, and then writes the output to a second text file
    public void run()
    {
        String plainText, encryptedText;

        plainText = readFile();
        System.out.println(plainText);
        encryptedText = encryptString(plainText);
        writeFile(encryptedText);
        System.out.println(encryptedText);
    }

    //This method reads a text file found at the locations contained in fileLocation
    //The contents of the file are converted into a single string and returned by the method
    private String readFile()
    {
        String text = "";
        String nextLine;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            nextLine = br.readLine();

            while(nextLine != null)
            {
                text += nextLine;
                nextLine = br.readLine();
            }
            br.close();
        }
        catch (IOException e)
        {
            System.err.println("Error, file not found");
        }

        return text;
    }

    //Takes a string as an input, and writes it to a text file called EncryptedFile
    private void writeFile(String encryptedText)
    {
        try
        {
            FileWriter writer = new FileWriter("EncryptedFile.txt");
            writer.write(encryptedText);
            writer.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }

    //Takes a string as an input, and encrypts it using the enigma machine
    private String encryptString(String plainText)
    {
        String encryptedText = "";
        plainText = plainText.toUpperCase();

        for (char c : plainText.toCharArray())
        {
            if (c != ' ')
            {
                encryptedText += enigmaMachine.encodeLetter(c);
            }
        }

        return encryptedText;
    }
}
