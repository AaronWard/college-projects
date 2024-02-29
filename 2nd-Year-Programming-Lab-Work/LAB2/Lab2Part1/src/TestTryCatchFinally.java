/**
 * A class to test a Try, Catch and Finally block
 * 
 * @author Aaron Ward - b00079288
 * @Year 2 - Semester 2
 * @group 4
 */

public class TestTryCatchFinally
{
	/**
	 * 
	 * @param args
	 */
	@SuppressWarnings("null")
	public static void main(String[] args)
	{
		try
		{
			String string = null;
			System.out.println("*** 1 ***\n");
			
			//catch is purposely executed because string is null
			string.toString();
			
		}
		
		//pass the string to the catch block
		catch(NullPointerException e)
		{
			System.out.println("\n*** 2 ***\n");
			
			//This is used to show where the bug occurred in the program
			e.printStackTrace();
		}
		
		//This block is executed regardless of what outcome
		finally
		{
			System.out.println("\n*** 3 ***\n");
			System.out.println("This is the finally block executing");
		}
	}
}
