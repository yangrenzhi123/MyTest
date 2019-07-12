package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSimple3 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.228:3306/saas_lyzh?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);

		String getTables = "select table_name from information_schema.tables where table_schema='saas_lyzh'";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		getTablesPre.close();
	}
}