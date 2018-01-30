import java.util.ArrayList;

public class Plugboard
{
    private ArrayList<Plug> plugs = new ArrayList(13);

    //Attempts to add a new plug to the plug board
    public boolean addPlug(char end1, char end2)
    {
        Plug newPlug = new Plug(end1, end2);

        //Checks each the new plug against all the current plugs to spot any clashes
        for (Plug currentPlug: plugs)
        {
            if(currentPlug.clashesWith(newPlug))
            {
                return false;
            }
        }

        plugs.add(newPlug);
        return  true;

    }

    //Returns the size of the plugs ArrayList
    public int getNumPlugs()
    {
        return plugs.size();
    }

    //Clears the plugs ArrayList
    public void clear()
    {
        plugs.clear();
    }

    //Sends the passed char through every plug in the plug board
    public char substitute(char plainChar)
    {
        for (Plug currentPlug: plugs)
        {
            plainChar = currentPlug.encode(plainChar);
        }
        return plainChar;
    }
}
