package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	//static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	//static final String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433/Test";
	//static final String USER = "sa";
	//static final String PASS = "1qazxcvbnm,./";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.30.120:8066/TESTDB";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		
		int num = 50;
		
		final List<Connection> l = new ArrayList<Connection>();
		for(int i=0;i<num;i++) {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(true);
			l.add(conn);
		}

		List<Thread> l2 = new ArrayList<Thread>();
		for(int i=0;i<num;i++) {
			final int j = i;
			
			Thread t = new Thread(new Runnable() {
				
				public void run() {
					try {
						long a = System.currentTimeMillis();
						Connection conn = l.get(j);
						PreparedStatement stmt = conn.prepareStatement("select id from t");
						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							//String UserName = rs.getString("id");
							//System.out.print("UserName: " + UserName);
						}
						rs.close();
						stmt.close();
						conn.close();
						System.out.println(System.currentTimeMillis() - a);
					}catch(Exception e) {
					}
				}
			});
			
			l2.add(t);
		}
		
		for(int i=0;i<num;i++) {
			l2.get(i).start();
		}
	}
}