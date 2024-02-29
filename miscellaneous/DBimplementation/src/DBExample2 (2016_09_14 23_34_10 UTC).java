import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//insert data into the database
public class DBExample2 {
	
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
			String sql = "insert into emp (empno, ename, job, deptno) values (1111, 'JONES', 'MARKETING', 40)";
			stmt.executeUpdate(sql);
			//Process the results set
			System.out.println("data inserted");
				
			
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
