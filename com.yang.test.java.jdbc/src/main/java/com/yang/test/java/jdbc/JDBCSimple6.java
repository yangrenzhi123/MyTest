package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCSimple6 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.90:4306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		
		for(int i = 0;i<30000;i++) {
			t1(conn, i*10000, (i+1)*10000);
			System.out.println("位置标记：" + i);
		}
	}
	
	public static void t1(Connection conn, long start, long end) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("UPDATE h_inspect_record SET communityid = 'eb872fee-5b9e-4980-8564-1be17ca4a6e4' WHERE tenantid = '5c6545ac-15c4-4631-8a7e-194011997865' AND communityid = '42017' and inspectrecordzzid > "+start+" and inspectrecordzzid <= " + end);
		long a = System.currentTimeMillis();
		boolean rs = stmt.execute();
		stmt.close();
		System.out.println(rs + "," + (System.currentTimeMillis() - a));
	}
}