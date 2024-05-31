package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testConnect {
	private static Connection con = null;

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databasename = Chau_Tauvexxx";
		String user  = "sa";
		String password = "123456";
		con  = DriverManager.getConnection(url,user,password);
		System.out.println(con);
	}

}
