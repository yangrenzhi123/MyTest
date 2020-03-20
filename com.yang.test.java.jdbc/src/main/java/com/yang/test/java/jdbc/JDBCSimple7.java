package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSimple7 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.90:4306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";

	static final int num = 10;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);

		long a = System.currentTimeMillis();
		for (int i = 70000; i < 100000; i++) {
			t1(conn, i * 10000 + 1, (i + 1) * 10000);
			System.out.println("，位置标记：" + i);
		}
		System.out.println(System.currentTimeMillis() - a);
	}

	public static void t1(Connection conn, long start, long end) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("UPDATE h_exchange_score_record e SET e.ownerid=e.scoreaccountid, zhlx=(CASE WHEN (RIGHT ( scoreaccountid, 1 ) IN ( '1', '3', '5', '7', '9' )) THEN 'YH0001' ELSE 'YH0002' END) where exchangescorerecordzzid >= " + start + " and exchangescorerecordzzid <= " + end);
		long a = System.currentTimeMillis();
		stmt.executeUpdate();
		System.out.print("耗时" + (System.currentTimeMillis() - a));
		stmt.close();
	}
}