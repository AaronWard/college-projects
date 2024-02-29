public class Student{
    private int studentNum;
    private String name;
    private String address;
    private String phoneNum;
    private String courseName;

    public void setStudentNum(int num){
        this.studentNum = num;
    }
    public int getStudentNum(){
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