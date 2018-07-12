package com.yang.test.java.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleTypes;

public class Test2 {

	static final String DB_URL = "jdbc:oracle:thin:@192.168.6.109:1521:GitDB";
	static final String USER = "health";
	static final String PASS = "health";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		final Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		new Thread(new Runnable() {

			public void run() {
				try {
					CallableStatement cs = conn.prepareCall("{call test(?)}");
					cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.execute();

					ResultSet rs = (ResultSet) cs.getObject(1);

					while (rs.next()) {
						System.out.println("\t" + rs.getString("ABC"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}).start();
		System.out.println(1);
	}
}