
/**
 * Tester class for 3 
 * @author aaron
 *
 */
public class CreditCardTester extends CreditCard
{
	/**
	 * 
	 * @param dateOfIssue
	 * @param cvnNumber
	 */
	public CreditCardTester(String dateOfIssue, int cvnNumber)
	{
		super(dateOfIssue, cvnNumber);
	}

	public CreditCardTester(String expiryDate, String dateOfIssue)
	{
		super(expiryDate, dateOfIssue);
	}

	public CreditCardTester(String nameOnCard)
	{
		super(nameOnCard);
		System.out.println(super.getNameOnCard());
	}

}
