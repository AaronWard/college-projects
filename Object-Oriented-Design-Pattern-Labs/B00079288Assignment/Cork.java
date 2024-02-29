/**
* A concrete factory class for Cork
*/
public class Cork extends App{
    public Town getTown(){
        return new Town("Cork");
    }
    public Restaurant getRestaurant(){
        return new Restaurant("Liberty Grill\nMarket Lane\n"
        +"Greenes Restaurant\nQuinlans Seafood Bar\nGourmet Burger");
    }
    public Hotel getHotel(){
        return new Hotel("Jurys Inn Cork\nTravelodge\nCork Airport Hotel\nMaldron Hotel");
    }
    public Place getPlace(){
        return new Place("Crawford Art gallery\nFota Island\nBlarney Castle\nCamden Fort\nCobh");
    }

    public Description getDescription(){
        return new Description("County Cork is located in the Southwest of Ireland. It is Ireland's largest county in terms of area and contains the island's third largest city, Cork City. Much of County Cork is dominated by the Atlantic Ocean. Cork is easily accessible from the rest of Ireland and by ferry and plane.");
    }
}