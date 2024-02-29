

public class StudentTest{
    public static void main(String [] args){
        Student student = new Student();
        student.setStudentNum(12345);
        student.setName("Aaron");
        student.setPhoneNum("087111111");
        student.setAddress("ITB road");
        student.setCourse("Computing");

        System.out.println(student.getStudentNum());
        System.out.println(student.getName());
        System.out.println(student.getPhoneNum());
        System.out.println(student.getCourse());
        System.out.println(student.getAddress());


    }
}