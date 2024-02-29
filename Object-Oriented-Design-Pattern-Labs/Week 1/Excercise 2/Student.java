public class Student{
    private String studentNum;
    private String name;
    private String address;
    private String phoneNum;
    private String courseName;


    public void Student(){
        studentNum =  "0000";
        name = "Aaron";
        address = "itb road";
        phoneNum = "0879999999";
        courseName = "Computing";
    }

    public void Student(String num, String name, String add, String phoneNum, String course){
        this.studentNum = num;
        this.name = name;
        this.address = add;
        this.phoneNum = phoneNum;
        this. courseName = course;
    }


    public void setStudentNum(String num){
        this.studentNum = num;
    }
    public String getStudentNum(){
        return this.studentNum;
    }

    public void setName(String name){
        this.name = name;    
    }
    public String getName(){
        return this.name;
    }

    public void setAddress(String add){
        this.address = add;
    }
    public String getAddress(){
        return this.address;
    }

    public void setPhoneNum(String num){
        this.phoneNum = num;
    }   
    public String getPhoneNum(){
        return this.phoneNum;
    }

    public void setCourse(String course){
        this.courseName = course;
    }
    public String getCourse(){
        return this.courseName;
    }
}