/**
    Aaron Ward - B00079288
    Lab 0 - Question 4

    Return the index of the largest element
 */

public class Question4{
    public static void main(String [] args){
        int largest = 0;
        int index = 0;
        int [] array = new int [] {3, 4, 2, 55, 1, 22};

        for(int i = 0; i < array.length; i++){
            //Check if the current index has a greater
            //value than the largest known index.
            if(array[i] > largest){
                largest = array[i];
                index = i;
            }
        }
        System.out.println("Index of largest element: " + index);
    }
}