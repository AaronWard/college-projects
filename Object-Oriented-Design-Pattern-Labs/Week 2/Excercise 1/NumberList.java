public abstract class NumberList{

    protected int [] intList;
    protected double [] doubleList;
    protected char [] alphabeticalList;
    protected String [] hexList;
    NumberList(){
        intList = null;
        doubleList = null;
        alphabeticalList = null;
        hexList = null;
    }

    public int[] getIntList(){
        return intList;
    }
    public double[] getDoubleList(){
        return doubleList;
    }
    public char[] getAlphabeticalList(){
        return alphabeticalList;
    }
    public String[] getHexList(){
        return hexList;
    }
    public void display(){}

    public void sum(){}
}