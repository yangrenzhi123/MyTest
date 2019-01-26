package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSimple {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.228:3306/saas_lyzh?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "123456";

	static final int num = 10;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		PreparedStatement stmt = conn.prepareStatement("select count(1) from h_account");
		long a = System.currentTimeMillis();
		boolean rs = stmt.execute();
		System.out.println(rs + "," + (System.currentTimeMillis() - a));
		stmt.close();
	}
}