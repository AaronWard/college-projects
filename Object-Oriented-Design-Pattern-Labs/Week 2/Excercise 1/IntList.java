import java.util.StringTokenizer;

public class IntList extends NumberList{
    int size;
    IntList(String list){
        size = 0;

        //StringTokenizer is used to split strings into sections 
        //called tokens
        StringTokenizer token = new StringTokenizer(list);

        //countTokens() calculates the number of times that this tokenizer's
        // nextToken method can be called
        size = token.countTokens();
        //Alocate Memory
        intList = new int[size]; 

        //Store the item
        for(int i = 0; i < size; i++){
            intList[i] = Integer.parseInt(token.nextToken());
        }
    }


    public void sum() {
		int n = 0;
		for(int i = 0; i < size; i++) {
		   n +=1;
		}
        System.out.println(n + " tokens in the string.");
	}

    public void display(){
        System.out.println("Integer List");
        for(int i = 0; i < size; i++){
            //Display the items in the list
            System.out.println("[" + i + "] = " + intList[i]);
        }
    }
}