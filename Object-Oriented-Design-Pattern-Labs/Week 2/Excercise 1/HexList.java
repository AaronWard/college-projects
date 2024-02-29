import java.util.StringTokenizer;
public class HexList extends NumberList{
    int size;
    HexList(String list){
        size = 0;

        //StringTokenizer is used to split strings Doubleo sections 
        //called tokens
        StringTokenizer token = new StringTokenizer(list);

        //countTokens() calculates the number of times that this tokenizer's
        // nextToken method can be called
        size = token.countTokens();
        //Alocate Memory
        hexList = new String[size]; 

        //Store the item
        for(int i = 0; i < size; i++){
            hexList[i] = token.nextToken();
        }
    }
    public void display(){
        System.out.println("Hex List");
        for(int i = 0; i < size; i++){
            //Display the items in the list
            System.out.println("[" + i + "] = " + hexList[i]);
        }
    }

    
    public void sum() {
		int n = 0;
		for(int i = 0; i < size; i++) {
		   n +=1;
		}
        System.out.println(n + " tokens in the string.");
	}
}