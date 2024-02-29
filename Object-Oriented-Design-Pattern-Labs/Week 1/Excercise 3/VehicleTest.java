public class VehicleTest{
    public static void main(String[] args) {
        Car c = new Car();
        c.Vehicle("Blue", "BMW", 1.2, 1000);
        System.out.println(c.getVRT());

        Motorbike m = new Motorbike();
        m.Vehicle("Blue", "BMW", 1.4, 1000);
        System.out.println(m.getBikeVRT());
    }
}