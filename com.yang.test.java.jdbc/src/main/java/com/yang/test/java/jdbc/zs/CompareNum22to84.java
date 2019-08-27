package com.yang.test.java.jdbc.zs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompareNum22to84 {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.84:3307/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER1 = "xmzx";
	static final String PASS1 = "xmzx019";
	
	static final String DB_URL2 = "jdbc:mysql://192.168.10.22:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER2 = "root";
	static final String PASS2 = "Lenovo@@7788";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn2 = DriverManager.getConnection(DB_URL1, USER1, PASS1);
		conn2.setAutoCommit(true);
		
		List<String> tables = getTables(conn2);

		Connection conn = DriverManager.getConnection(DB_URL2, USER2, PASS2);
		conn.setAutoCommit(true);
		
		PreparedStatement stmt2 = null;
		PreparedStatement stmt = null;
		for(String table : tables) {
			if(table.startsWith("vw_")) {
				continue;
			}
			if(table.startsWith("vm_")) {
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
			
			
			System.out.println((a == b ? "一致": "不一致") + ",表"+table+","+a+"-"+b+(a != b ? "，差值：("+(a-b)+")" : ""));
		}
		
		stmt2.close();
		stmt.close();
	}

	public static List<String> getTables(Connection conn) throws SQLException {
		List<String> l = new ArrayList<>();

		String getTables = "select table_name from information_schema.tables where table_schema='"+schema+"'";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		while (rs.next()) {
			l.add(rs.getString(1));
		}
		getTablesPre.close();
		return l;
	}
}