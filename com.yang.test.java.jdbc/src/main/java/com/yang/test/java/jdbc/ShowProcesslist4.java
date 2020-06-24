package com.yang.test.java.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yang.test.java.jdbc.zs.JDBCSimple2243307;

public class ShowProcesslist4 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.224:3307?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		doIt();
	}

	public static void doIt() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		System.out.println("-------------224 3307--------------");
		JDBCSimple2243307.doIt(); // 从库
		
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		PreparedStatement stmt = conn.prepareStatement("show full processlist");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String Time = rs.getString("Time");
			String user = rs.getString("user");
			String host = rs.getString("host");
			String state = rs.getString("state");
			String info = rs.getString("info");
			//if (info != null) {
				String s = id + "\t" + Time + "\t" + user + "\t" + host + "\t" + state + "\t" + info;
				System.out.println(s);
			//}
		}
		stmt.close();
	}
}