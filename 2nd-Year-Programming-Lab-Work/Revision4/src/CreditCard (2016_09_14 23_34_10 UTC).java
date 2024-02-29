
/**
 * An abstract CreditCard Class
 * @author aaron
 *
 */
public abstract class CreditCard
{
	//Properties initialised
	private String nameOnCard ="Aaron Ward";
	private String expiryDate ="2019";
	private String dateOfIssue="2014";
	private int cvnNumber = 123;
	
	public static void main(String [] arg)
	{
		
	}
	
	/**
	 * 
	 * Constructor the only takes one parameter
	 * @param nameOnCard
	 * @return 
	 */
	public CreditCard(String nameOnCard)
	{
		this.nameOnCard = nameOnCard;
	}
	
	/**
	 * overloaded constuctor that takes two parameters
	 * @param expiryDate
	 * @param dateOfIssue
	 */
	
	public CreditCard(String expiryDate, String dateOfIssue)
	{
		this.expiryDate = expiryDate;
		this.dateOfIssue = dateOfIssue;
		
	}
	
	@overload
	public CreditCard(String dateOfIssue, int cvnNumber)
	{
		this.dateOfIssue = dateOfIssue;
		this.cvnNumber = cvnNumber;
	}
	
	/**
	 * Getters to return the values to the tester class
	 * @return
	 */
	public String getNameOnCard()
	{
		return this.nameOnCard;
	}

	public String getExpiryDate()
	{
		return this.expiryDate;
	}

	public String getDateOfIssue()
	{
		return this.dateOfIssue;
	}

	public int getCvnNumber()
	{
		return this.cvnNumber;
	}
}
