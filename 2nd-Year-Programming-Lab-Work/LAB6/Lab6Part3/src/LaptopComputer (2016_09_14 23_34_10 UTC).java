
/**
 * use extends to inherit from the computer class
 * @author aaron
 *
 */
public class LaptopComputer extends Computer
{	
	private double batteryLife;

	/**
	 * inherits from the computer class
	 * @param s
	 * @param gb
	 * @param i
	 */
	public LaptopComputer(double s, double gb, double i)
	{
		//super class
		super(s, gb, i);
	}
	
	//Extra attribute (battery hours) for this class
	public void setBatteryLife(double b)
	{
		this.batteryLife = b;
	}
	
	/**
	 * Returns the hours to the object
	 * @return double
	 */
	public double getBatteryLife()
	{
		return this.batteryLife;
	}
	

}
