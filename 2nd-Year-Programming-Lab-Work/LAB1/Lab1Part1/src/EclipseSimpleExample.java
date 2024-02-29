/**
 * 
 * @author Aaron Ward - B00079288
 * @Group 4
 * @Year 2 - Semester 2
 * Lab1Part1
 * 
 *This class is used to display the String as a jar file
 */

public class EclipseSimpleExample
{	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String str = ("Hello, This is a Jar File");
		
		//parameters are passed to the method
		printGreeting(str);
	}
	
	/**
	 * Method to print out the string
	 * @param str
	 */
	public static void printGreeting(String str)
	{
		//prints the string to the console
		System.out.println(str);
	}
}
