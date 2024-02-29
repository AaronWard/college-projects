
public abstract class HandHeldDevice extends ElectronicDevice
{
	private double weight;
	
	public HandHeldDevice(String man, double w)
	{
		super(man);
		this.weight = w;
	}


	public double getWeight()
	{
		return this.weight;
	}
	
}
