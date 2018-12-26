package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSimple {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://172.28.51.33:23306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String DB_URL2 = "jdbc:mysql://172.28.51.33:33306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "123456";

	static final int num = 10;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName(DRIVER);

		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					Connection conn = DriverManager.getConnection(DB_URL1, USER, PASS);
					conn.setAutoCommit(true);
					PreparedStatement stmt = conn.prepareStatement("insert into t values ('5', '100')");
					boolean rs = stmt.execute();
					System.out.println(rs);
					stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
					conn.setAutoCommit(true);
					PreparedStatement stmt = conn.prepareStatement("insert into t values ('6', '100')");
					boolean rs = stmt.execute();
					System.out.println(rs);
					stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}
}