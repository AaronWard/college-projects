/**
    Simple program that solve propositions and return a true or false statement
 */

public class Question1{
    public static void main(String [] args){

        System.out.println(3 % 2 == 0);
        System.out.println(10 > 4);
        System.out.println(21 + (-3) > 7);
        System.out.println("\n");

         boolean A = true;
         boolean B = false;
         boolean C = true;

         System.out.println((A && B) || C);
         System.out.println(!((A && B) || C));
         System.out.println(((C || C) || (A && !(C))) || (B && C));
    }
}