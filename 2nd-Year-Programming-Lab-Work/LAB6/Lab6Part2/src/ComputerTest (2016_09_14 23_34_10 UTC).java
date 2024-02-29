
public class ComputerTest
{

	public static void main(String[] args)
	{
		//3 objects are made
		Computer c1 = new Computer(3.0, 8.0, 29);
		Computer c2 = new Computer(2.8, 7.0, 20);
		Computer c3 = new Computer(2.5, 4.0, 25);

		//Print out the data from the computer class
		System.out.println("** Object 1 **");
		System.out.println(c1.getMemory() + " GB");
		System.out.println(c1.getSize() + " inches");
		System.out.println(c1.getSpeed() + " GHZ\n");
		
		/************************/
		
		System.out.println("** Object 2 **");
		System.out.println(c2.getMemory() + " GB");
		System.out.println(c2.getSize() + " inches");
		System.out.println(c2.getSpeed() + " GHZ\n");
		

		/***********************/
		
		System.out.println("** Object 3 **");
		System.out.println(c3.getMemory() + " GB");
		System.out.println(c3.getSize() + " inches");
		System.out.println(c3.getSpeed() + " GHZ\n");	
	}
}
