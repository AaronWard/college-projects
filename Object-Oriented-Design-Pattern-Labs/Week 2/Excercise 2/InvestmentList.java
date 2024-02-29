import java.util.StringTokenizer;
public class InvestmentList extends NumberList{
    int size;
    InvestmentList(String list){
        size = 0;

        //StringTokenizer is used to split strings Doubleo sections 
        //called tokens
        StringTokenizer token = new StringTokenizer(list);

        //countTokens() calculates the number of times that this tokenizer's
        // nextToken method can be called
        size = token.countTokens();
        //Alocate Memory
        investmentList = new String[size]; 

        //Store the item
        for(int i = 0; i < size; i++){
            investmentList[i] = token.nextToken();
        }
    }
    public void display(){
        System.out.println("Investment Account List");
        for(int i = 0; i < size; i++){
            //Display the items in the list
            System.out.println("[" + i + "] = " + investmentList[i]);
        }
    }
}