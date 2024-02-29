
public class Computer
{
	//Declare private variables 
	private double speed;
	private double gigaBytes;
	private double inches;
	
	/**
	 * Constructor 
	 * @param s
	 * @param gb
	 * @param i
	 */
	public Computer(double s, double gb, double i)
	{
		this.speed = s;
		this.gigaBytes = gb;
		this.inches = i;
	}
	
	/**
	 * Returns value to the object created
	 * @return
	 */
	public double getSpeed()
	{
		return speed;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getMemory()
	{
		return gigaBytes;
	}
	public double getSize()
	{
		return inches;
	}
	
}
