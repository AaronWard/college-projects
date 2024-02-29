/**
 * A method that is purposly coded to catch 3 expcetions. ArrayIndexOutOfBoundsException
 * NumberFormatException and a general Exception
 * 
 * 
 * @author aaron
 * @date 2-3-16
 * @group 4
 * 
 */
public class TestMultipleCatches
{
	/**
	 * 
	 * @param agrs
	 */
	public static void main(String [] agrs)
	{		
		//declare Array and variables
		int [] array = {1, 2, 3, 4};
		String s = "2" ;
		
		//try this block
		try
		{
			System.out.println(array[5]);
			
			//ParseInt changes a string to an integer 
			 int i = Integer.parseInt(s);
			System.out.println(i);
						
		}
		
		//These catch blocks is purposely called
		catch(ArrayIndexOutOfBoundsException e)
		{		
			System.out.println("1) array out of bounds pal");
			e.printStackTrace();
		}
		

		catch(NumberFormatException e)
		{
			System.out.println("\n 2) Number Format Exception Pal");
					
			e.printStackTrace();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
