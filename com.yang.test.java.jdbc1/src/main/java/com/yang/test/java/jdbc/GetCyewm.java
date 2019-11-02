package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetCyewm {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.228:3306/online_saas_lyzh_230?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		GetCyewm.getCyewm();
	}
	
	public static List<String> getCyewm() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		String getTables = "select cyewm from h_tenant_group where cyewm is not null limit 10000";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		List<String> cyewmList = new ArrayList<String>();
		while (rs.next()) {
			String cyewm = rs.getString(1);
			if(cyewm == null) {
				System.out.println("二维码为NULL");
				continue;
			}
			if(cyewm.equals("")) {
				System.out.println("二维码为空字符串");
				continue;
			}
			
			if (!cyewm.startsWith("LYZH")) {
				System.out.println(rs.getString(1) + "，二维码异常");
			}
			
			cyewmList.add(cyewm);
		}
		getTablesPre.close();
		return cyewmList;
	}
}