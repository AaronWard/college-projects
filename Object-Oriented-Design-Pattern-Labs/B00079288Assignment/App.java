/**
 * Abstract Factory class that the town classes will 
 * inherit their method from.
 *
 * This allows the deferation of instantiation to the concrete classes
 */
public abstract class App{
    public abstract Town getTown();
    public abstract Restaurant getRestaurant();
    public abstract Hotel getHotel();
    public abstract Place getPlace();
    public abstract Description getDescription();
}