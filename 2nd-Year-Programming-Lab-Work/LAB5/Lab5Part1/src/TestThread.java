/**
 * This class will run 2 Threads at the same time in the
 * main method. 
 * @author aaron
 *
 */
public class TestThread
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args)
	{	
		new ThreadWithExtends().start();
		new ThreadWithExtends().start();
	}
}
