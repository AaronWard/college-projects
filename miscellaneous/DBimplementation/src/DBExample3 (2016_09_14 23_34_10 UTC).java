import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//update data in a database
public class DBExample3 {
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
			Statement stmt = conn.createStatement();
			//Execute a Query
			String sql = "update emp set sal = 1250.00 where empno = 7369";
			stmt.executeUpdate(sql);
			//Process the results set
			System.out.println("data updated");
				
			
		//clean up the enviornment	
		//rs.close();
	    stmt.close();
	    conn.close();
		}
		
		catch(Exception e){
			
			e.printStackTrace();
		}
	}
}
