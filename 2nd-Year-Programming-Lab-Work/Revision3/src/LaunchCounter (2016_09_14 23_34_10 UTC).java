
/**
 * a class that runs the counter thread to display the time
 * @author aaron
 *
 */
public class LaunchCounter
{

	public static void main(String[] args) throws InterruptedException
	{
		//Runs the CounterThread
		new CounterThread().start();
	}
}
