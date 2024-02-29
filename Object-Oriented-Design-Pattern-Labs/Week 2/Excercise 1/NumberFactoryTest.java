public class NumberFactoryTest{

    public static void main(String[] args) {
        String list1 = new String("1 2 3 4 5 6 7 8");
        String list2 = new String("1.3 1.2 2.3 2.4 4.1 1.0");
        String list3 = new String("A B C D E F G");
        String list4 = new String("0xFF 0xFDAB 0x2ACF 0x32AC");

        NumberFactory n = new NumberFactory();

        n.getNumberList(list1, "int").display();
        n.getNumberList(list1, "int").sum();

        n.getNumberList(list2, "double").display();
        n.getNumberList(list2, "double").sum();

        n.getNumberList(list3, "char").display();
        n.getNumberList(list3, "char").sum();

        n.getNumberList(list4, "hex").display();
        n.getNumberList(list4, "hex").sum();

    }

}