import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class that displays 3 local senstive pieces of information.
 * like date format, currency and days of the week.
 * @author aaron
 *
 */
public class PrintInternationalData
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args)
	{	
		//Variables initialised and Creat Locale objects
		Date today = new Date();
		String dateOut1, dateOut2, dateOut3;

		Locale L1 = new Locale("et", "ET");
		Locale L2 = new Locale("de", "DE");
		Locale L3 = new Locale("cs", "CS");
		
		
		/**
		 * Create and display dates for the 3 locale objects
		 * created
		 */
		System.out.println("******DATES******");
		
		DateFormat dateFormatter1 = DateFormat.getDateInstance(DateFormat.DEFAULT, L1);
		DateFormat dateFormatter2 = DateFormat.getDateInstance(DateFormat.DEFAULT, L2);
		DateFormat dateFormatter3 = DateFormat.getDateInstance(DateFormat.DEFAULT, L3);
	
		dateOut1 = dateFormatter1.format(today);
		dateOut2 = dateFormatter2.format(today);
		dateOut3 = dateFormatter3.format(today);
		
		System.out.println("ESTONIA: " + dateOut1 + " " + L1.toString());
		System.out.println("GERMANY: " + dateOut2 + " " + L2.toString());
		System.out.println("CZECH  : " + dateOut3 + "  " + L3.toString() + "\n");
		
		
		/**
		 * Display the days of the week for each locale
		 */
		System.out.println("*****WEEKDAYS*****");
		
		DateFormatSymbols daysOne = new DateFormatSymbols(L1);
		DateFormatSymbols daysTwo = new DateFormatSymbols(L2);
		DateFormatSymbols daysThree = new DateFormatSymbols(L3);

	    String [] dayArray1 = daysOne.getWeekdays();
	    String [] dayArray2	= daysTwo.getWeekdays();
	    String [] dayArray3 = daysThree.getWeekdays();
	    
	    //For loop to print weekday arrays
	    for(int i = 0; i < 8; i++)
	    {
	    	System.out.println(dayArray1[i] + " "+ dayArray2[i] + " "+ dayArray3[i]);
	    }
		
		/**
		 * Create and display time for the 3 locale objects
		 */
		System.out.println("\n*****CURRENCY*****");
		
		NumberFormat currencyOne   = NumberFormat.getCurrencyInstance(L1);
		NumberFormat currencyTwo   = NumberFormat.getCurrencyInstance(L2);
		NumberFormat currencyThree = NumberFormat.getCurrencyInstance(L3);

		//Display the currencies of the locales with the value of 2.50 units
		System.out.println(currencyOne.format(2.50));
		System.out.println(currencyTwo.format(2.50));
		System.out.println(currencyThree.format(2.50));
	}
}
