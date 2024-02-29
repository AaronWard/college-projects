import java.util.Scanner;

class DFATest {

    //
    // This program will ask the user to continually enter words and
    // check if the variation is considered a valid input
    // Entry ceases when the user enters zero.
    //

    public static void main (String [] args){
    	Scanner Keyboard = new Scanner(System.in);
		try {
//			DFA dfa = new DFA("dfaPhone.txt");
			
//			DFA dfa = new DFA("C:/Users/aaron/workspace/DFA/src/sheep.txt");
			DFA dfa = new DFA("C:/Users/aaron/workspace/DFA/src/dfaPhone.txt");

			
            String str; boolean b;
			do{

		   	   System.out.println("Enter your Mobile Phone Number in the form (087/6/5)-7/8XXXXXX : ");
  	   	       System.out.println("Don't forget the hyphen:  ");
  	   	       System.out.println("________________________ ");

//
//	   	       System.out.println("Enter sheep talk: ");

			   str = Keyboard.nextLine();
		   	   b = dfa.parseInput(str);

               System.out.println("End states: ");
			   dfa.printEndStates();

			   if (b)
			    	System.out.println("Valid phone number");

//			    	System.out.println("---valid sheep talk---");
			   else
			    	System.out.println("invalid phone number");

//			    	System.out.println("invalid sheep talk");
	        }while (!str.equals("0"));
		} catch (Exception e){
//	    	System.out.println("invalid sheep talk");
	    	System.out.println("invalid number");

			e.printStackTrace();
		}
	}
}