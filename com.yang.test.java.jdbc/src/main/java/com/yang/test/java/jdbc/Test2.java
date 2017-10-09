package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试连接和数据库spid是一一对应的
 * 
 */
public class Test2 {

	static final String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433/Test";
	static final String USER = "sa";
	static final String PASS = "1qazxcvbnm,./";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		final Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		new Thread(new Runnable() {

			public void run() {
				Statement stmt2;
				try {
					stmt2 = conn.createStatement();
					ResultSet rs = stmt2.executeQuery("select @@spid as spid");
					while (rs.next()) {
						String spid = rs.getString("spid");
						System.out.print("spid: " + spid);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				Statement stmt2;
				try {
					final Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
					stmt2 = conn.createStatement();
					ResultSet rs = stmt2.executeQuery("select @@spid as spid");
					while (rs.next()) {
						String spid = rs.getString("spid");
						System.out.print("spid: " + spid);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		System.out.println(1);
	}
}