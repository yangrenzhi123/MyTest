package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowProcesslist {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.90:4306?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		doIt();
	}

	public static void doIt() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);
		PreparedStatement stmt = conn.prepareStatement("show processlist");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String user = rs.getString("user");
			String host = rs.getString("host");
			String state = rs.getString("state");
			String info = rs.getString("info");
			if(info != null && (info.contains("h_garbagebag_pull") || info.contains("h_garbagebag_pull"))) {
				System.out.println(id + "\t" + user + "\t" + host + "\t" + state + "\t" + info);
			}
		}
		stmt.close();
	}
}