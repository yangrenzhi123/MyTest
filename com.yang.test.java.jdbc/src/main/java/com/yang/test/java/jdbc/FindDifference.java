package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindDifference {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.20:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.91:3307/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn1.setAutoCommit(true);

		Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn2.setAutoCommit(true);
		
		long last = 0;
		A:while(true) {
			long a = System.currentTimeMillis();
			PreparedStatement stmt1 = null;
			PreparedStatement stmt2 = null;

			stmt1 = conn1.prepareStatement("select * from h_dispenser_order where dispenserorderzzid >= "+last+" order by dispenserorderzzid limit 10000");
			ResultSet rs1 = stmt1.executeQuery();
			stmt2 = conn2.prepareStatement("select * from h_dispenser_order where dispenserorderzzid >= "+last+" order by dispenserorderzzid limit 10000");
			ResultSet rs2 = stmt2.executeQuery();
			
			while (rs1.next()) {
				rs2.next();

				long n1 = rs1.getLong("dispenserorderzzid");
				long n2 = rs2.getLong("dispenserorderzzid");
				if(n1 != n2) {
					System.out.println(n1 + "，" + n2);
					break A;
				}
				last = n1;
			}
			
			stmt1.close();
			stmt2.close();
			System.out.println(System.currentTimeMillis() - a);
		}
	}
}