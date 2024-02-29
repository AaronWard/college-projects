/**
    Aaron ward - B00079288
    lab 0 - question 2

    output the product of the elements in an array
 */
public class Question2{
    public static void main(String [] args){

    // set product at value of 1 to prevent multiplication of 0
    int product = 1;
    final int N = 4;

    int [] f = new int [N];
    f[0] = 2;
    f[1] = 2;
    f[2] = 2;
    f[3] = 2;
    
    for(int i = 0; i < f.length; i++){
        product *= f[i];
    }
    System.out.println(product);
  }
}