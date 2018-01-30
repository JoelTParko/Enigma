/*
This class is used solve the challenges set in part 8 of the coursework
It decodes messages given by the coursework using an enigma machine
 */

public class Bombe
{
    private static EnigmaMachine machine;

    //Creates an enigma machine that will be used by the bombe when solving the challenges
    public Bombe()
    {
        machine = new EnigmaMachine();
    }

    //Decodes the message found in challenge 1, using the settings given in the coursework
    public void challenge1()
    {
        char plug1 = 'D';
        char plug2 = 'S';
        String message = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
        String output = "";
        String keyPhrase = "ANSWER";

        System.out.println();
        System.out.println("CHALLENGE 1:");
        System.out.println();

        //Sets up the enigma machine with the settings needed to solve the challenge
        machine.clearPlugBoard();
        machine.addRotor(new BasicRotor("IV"), 0);
        machine.addRotor(new BasicRotor("III"), 1);
        machine.addRotor(new BasicRotor("II"), 2);
        machine.setPoisiton(0, 8);
        machine.setPoisiton(1, 4);
        machine.setPoisiton(2, 21);
        machine.addReflector(new Reflector("reflector1"));

        //This nested for loop decodes the encrypted string using all possible plug combinations
        //If the output contains the key phrase given by the coursework, then the output is printed to the console
        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 26; j++)
            {
                machine.addPlug(plug1, (char)(i+65));
                if(machine.addPlug(plug2, (char)(j+65)))
                {
                    output = encodeString(message);
                    if(output.contains(keyPhrase))
                    {
                        System.out.println(output);
                    }
                }
                machine.clearPlugBoard();
                machine.setPoisiton(0 , 8);
                machine.setPoisiton(1, 4);
                machine.setPoisiton(2, 21);
            }
        }
    }

    //Decodes the message found in challenge 2, using the settings given in the coursework
    public void challenge2()
    {
        String message = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
        String output = "";
        String keyPhrase = "ELECTRIC";
        System.out.println();
        System.out.println("CHALLENGE 2:");
        System.out.println();

        //Sets up the enigma machine with the settings needed to solve the challenge
        machine.clearPlugBoard();
        machine.addPlug('H', 'L');
        machine.addPlug('G', 'P');
        machine.addRotor(new BasicRotor("V"), 0);
        machine.addRotor(new BasicRotor("III"), 1);
        machine.addRotor(new BasicRotor("II"), 2);
        machine.addReflector(new Reflector("reflector1"));

        //This nested for loop will decode the encrypted string using all possible rotor positions
        //If the decrypted messages contains the key phrase given in the coursework, it is printed to the console
        for (int i = 0; i < 25; i++)
        {
            machine.setPoisiton(0, i);
            for (int j = 0; j < 25; j++)
            {
                machine.setPoisiton(1, j);
                for (int k = 0; k < 25; k++)
                {
                    machine.setPoisiton(2, k);
                    output = encodeString(message);
                    if(output.contains(keyPhrase))
                    {
                        System.out.println(output);
                    }
                }
            }
        }
    }

    //Decodes the message found in challenge 3, using the settings given in the coursework
    public void challenge3()
    {
        String message = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
        String keyPhrase = "JAVA";
        String[] rotorNames = {"I", "II", "III", "IV", "V"};
        String output;

        System.out.println();
        System.out.println("Challenge 3:");
        System.out.println();

        //Puts the required plugs into the plug board
        machine.clearPlugBoard();
        machine.addPlug('M', 'F');
        machine.addPlug('O', 'I');

        //This nested for loop will decode the encrypted string using all possible combinations of rotor types
        //If the decrypted message contains the key phrase, it is printed to the console
        for (int i = 0; i < rotorNames.length; i++)
        {
            machine.addRotor(new BasicRotor(rotorNames[i]), 0);
            for (int j = 0; j < rotorNames.length; j++)
            {
                if(j != i)
                {
                    machine.addRotor(new BasicRotor(rotorNames[j]), 1);
                    for (int k = 0; k < rotorNames.length; k++)
                    {
                        if (k != i || k != j)
                        {
                            machine.addRotor(new BasicRotor(rotorNames[k]), 2);

                            //Gives the rotors the rotor positions specified in the coursework
                            machine.setPoisiton(0, 22);
                            machine.setPoisiton(1, 24);
                            machine.setPoisiton(2, 23);
                            output = encodeString(message);
                            if (output.contains(keyPhrase))
                            {
                                System.out.println(output);
                            }
                        }
                    }
                }
            }
        }
    }

    //This method takes an input string, and sends it through the enigma machine
    //The output is then returned by the method
    public String encodeString(String input)
    {
        String output = "";
        for (char c: input.toCharArray())
        {
            output += machine.encodeLetter(c);
        }
        return output;
    }
}
