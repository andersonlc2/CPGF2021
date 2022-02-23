package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	// open connection
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("url");
				
				conn = DriverManager.getConnection(url, props);			
			}
			catch(SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
		return conn;
	}
	
	// close connection
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch(SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	
	// laod properties
	private static Properties loadProperties() {
		
		try(FileInputStream fs = new FileInputStream("/home/anderson.correa/Props/db.properties")) {
			
			Properties props = new Properties();
			props.load(fs);
			
			return props;
		}
		catch(IOException e) {
			throw new DBException(e.getMessage());
		}
	}

	// close Statement
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}			
		}
	}

	// close Resultset
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}					
		}
	}
}
