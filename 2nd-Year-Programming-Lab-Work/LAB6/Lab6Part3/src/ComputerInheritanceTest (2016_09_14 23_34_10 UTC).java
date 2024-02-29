
/**
 * A class that tests the objects and the subclass objects
 * @author aaron
 *
 */
public class ComputerInheritanceTest
{
	public static void main(String [] args)
	{
		//Create 2 super class objects
		Computer c1 = new Computer(2.2, 8.1, 20);
		Computer c2 = new Computer(1.2, 4, 22);
		
		
		//Print out the data from the computer class
		System.out.println("** Object 1 **");
		System.out.println(c1.getMemory() + " GB");
		System.out.println(c1.getSize() + " inches");
		System.out.println(c1.getSpeed() + " GHZ\n");
		
		
		System.out.println("** Object 2 **");
		System.out.println(c2.getMemory() + " GB");
		System.out.println(c2.getSize() + " inches");
		System.out.println(c2.getSpeed() + " GHZ\n");
		
		
		/**********************************************/
		
		//Create 2 subclass objects that extend the super class
		LaptopComputer lc1 = new LaptopComputer(2.2, 8.1, 20);
		lc1.setBatteryLife(10.2);
		LaptopComputer lc2 = new LaptopComputer(2.5, 6, 18);
		lc2.setBatteryLife(10.2);
		
		System.out.println("** Subclass Object 1 **");
		System.out.println(lc1.getMemory() + " GB");
		System.out.println(lc1.getSize() + " inches");
		System.out.println(lc1.getSpeed() + " GHZ");
		System.out.println(lc1.getBatteryLife() + " Hours battery life\n");

		
		System.out.println("** Subclass Object 1 **");
		System.out.println(lc2.getMemory() + " GB");
		System.out.println(lc2.getSize() + " inches");
		System.out.println(lc2.getSpeed() + " GHZ");
		System.out.println(lc2.getBatteryLife() + " Hours battery life\n");
		
	}
}
