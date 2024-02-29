
public class WalkieTalkie extends HandHeldDevice
{   
	
	private double rangeInKm;
	
	public WalkieTalkie(double weight, double ra)
	{
		super(weight, ra);
		
		this.rangeInKm = ra;
	}

	
	public double getRange()
	{
		return this.rangeInKm;
	}
}
