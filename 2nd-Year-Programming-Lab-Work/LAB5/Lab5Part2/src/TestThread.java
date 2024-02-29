/**
 * Test class to execute the threads in the class
 * which implements the runnable interface
 * @author aaron
 *
 */
public class TestThread
{
	public static void main(String[] args)
	{
		new ThreadWithRunnable().start();
		new ThreadWithRunnable().start();
	}

}
