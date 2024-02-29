public class Vehicle{
    private String colour;
    private String type;
    private double engineSize;
    private double netPrice;

    public void Vehicle(String colour, String type, double engineSize, double netPrice){
        this.colour = colour;
        this.type = type;
        this.engineSize = engineSize;
        this.netPrice = netPrice;
    }

    public double getNetPrice(){
        return this.netPrice;
    }

    public double getVRT(){
        return getNetPrice() * .21;
    }
}