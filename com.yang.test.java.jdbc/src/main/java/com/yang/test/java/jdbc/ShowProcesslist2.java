package com.yang.test.java.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowProcesslist2 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:8099?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		doIt();
	}

	public static void doIt() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		PreparedStatement stmt = conn.prepareStatement("show full processlist");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String user = rs.getString("user");
			String host = rs.getString("host");
			String state = rs.getString("state");
			String info = rs.getString("info");
			String command = rs.getString("Command");
			if (command != null && command.equals("Binlog Dump")) {
				String s = id + "\t" + user + "\t" + host + "\t" + state + "\t" + info;
				System.out.println(s);
			}
		}
		stmt.close();
	}
}