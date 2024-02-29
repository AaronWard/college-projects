/**
 * This is a class to represent a Sample Solar system limit exception
 * The Sun class is a Singleton and the number of Sun objects cannot exceed max of Solar System
 * This Exception represents the condition where the limit is exceeded
 */
public class SolarSystemSunLimitException extends Exception {
	public SolarSystemSunLimitException(String message) {
		super(message);
	}
}
