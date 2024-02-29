
public abstract class ElectronicDevice
{
	protected static String manufacturer;
	
	@SuppressWarnings("static-access")
	public ElectronicDevice(String man)
	{
		this.manufacturer=man;
	}
	
	public String getManufacturer()
	{
		return manufacturer;
	}
	
}
