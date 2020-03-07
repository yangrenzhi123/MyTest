package com.yang.test.java.jdbc.zs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllTables_20 {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.20:3306/" + schema + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
		conn2.setAutoCommit(true);

		List<String> tables = getTables(conn2);
		StringBuilder sb = new StringBuilder();
		for (String tableName : tables) {
			sb.append(tableName);
			sb.append("\r");
		}

		System.out.println(sb.toString());
	}

	public static List<String> getTables(Connection conn) throws SQLException {
		List<String> l = new ArrayList<>();

		String getTables = "select table_name from information_schema.tables where table_schema='" + schema + "'";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		while (rs.next()) {
			l.add(rs.getString(1));
		}
		getTablesPre.close();
		return l;
	}
}