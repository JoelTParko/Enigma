
public class Main
{
    public static void main(String args[])
    {
        //Test 1 and 2
        EnigmaMachine machine = new EnigmaMachine();
        machine.start();

        //Test 3
        EnigmaFile enigmaFile = new EnigmaFile("Test.txt");
        enigmaFile.test3();

        //Creates a bombe, and then runs the challenges specified in part 8 of the coursework
        Bombe bombe = new Bombe();
        bombe.challenge1();
        bombe.challenge2();
        bombe.challenge3();
    }
}
