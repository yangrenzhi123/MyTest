package com.yang.test.java.jdbc.zs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchPrimary {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.91:3307/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";
	static final TableCount a = new TableCount();
	static final TableCount b = new TableCount();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

		Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn2.setAutoCommit(true);
		
		List<String> tables = getTables(conn2);

		for(String table : tables) {
			if(table.startsWith("vw_")) {
				continue;
			}

			PreparedStatement stmt2 = conn2.prepareStatement("SELECT count(*) as count FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE table_schema = DATABASE() and constraint_name = 'PRIMARY' AND table_name = '"+table+"'");
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();
			int count = rs2.getInt("count");
			stmt2.close();
			
			if(count == 0) {
				System.out.println(table);
			}
		}
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