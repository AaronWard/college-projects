import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBExample5 {
public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost/itb";
		String user = "root";
		String password = "";
		
		try{
			//Register the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Get a connection to the database
			Connection conn = DriverManager.getConnection(url,user,password);
			//Create a statement 
			PreparedStatement stmt = conn.prepareStatement("select * from emp where sal > ? and deptno = ?");
			
			//set the parameters
			stmt.setDouble(1, 1000);
			stmt.setInt(2,30);
			
			//Execute a Query
			ResultSet rs = stmt.executeQuery();
			//display the results set
			while(rs.next()){
			    //Retrieve by column name
			    int empno  = rs.getInt("empno");
			    String ename = rs.getString("ename");
			    String job = rs.getString("job");
			    Double sal = rs.getDouble("sal");
			    int deptno  = rs.getInt("deptno");
			 
			    //Display values
			    System.out.print("ID: " + empno);
			    System.out.print(", Name: " + ename);
			    System.out.println(", Job: " + job);
			    System.out.println(", Sal: " + sal);
			    System.out.println(", Dept: " + deptno);
			 }
				
		//clean up the enviornment	
		rs.close();
	    stmt.close();
	    conn.close();
		}
		
		catch(Exception e){
			
			e.printStackTrace();
		}
	}



}
