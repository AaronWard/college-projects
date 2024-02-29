
/**
 * Thread class that implements the runnable interface
 * Array of ints printed 10 times with a for loop
 * @author aaron
 *
 */
public class ThreadWithRunnable implements Runnable
{
	//Static array of integers
	static int [] array = {1, 2, 3};
	
	private Thread thread;
	
	public void start()
	{
			thread = new Thread(this, "");
			thread.start();
	}
	
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{
			//Print the array with the thread ID to identify what thread
			//has been executed
				System.out.println(array[0] +" (THREAD ID: " +
									Thread.currentThread().getId() + ")");
				System.out.println(array[1] +" (THREAD ID: " + 
									Thread.currentThread().getId() + ")");
				System.out.println(array[2] +" (THREAD ID: " + 
									Thread.currentThread().getId() + ")");
				
				System.out.println("\n");
		}
	}
}
