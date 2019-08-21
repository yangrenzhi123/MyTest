package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CompareNum1 {

	static final String schema = "online_saas_lyzh_230";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.228:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.229:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DecimalFormat df = new DecimalFormat("####,####");
		Class.forName(DRIVER);

		Connection conn2 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn2.setAutoCommit(true);
		
		List<String> tables = new ArrayList<>();tables.add("h_recycle_record");

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		
		PreparedStatement stmt2 = null;
		PreparedStatement stmt = null;
		for(String table : tables) {
			if(table.startsWith("vw_")) {
				continue;
			}
			
			stmt2 = conn2.prepareStatement("select count(1) as num from "+ table);
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();
			int a = rs2.getInt("num");
			stmt = conn.prepareStatement("select count(1) as num from "+ table);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int b = rs.getInt("num");
			
			
			System.out.println((a == b ? "一致": "不一致") + "，表"+table+"，"+df.format(a)+"---"+df.format(b)+(a != b ? "，差值：("+(a-b)+")" : ""));
		}
		
		stmt2.close();
		stmt.close();
	}
}