
/**
* A concrete factory class for Dublin
*/
public class Dublin extends App{
    public Town getTown(){
        return new Town("Dublin");
    }
    public Restaurant getRestaurant(){
      return new Restaurant("Aussie BBQ\nTGI Fridays\nAlfies\nPyg\nThunder Road Cafe\nFeatherblade");
    }
    public Hotel getHotel(){
        return new Hotel("The Plaza\nThe Gresham Hotel\nSpencer Hotel\nAshling Hotelz\nTravel Lodge");
    }
    public Place getPlace(){
        return new Place("Stephens Green\nCroke Park\nDundrum Shopping Centre\nTemple Bar");
    }

    public Description getDescription(){
        return new Description("Dublin is the best of everything, with the cosmopolitan variety of a big city and the beating heart of a village. In a city as old as the Vikings, there are still plenty of surprises for locals and visitors alike; who knows what new bar, pop-up restaurant or vintage store has appeared behind the iconic Georgian doors? The winter days are short but the nights are long so find a pub with a roaring fireplace and settle in with a hot whiskey to warm up.");
    }
}