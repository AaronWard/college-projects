/**
 *
 *
 *
 * This is an Abstract class to represent the super class of all objects in the Solar System
 */

public abstract class CelestialBody {

	private double mass;
	protected double diameter;
	private String name;

	/**
	 * Abstract accessor method to retrieve object name
	 *
	 * @return String return the name of the object
	 */

	public abstract String getName();

}
