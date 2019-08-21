package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GetDataNum {

	static final String schema = "online_saas_lyzh_230";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.229:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DecimalFormat df = new DecimalFormat("####,####");
		Class.forName(DRIVER);

		Connection conn2 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn2.setAutoCommit(true);
		
		List<String> tables = getTables(conn2);

		PreparedStatement stmt2 = null;
		for(String table : tables) {
			if(table.startsWith("vw_")) {
				continue;
			}
			
			stmt2 = conn2.prepareStatement("select count(1) as num from "+ table);
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();
			int a = rs2.getInt("num");
			

			System.out.println("表"+table+"，"+df.format(a));
		}
		
		stmt2.close();
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