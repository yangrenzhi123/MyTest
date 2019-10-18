package com.yang.test.java.jdbc.zs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GetNum20 {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.20:3306/" + schema + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int max = 0;
		long sum = 0;
		
		DecimalFormat df = new DecimalFormat("####,####");
		Class.forName(DRIVER);

		Connection conn2 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn2.setAutoCommit(true);

		List<String> tables = getTables(conn2);

		PreparedStatement stmt2 = null;
		for (String table : tables) {
			if (table.startsWith("vw_")) {
				continue;
			}

			stmt2 = conn2.prepareStatement("select count(1) as num from " + table);
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();
			int a = rs2.getInt("num");
			
			if(!table.equals("h_etl_recycle_record") && a > max) {
				max = a;
			}
			sum = sum + a;

			System.out.println("表" + table + "，" + df.format(a));
		}

		System.out.println("单表的最大数据量：" + df.format(max));
		System.out.println("存储数据的总量：" + df.format(sum));
		stmt2.close();
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