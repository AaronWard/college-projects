
/**
 * A class that has 3 method, to calculate the area of a sphere, volume of
 * a cube and the volume of cylinder, using instance variables
 * @author aaron
 *
 */
public class TestClass
{
	
	@SuppressWarnings("static-access")
	public static void main(String [] arg)
	{
		//New object of ShapeCalculations is created
		ShapeCalculations s = new ShapeCalculations();
		int a = 1;
		
		//The radius is passed to the method and a value is returned
		double area = s.getAreaOfSphere(a);
		System.out.println("Area Of Sphere: " + area + " units \n");
		
		//Passes radius to the method and gets the volume
		double volume = s.getVolumeOfCube(++a);
		System.out.println("Area of cube: " + volume + " units \n");

		//passes radius and hieght and recieves volume from the method
		double volumeC = s.getVolumeOfCylinder(a, a);
		System.out.println("Voume Of Cyclinder: " + volumeC + " units \n" );
	}
}
