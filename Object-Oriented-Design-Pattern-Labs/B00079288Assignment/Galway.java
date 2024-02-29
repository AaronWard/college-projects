/**
* A concrete factory class for Galway
*/
public class Galway extends App{
    public Town getTown(){
        return new Town("Galway");
    }

    public Restaurant getRestaurant(){
        return new Restaurant("Brasserie On The Corner\nBrasserie On The Corner\nAniar Restaurant\nKirwan's Lane");
    }
    public Hotel getHotel(){
        return new Hotel("Galway Bay Hotel\nJury's Inn\nArdiluan Hotel\nMenlo Park\nThe Western Hotel\nClybuarn");
    }
    public Place getPlace(){
        return new Place("Lynches Castle\nEyre Castle\nGalway Museum\nMenlo Castle\nBarna Woods");
    }

    public Description getDescription(){
        return new Description("County Galway is situated in the West of Ireland and is one of Ireland's largest counties. It is one of Ireland's most diverse counties containing the largest Gaeltacht (Irish speaking area) in the country, the largest island community, a vibrant city (Galway City) and some of Ireland's most dramatic mountains.");
    }
}