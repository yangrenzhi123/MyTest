package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetSbbh {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.228:3306/saas_lyzh?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		GetSbbh.getSbbh();
	}
	
	public static List<String> getSbbh() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		String getTables = "select sbbh from h_equipment where sblx = '03'";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		List<String> sbbhList = new ArrayList<String>();
		while (rs.next()) {
			String deviceNo = rs.getString(1);
			if (deviceNo.length() != 14) {
				System.out.println(rs.getString(1) + "，长度异常");
			}
			sbbhList.add(deviceNo);
		}
		getTablesPre.close();
		return sbbhList;
	}
}