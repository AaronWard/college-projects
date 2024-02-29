
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
		speed = s;
		gigaBytes = gb;
		inches = i;
	}


	/**
	 * Returns value to the object created
	 * @return
	 */
	public double getSpeed()
	{
		return this.speed;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getMemory()
	{
		return this.gigaBytes;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getSize()
	{
		return this.inches;
	}
	
}
