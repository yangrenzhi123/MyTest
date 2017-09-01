package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	static final String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433/Test";
	static final String USER = "sa";
	static final String PASS = "1qazxcvbnm,./";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(false);

		Statement stmt2 = conn.createStatement();
		stmt2.executeQuery("select @@spid");

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