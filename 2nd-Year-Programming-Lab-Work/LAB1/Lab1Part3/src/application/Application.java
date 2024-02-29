/**
 * @author aaron - B00079288
 * @Group 4 
 * @year 2 - semester 2
 * 
 */

package application;

import Mathematics.*;	//Package is imported
import java.util.Scanner;

/**
 * A maths helper class with a package import. used to calculate the numbers
 * entered by the user.
 * @author aaron
 *
 */
public class Application
{

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		//MathHelper instance is made
		MathHelper maths = new MathHelper();
		Scanner input = new Scanner(System.in);
		
		//Take in the numbers from the user.
		System.out.println("Enter the first number");
		int x = input.nextInt();
		System.out.println("\nEnter the second Number");
		int y = input.nextInt();
		
		input.close();


		//two ints are passed to the multiplyNums method
		int result = maths.multiplyNums(x, y); 
		System.out.println("\nResult: " + result);
	}
}
