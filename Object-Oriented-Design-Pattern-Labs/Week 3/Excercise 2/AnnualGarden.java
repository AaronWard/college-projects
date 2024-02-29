public class AnnualGarden extends Garden{
    public Plant getShade(){
        return new Plant("Coleus");
    }

    public Plant getCenter(){
        return new Plant("Marigold");
    }

    public Plant getBorder(){
        return new Plant("alyssum");
    }
    
        public Soil getSoil(){
        return new Soil("Clay-loam");
    }
}