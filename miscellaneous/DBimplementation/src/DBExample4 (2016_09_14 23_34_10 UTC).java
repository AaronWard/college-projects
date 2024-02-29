import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBExample4 {
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
			String sql = "delete from emp where deptno = 40";
			int numRows = stmt.executeUpdate(sql);
			//Process the results set
			System.out.println("Number of Rows Affected " + numRows);
			System.out.println("data deleted");
				
			
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
