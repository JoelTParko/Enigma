public class Plug {

    private char end1;
    private char end2;

    //Sets the values of end1 and end2 when the a plug object is instantiated
    public Plug(char end1, char end2)
    {
        this.end1 = end1;
        this.end2 = end2;
    }

    //Sets the value of end1 to the value of the parameter
    public void setEnd1(char end1)
    {
        this.end1 = end1;
    }

    //Sets the value of end2 to the value of the parameter
    public void setEnd2(char end2)
    {
        this.end2 = end2;
    }

    //Returns the value of end1
    public char getEnd1()
    {
        return end1;
    }

    //Returns the value of end2
    public char getEnd2()
    {
        return end2;
    }

    //Encodes a given char by checking if the char matches end1 or end2
    public char encode(char letterIn)
    {
        if(letterIn == end1){
            return end2;
        }else if(letterIn == end2){
            return end1;
        }else{
            return letterIn;
        }
    }

    //Checks if the given plug matches this plug
    public boolean clashesWith(Plug plugin){
        if(plugin.getEnd1() == end1 || plugin.getEnd2() == end2){
            return true;
        }
        return false;
    }

}
