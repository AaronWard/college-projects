/**
 * Factory class has no constructor or attributes
*/

public class NumberFactory{
    public NumberList getNumberList(String list, String type) {


        if(type == "int"){
            return new IntList(list);
        }
        else if(type == "double"){
            return new DoubleList(list);
        }
        else if(type == "char"){
            return new AlphabeticalList(list);
        }
        else {
            return new HexList(list);
        }
    
    
    }
}