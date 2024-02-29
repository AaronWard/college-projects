import java.util.StringTokenizer;
public class CurrentList extends NumberList{
    int size;
    CurrentList(String list){
        size = 0;

        //StringTokenizer is used to split strings Doubleo sections 
        //called tokens
        StringTokenizer token = new StringTokenizer(list);

        //countTokens() calculates the number of times that this tokenizer's
        // nextToken method can be called
        size = token.countTokens();
        //Alocate Memory
        currentList = new String[size]; 

        //Store the item
        for(int i = 0; i < size; i++){
            currentList[i] = token.nextToken();
        }
    }
    public void display(){
        System.out.println("Current Accout List");
        for(int i = 0; i < size; i++){
            //Display the items in the list
            System.out.println("[" + i + "] = " + currentList[i]);
        }
    }
}