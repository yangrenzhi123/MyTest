package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSimple3 {

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
		
		for(int i = 0;i<24200;i++) {
			t1(conn, i*10000+1, (i+1)*10000);
			t2(conn, i*10000+1, (i+1)*10000);
			System.out.println("位置标记：" + i);
		}
		
	}
	
	public static void t1(Connection conn, long start, long end) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("update h_score_record set regionid = 'd9765bf6-91c2-4339-bd5e-f70e54e89cae' where communityid like '512%' and scorerecordzzid >= "+start+" and scorerecordzzid <= " + end);
		long a = System.currentTimeMillis();
		boolean rs = stmt.execute();
		System.out.println(rs + "," + (System.currentTimeMillis() - a));
		stmt.close();
	}
	
	public static void t2(Connection conn, long start, long end) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("update h_score_record set regionid = '55b50a03-5927-4868-afe4-2c834effcb21' where communityid like '511%' and scorerecordzzid >= "+start+" and scorerecordzzid <= " + end);
		long a = System.currentTimeMillis();
		boolean rs = stmt.execute();
		System.out.println(rs + "," + (System.currentTimeMillis() - a));
		stmt.close();
	}
}