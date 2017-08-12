package com.yang.test.java.jdbc;

import java.sql.*;

public class Test {

	static final String DB_URL = "jdbc:jtds:sqlserver://192.168.1.100:1433/TEST";

	static final String USER = "sa";
	static final String PASS = "1qazxcvbnm,./";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		System.out.println("Connecting to database...");
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

//		Statement stmt = conn.createStatement();
//		stmt.executeQuery("SELECT qq FROM [USER] where id = 1");
		
		
		PreparedStatement stmt = conn.prepareStatement("SELECT qq FROM [USER] where id = 1");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String UserName = rs.getString("qq");
			System.out.print("UserName: " + UserName);
		}
		rs.close();
		stmt.close();
		conn.close();
	}
}