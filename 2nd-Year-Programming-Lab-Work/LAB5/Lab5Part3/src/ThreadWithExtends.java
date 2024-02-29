/**
 * A modified version of lab5part1 that uses a synchronized block to
 * make sure the letters are printed in order
 * This class is executed by the TestThread class.
 * @author aaron
 *
 */
public class ThreadWithExtends extends Thread 
{
	//Static string array 
	static String [] array = {"A", "B", "C"};
	
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{ 	
			try{
				//Synchronize the array object so that the output
				//will always be "A" followed by "B" followed by "C"
				synchronized(array)
				{
					System.out.println(array[0]  + " " + getName());
					System.out.println(array[1]  + " " + getName());
					System.out.println(array[2]  + " " + getName());
					System.out.println("\n");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		}
		System.out.println("DONE");
		
	}
}