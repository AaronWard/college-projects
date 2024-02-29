
import java.io.*;
import java.util.Scanner;

/**
 * A class that reverses a string that is entered by the user and catches
 * a null pointer exception.
 * An open file method is then called that throws an IO Exception.
 * 
 * 
 * @author aaron
 * @date 2-3-16
 * @group 4
 *
 */
public class TestDeclaringExceptions
{	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String s = null;
		
		System.out.println("Enter a string");
		s = input.nextLine();

		String stringReversed = reverseString(s);
		
		try{
		System.out.println(stringReversed);
		}
		
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}

		/*****************/
		
		
		System.out.println("\nEnter A File Name");
		String fileName = input.nextLine();
		
		
		//There is a try and catch around the openFile method
		//because the throws IOException is unchecked
		try
		{
			boolean fileExists = openFile(fileName);
			System.out.println(fileExists);
		}
		
		//catch the exceptions
		catch(IOException e)
		{		}

		input.close();
	}
	
	/**
	 * Method to reverse the string that has been entered by 
	 * the user.
	 * @param s 
	 * @return s
	 * @throws NullPointerException
	 */
	public static String reverseString(String s) throws NullPointerException
	{

		//StringBuilder reverses the string.
		s = new StringBuilder(s).reverse().toString();
		return s;
	}
	
	/**
	 * I Method used to open a file
	 * @param fileName
	 * @return boolean fileExists
	 * @throws IOException
	 */
	public static boolean openFile(String fileName) throws IOException
	{
		File dir = new File("./Documents/");
        dir.mkdirs();
		
		File file = new File(dir, fileName + ".txt");
		
		//displays true or false if the file exists
		boolean fileExists = file.exists();
	
		return fileExists;
	}
}
