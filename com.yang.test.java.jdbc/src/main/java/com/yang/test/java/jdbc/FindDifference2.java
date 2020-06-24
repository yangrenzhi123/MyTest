package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindDifference2 {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.90:4306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.229:5306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn1.setAutoCommit(true);
		Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn2.setAutoCommit(true);
		
		long last = 0L;
		long max = 0L;
		A:while(true) {
			long a = System.currentTimeMillis();
			PreparedStatement stmt1 = null;
			PreparedStatement stmt2 = null;

			stmt1 = conn1.prepareStatement("select * from h_notice where noticeid <= "+max+" noticeid >= "+last+" order by noticeid limit 10000");
			ResultSet rs1 = stmt1.executeQuery();
			stmt2 = conn2.prepareStatement("select * from h_notice where noticeid <= "+max+" noticeid >= "+last+" order by noticeid limit 10000");
			ResultSet rs2 = stmt2.executeQuery();
			
			while (rs1.next()) {
				rs2.next();

				long n1 = rs1.getLong("noticeid");
				long n2 = rs2.getLong("noticeid");
				if(n1 == n2) {
					System.out.println(n1 + "，" + n2);

					System.out.println(rs1.getObject(1));
					System.out.println(rs1.getObject(2));
					System.out.println(rs1.getObject(3));
					System.out.println(rs1.getObject(4));
					System.out.println(rs1.getObject(5));
					System.out.println(rs1.getObject(6));
					System.out.println(rs1.getObject(7));
					System.out.println(rs1.getObject(8));
					System.out.println(rs1.getObject(9));
					System.out.println(rs1.getObject(10));
					System.out.println(rs1.getObject(11));
					System.out.println(rs1.getObject(12));

					String insert = "(";
					for(int i =0;i<12;i++) {
						Object o = rs1.getObject(i+1);
						if(o == null) {
							insert = insert + "NULL,";
						}else {
							insert = insert + "'"+o+"',";
						}
					}
					insert = insert.substring(0, insert.length() - 1);
					insert = insert + ")";
					insert = "insert into h_notice2 values " + insert;
					PreparedStatement stmt3 = conn2.prepareStatement(insert);
					System.out.println(insert);
					stmt3.executeUpdate();
					break A;
				}
				last = n1;
			}
			
			stmt1.close();
			stmt2.close();
			System.out.println(System.currentTimeMillis() - a);
		}
	}
}