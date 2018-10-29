package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Test {

	//static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	//static final String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433/Test";
	//static final String USER = "sa";
	//static final String PASS = "1qazxcvbnm,./";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.10:3306/test";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		Class.forName(DRIVER);
		
		int num = 10;
		
		long b = System.currentTimeMillis();
		final List<Connection> l = new ArrayList<Connection>();
		for(int i=0;i<num;i++) {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(true);
			l.add(conn);
		}
		System.out.println(System.currentTimeMillis() - b);

		final CountDownLatch latch = new CountDownLatch(num);
		List<Thread> l2 = new ArrayList<Thread>();
		for(int i=0;i<num;i++) {
			final int j = i;
			
			Thread t = new Thread(new Runnable() {
				
				public void run() {
					try {
						Connection conn = l.get(j);
						for(int h=0;h<1000;h++) {
							PreparedStatement stmt = conn.prepareStatement("select id from t");
							ResultSet rs = stmt.executeQuery();
							while (rs.next()) {
							}
							rs.close();
							stmt.close();
						}
						conn.close();
						latch.countDown();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			l2.add(t);
		}
		
		long a = System.currentTimeMillis();
		for(int i=0;i<num;i++) {
			l2.get(i).start();
		}
		
		latch.await();
		System.out.println(System.currentTimeMillis() - a);
		System.out.println(1);
	}
}