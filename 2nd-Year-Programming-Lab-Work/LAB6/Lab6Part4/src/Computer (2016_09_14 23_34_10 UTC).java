
public class Computer extends ElectronicDevice
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
	public Computer(String man, double d, double e, double f)
	{
		super(man);	
		speed = d;
		gigaBytes = e;
		inches = f;
	}
	
	/**
	 * Setters for the values in the objects
	 * @param s
	 */
	void setSpeed(double s)
	{
		this.speed = s;
	}
	
	void setMemory(double m)
	{
		this.gigaBytes = m;
	}
	
	void setSize(double i)
	{
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
	
	public double getMemory()
	{
		return gigaBytes;
	}
	public double getSize()
	{
		return inches;
	}
	
}

