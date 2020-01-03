package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private ConnectionUtil() {
		super();
	}
	
	
	private static final Logger LOGGER = Logger.getLogger(ConnectionUtil.class);

	/*
	 * public static Connection getConnection() throws SQLException {
	 * 
	 * String url ="jdbc:oracle:thin:@localhost:1521:xe"; String username =
	 * "BANK_DB"; String password = "tiger";
	 * 
	 * return DriverManager.getConnection(url, username, password); }
	 */
	
	
	public static Connection getConnection() throws SQLException {
		
		String url ="jdbc:postgresql://database-1.cmaffqutuunn.us-east-1.rds.amazonaws.com:5432/postgres";
		String username = "postgres";
		String password = "Revature123";
		
		return DriverManager.getConnection(url, username, password);
	}
	
	
}


/*
 * getConnection(
 * "jdbc:postgresql://database-1.cmaffqutuunn.us-east-1.rds.amazonaws.com:5432/postgres",
 * "postgres", "Revature123");
 */