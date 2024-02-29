/**
 * 
 * A Class that purposly catches a NullPointerException
 * and an ArithmeticException
 * @author aaron
 * @group 4
 * @year 2
 * @date 8-3-16
 */

public class NestedTryCatch
{
	/**
	 * @param args
	 */
	@SuppressWarnings("null")
	public static void main(String [] args)
	{
		//outer Try
		try
		{
			String string = null;
			string.toString(); //Catch will be purposely called because string is set to null
		}
		
		//outer catch
		catch(NullPointerException e)
		{
			System.out.println("outter catch block\n{");
			
			//inner try
			try
			{
				System.out.println("	Inner try block\n	{");
	            int x = 1/0;
	            System.out.println(x); //catch will be called because 1 can't be divided by 0
			}
			
			//inner catch
			catch(ArithmeticException e2)
			{
				System.out.println("		inside catch block\n		{");
				System.out.println("		 1 can not be divided by zero\n		 getMessage: " + e2.getMessage());
				
			}
		} //end of outer catch
	}	
}
