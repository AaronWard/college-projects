import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBExample1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//Register the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Get a connection to the database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/itb","root", "");
			//Create a statement 
			Statement stmt = conn.createStatement();
			//Execute a Query
			ResultSet rs = stmt.executeQuery("select * from emp");
			//Process the results set
			while (rs.next()){
				System.out.println(rs.getInt("empno") + "," + rs.getString("ename"));
				
			}
		//clean up the environment	
		rs.close();
	    stmt.close();
	    conn.close();
		}
		
		catch(Exception e){
			
			e.printStackTrace();
		}
	}
}
