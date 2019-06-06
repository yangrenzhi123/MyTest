package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSimple2 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://172.28.51.33:3308?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "123456";

	static final int num = 10;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		PreparedStatement stmt = conn.prepareStatement("show slave status");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String s1 = rs.getString("Master_Log_File");
			String s2 = rs.getString("Read_Master_Log_Pos");
			String s3 = rs.getString("Relay_Master_Log_File");
			String s4 = rs.getString("Exec_Master_Log_Pos");
			String s5 = rs.getString("slave_io_running");
			String s6 = rs.getString("slave_sql_running");
			String s7 = rs.getString("last_error");
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			System.out.println(s4);
			System.out.println(s5);
			System.out.println(s6);
			System.out.println("错误日志：" + s7);
		}
		stmt.close();
	}
}