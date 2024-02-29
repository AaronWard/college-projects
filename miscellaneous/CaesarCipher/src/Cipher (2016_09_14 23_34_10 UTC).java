
import java.util.Scanner;
import java.lang.*;


public class Cipher
{

	public static void main(String[] args)
	{		
		Scanner input = new Scanner(System.in);
		System.out.println("Write a word to be ciphered: " );
		String word = input.nextLine();
		
		System.out.println("How many characters for the key?: " );
		int key = input.nextInt();

		//Invoke ciphering method
		cipherMethod(word, key);
		
		input.close();
	}
	
	//This method uses string builder to loop trough every character
	//and add as many alphabetical numbers to the char as 
	//specified by the user.
	public static void cipherMethod(String word, int key)
	{
		//Create String Builder
		StringBuilder sb = new StringBuilder();

		//For loop to loop trough the char array
	    for(char c:word.toCharArray())
	    {
	    	c+=key;
		    sb.append(c);	
	    }
	    //replace the "!" characters with a space
	    System.out.print("\nCiphered word is: " + sb.toString().replaceAll("!", " "));	    
	}		
}


