public class StudentApplication extends Student{
    public static void main(String [] args){

        /**
         * Default Constructor is called
         */
        Student s1 = new Student();
        s1.Student();
        System.out.println("** Default Constructor **");
        System.out.println(s1.getStudentNum());
        System.out.println(s1.getName());
        System.out.println(s1.getPhoneNum());
        System.out.println(s1.getCourse());
        System.out.println(s1.getAddress());

        System.out.println("\n");
    
        /**
         * User defined constructore is called 
         */
        Student s2 = new Student();
        s2.Student("B000111", "Brian", "Itb street", "087882288", "Engineering");
        System.out.println("** user Defined Constructor Constructor **");
        System.out.println(s2.getStudentNum());
        System.out.println(s2.getName());
        System.out.println(s2.getPhoneNum());
        System.out.println(s2.getCourse());
        System.out.println(s2.getAddress());
    }
}