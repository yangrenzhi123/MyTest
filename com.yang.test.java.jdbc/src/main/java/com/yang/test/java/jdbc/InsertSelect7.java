package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertSelect7 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.229:5306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";

	static final int num = 10;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);

		long a = System.currentTimeMillis();
		for (int i = 60000; i < 70000; i++) {
			t1(conn, i * 10000 + 1, (i + 1) * 10000);
			System.out.println("，位置标记：" + i);
		}
		System.out.println(System.currentTimeMillis() - a);
	}

	public static void t1(Connection conn, long start, long end) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("insert into h_recycle_record2 select * from h_recycle_record where recyclerecordzzid <= 739948177 and recyclerecordzzid >= " + start + " and recyclerecordzzid <= " + end);
		long a = System.currentTimeMillis();
		stmt.executeUpdate();
		System.out.print("耗时" + (System.currentTimeMillis() - a));
		stmt.close();
	}
}