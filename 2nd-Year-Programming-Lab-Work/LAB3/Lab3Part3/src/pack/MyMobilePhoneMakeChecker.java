package pack;

import java.util.Scanner;
import pack.NotMyPhoneMakeException;

/**
 * 
 * @author aaron
 * @group 4
 * @date 10-3-16
 */
public class MyMobilePhoneMakeChecker
{	
	
	/**
	 * Main method that asks the user to enter in their phone
	 * make. and throws a custom made exception if it is incorrect
	 * @param args
	 * @throws NotMyPhoneMakeException
	 */
	public static void main(String [] args) throws NotMyPhoneMakeException 
	{

		Scanner input = new Scanner(System.in);
		System.out.println("Enter in your phone model?");
		String myPhone = input.nextLine();
		
		checkMyMobilePhoneMake(myPhone);
				
		input.close();
	}
	
	/**
	 * Method to check if the input matches the make
	 * of my phone
	 * @param inputPhoneMake
	 * @throws NotMyPhoneMakeException
	 */
	public static void checkMyMobilePhoneMake(String inputPhoneMake) throws NotMyPhoneMakeException
	{
		//My real phone makke
		String myPhoneMake = ("Iphone");
		
		//If the user enters it correctly
		if(inputPhoneMake.equalsIgnoreCase(myPhoneMake))
		{
			System.out.println(inputPhoneMake + " is your phone");
		}
		
		//If it is incorrect
		else
		{
			throw new NotMyPhoneMakeException();
		}
	}
}


@SuppressWarnings("serial")
/**
 * A custom made exception to handle when a user
 * enters the incorrect phone make
 * @author aaron
 *
 */
class NotMyPhoneMakeException extends Exception
{
	public NotMyPhoneMakeException()
	{
		System.out.println("Not the correct phone make.");
		
		//suppresses the red writing in the console
		fillInStackTrace();
	} 
	
	public Throwable fillInStackTrace() {
        return null;
    }  
}

