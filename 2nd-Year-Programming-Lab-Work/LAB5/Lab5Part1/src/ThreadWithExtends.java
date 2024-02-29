/**
 * A class that extends thread, and prints out a string array
 * with the letters "A" "B" and "C".
 * This class is executed by the TestThread class.
 * @author aaron
 *
 */
public class ThreadWithExtends extends Thread 
{
	//Static string array 
	static String [] array = {"A", "B", "C"};
	
	
	
	@Override 
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{ 	
			try{
				//Sleep for 1 second after each loop
				Thread.sleep(1000);	
				//get name to display the thread
				System.out.println(array[0]  + " " + getName());
				System.out.println(array[1]  + " " + getName());
				System.out.println(array[2]  + " " + getName());
				//System.out.println("\n");
			}
			catch(Exception e)
			{e.printStackTrace();}
		
		}
		System.out.println("DONE");
		
	}
}
