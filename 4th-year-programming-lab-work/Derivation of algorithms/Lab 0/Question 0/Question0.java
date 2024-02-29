/**
  Aaron Ward - B00079288
  Lab 0 - Question 0
 */

public class Question0 {

    public static void main(String [] args){

    /**
     * 2 arrays for the x an y value
     */
    boolean [] x = new boolean[] {true, true, false, false};
    boolean [] y = new boolean[] {true, false, true, false};

    System.out.println("--------- AND -----------");
    for(int i = 0; i < x.length; i++){
        System.out.println(x[i] && y[i]);
    }

    System.out.println("--------- OR -----------");
    for(int z = 0; z < x.length; z++){
        System.out.println(x[z] || y[z]);
    }

    System.out.println("--------- NOT -----------");
    for(int q = 0; q < x.length; q++){
        System.out.println(x[q] != true);
    }
  }
}