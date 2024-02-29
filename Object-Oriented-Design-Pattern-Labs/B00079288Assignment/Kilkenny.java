/**
* A concrete factory class for Kilkenny
*/
public class Kilkenny extends App{
    public Town getTown(){
        return new Town("Kilkenny");
    }
    public Restaurant getRestaurant(){
        return new Restaurant("Ristorante Rinuccini\nCampagne\nRive Gauche\nAnocht\nZuni");
    }
    public Hotel getHotel(){
        return new Hotel("Kilkenny Ormond Hotel\nNewpark Hotel\nRiver Court\nBoutique Hotel\nClub House Hotel");
    }
    public Place getPlace(){
        return new Place("St. Canices Cathedral\nKilkenny Castle\nBlack Abbey\nCapuchin Church");
    }

    public Description getDescription(){
        return new Description("Kilkenny city is Ireland's most historic and compact medieval city. The use of the term city is a point of some dispute. Kilkenny's inhabitants claim its city status because of a medieval charter it received 800 years ago. Kilkenny's centre is based on the west bank of the River Nore around Parliament Street, Hight Street and the Parade, with Rothe House at one end and Kilkenny Castle on the hill at the other.");
    }
}