package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class InnodbPoolHit {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.229:5306/" + schema + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DecimalFormat df = new DecimalFormat("####,####.######");
		Class.forName(DRIVER);
		
		Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn1.setAutoCommit(true);
		
		PreparedStatement stmt = conn1.prepareStatement("show status like 'Innodb_buffer_pool_read_requests'");
		ResultSet rs = stmt.executeQuery();
		rs.next();
		double Innodb_buffer_pool_read_requests = rs.getDouble("Value");
		System.out.println(df.format(Innodb_buffer_pool_read_requests));
		
		stmt = conn1.prepareStatement("show status like 'Innodb_buffer_pool_read_ahead'"); //预读
		rs = stmt.executeQuery();
		rs.next();
		double Innodb_buffer_pool_read_ahead = rs.getDouble("Value");
		System.out.println(df.format(Innodb_buffer_pool_read_ahead));
		
		stmt = conn1.prepareStatement("show status like 'Innodb_buffer_pool_reads'"); //读取磁盘次数
		rs = stmt.executeQuery();
		rs.next();
		double Innodb_buffer_pool_reads = rs.getDouble("Value");
		System.out.println(df.format(Innodb_buffer_pool_reads));
		
		System.out.println(Innodb_buffer_pool_read_requests/(Innodb_buffer_pool_read_requests + Innodb_buffer_pool_read_ahead + Innodb_buffer_pool_reads));
		System.out.println((Innodb_buffer_pool_read_requests-Innodb_buffer_pool_reads)/Innodb_buffer_pool_read_requests);
	}
}