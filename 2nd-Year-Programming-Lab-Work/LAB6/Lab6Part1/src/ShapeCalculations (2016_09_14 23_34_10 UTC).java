
/**
 * A calculation class that returns the values to
 * the test class
 * @author aaron
 *
 */
public class ShapeCalculations
{
	//pi always stay the same
	final static double pi = 3.14159265;
	
	/**
	 * Takes the parameters and calculates the volume of
	 * a cylinder
	 * @param r
	 * @param h
	 * @return
	 */
	static double getVolumeOfCylinder(double r, double h)
	{
		double volume = (pi * r * r * h);
		
		return volume;
	}
	
	/**
	 * takes the paramter and calculates the volume of
	 * a cube
	 * @param v
	 * @return
	 */
	static double getVolumeOfCube(double v)
	{
		v += v * v * v;
		
		return v;
	}
	
	/**
	 * takes the parameters and calculates the area of
	 * a sphere.
	 * @param r
	 * @return
	 */
	static double getAreaOfSphere(double r)
	{
		double area = (4 * pi *r * r);
		
		return area;
	}
}
