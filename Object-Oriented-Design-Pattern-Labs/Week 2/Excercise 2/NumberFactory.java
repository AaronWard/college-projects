/**
 * Factory class has no constructor or attributes
*/

public class NumberFactory{
    public NumberList getNumberList(String list) {

        if(list.charAt(0) == 'C'){
            return new CurrentList(list);
        }
        else{
            return new InvestmentList(list);
        }
    }
}