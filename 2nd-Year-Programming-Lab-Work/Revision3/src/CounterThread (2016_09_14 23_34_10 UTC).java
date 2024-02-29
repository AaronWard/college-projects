import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A thread class that prints time to screen
 * @author aaron
 *
 */
public class CounterThread implements Runnable
{
	//Declare new thread
	private Thread thread;
	
	public void start()
	{
			thread = new Thread(this, "");
			thread.start();
	}

	@Override
	public void run()
	{	
		//Formatter used to display just time not date
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
			//never ending forloop to display time
			for(int i = 1; i > 0; i++)
			{
				try
				{  	
					//Sleep for 1 second after each loop
					Thread.sleep(1000);
					Calendar cal = Calendar.getInstance();
					
					System.out.println(dateFormat.format(cal.getTime()));
				}
				catch(Exception e)
				{
					e.printStackTrace();//throw new RuntimeException(e);
				}
			}
	}
}

