public class NumberFactoryTest{

    public static void main(String[] args) {
        String list1 = new String("C12345 C1324 C23222 C39288 C993383");
        String list2 = new String("I2342 I22443 I424532 I35533 I33323");
        NumberFactory n = new NumberFactory();

        n.getNumberList(list1).display();
        n.getNumberList(list2).display();
    }

}