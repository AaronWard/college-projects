public class TreeGarden extends Garden{
    public Plant getShade(){
        return new Plant("Silt");
    }

    public Plant getCenter(){
        return new Plant("Black Birch");
    }

    public Plant getBorder(){
        return new Plant("Walnut");
    }

    public Soil getSoil(){
        return new Soil("White Ash");
    }
}