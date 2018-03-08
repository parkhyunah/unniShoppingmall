package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "hyuna";
	private static final String PASSWORD = "1234";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		//드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//연결 : DriverManager.getConnection(url,user,password); 
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
}
