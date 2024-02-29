
public class MobilePhone extends HandHeldDevice
{
	private String networkName;
	
	public MobilePhone(double weight, String n)
	{
		super(weight);
		this.networkName = n;
	}



	public void setNetworkName(String n)
	{
		this.networkName = n;
	}

	public String getNetworkName()
	{
		return this.networkName;
	}
	
	
}
