package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlDBConexion {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConexion(){
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","mysql");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
		
}
