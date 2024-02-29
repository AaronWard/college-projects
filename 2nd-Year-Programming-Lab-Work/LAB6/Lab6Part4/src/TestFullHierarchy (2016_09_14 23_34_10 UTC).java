import java.util.Vector;

public class TestFullHierarchy
{

	public static void main(String[] args)
	{
		//Vector for electronic devices
		Vector <ElectronicDevice> list = new Vector<ElectronicDevice>();
		
		
		Computer c1 = new Computer();
		HandHeldDevice h1 = new HandHeldDevice();
		LaptopComputer l1 = new LaptopComputer();
		MobilePhone m1 = new MobilePhone();
		
		
		list.addElement(c1);
		list.addElement(h1);
		list.addElement(l1);
		list.addElement(m1);
		
		
		
		
	}

}
