
import java.util.logging.*;
import java.io.IOException;

/**
 * A class the purposly catchs an exception and logs the message to an
 * external log using the (e.getMessage())
 * @author aaron
 *
 */
public class Revision1
{
	

	/**
	 * 
	 * @param args
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void main(String [] args) throws SecurityException, IOException
	{
		int [] array = new int [3];
		Logger logger = Logger.getLogger("MyLogFile");
		FileHandler fileHandler;
		
		try{
			array[4] = 22;
		}
		
		//exception it caught and logged to the MyLogFile.log
		catch(ArrayIndexOutOfBoundsException e)
		{
			fileHandler = new FileHandler("./MyLogFile.log", 0, 1, true);
            logger.addHandler(fileHandler);
           // logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
  
            // the following statement is used to log any messages
            logger.info(" " + e.getMessage());
		}
	}
}