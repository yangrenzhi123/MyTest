package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScanTbale {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.20:3306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";
	static int tdzl;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		
//		for(int i = 0;i<10000;i++) {
//			t1(conn, i*10000, (i+1)*10000);
//			System.out.println("位置标记：" + i);
//		}
		
		for(int i = 0;i<1400;i++) {
			t2(conn, i*10000, (i+1)*10000);
			System.out.println("位置标记：" + i);
		}
		
		System.out.println("tdzl：" + tdzl);
	}
	
	public static void t1(Connection conn, long start, long end) throws SQLException {
		String sql = "select sum(tdzl) as tdzl from h_recycle_record r LEFT JOIN h_garbage_class g ON g.flbm = r.ljlx INNER JOIN h_region b ON r.regionid = b.regionid"
		+ " where r.active = 1"
		+ " AND (r.yhlx='0' OR r.yhlx='1' OR r.yhlx='4' OR r.yhlx='7'  OR   INSTR(r.yhlx,'0')>0 OR INSTR(r.yhlx,'1')>0 OR INSTR(r.yhlx,'4')>0 OR INSTR(r.yhlx,'7')>0)"
		+ " AND ( concat( ',', r.yhlx, ',' ) NOT LIKE '%,5,%' OR r.tdzl >= 0 ) "
        + " AND r.yhlx <> '6'"
		+ " and communityid = '7ad64681-04f8-4e56-8aff-4b5133b7e807' and tfsj > '2019-09-01 00:00:00.0' and tfsj < '2020-02-29 23:59:59.0'"
		+ " and recyclerecordzzid > " + start + " and recyclerecordzzid <= " + end;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		long a = System.currentTimeMillis();
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			tdzl = tdzl + rs.getInt("tdzl");
		}
		stmt.close();
		
		System.out.println("耗时：" + (System.currentTimeMillis() - a));
	}
	
	public static void t2(Connection conn, long start, long end) throws SQLException {
		String sql = "select sum(tdzl) as tdzl from h_recycle_loseweight r LEFT JOIN h_garbage_class g ON g.flbm = r.ljlx INNER JOIN h_region b ON r.regionid = b.regionid"
		+ " where r.active = 1"
		+ " AND ( concat( ',', r.yhlx, ',' ) NOT LIKE '%,5,%' OR r.tdzl >= 0 ) "
        + " AND r.yhlx <> '6'"
		+ " and communityid = '7ad64681-04f8-4e56-8aff-4b5133b7e807' and tfsj > '2019-09-01 00:00:00.0' and tfsj < '2020-02-29 23:59:59.0'"
		+ " and recycleloseweightzzid > " + start + " and recycleloseweightzzid <= " + end;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		long a = System.currentTimeMillis();
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			tdzl = tdzl + rs.getInt("tdzl");
		}
		stmt.close();
		
		System.out.println("耗时：" + (System.currentTimeMillis() - a));
	}
}