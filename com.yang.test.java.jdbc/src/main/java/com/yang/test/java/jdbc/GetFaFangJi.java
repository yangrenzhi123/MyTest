package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetFaFangJi {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.228:5306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		GetFaFangJi.getSbbh();
	}

	public static List<String> getSbbh() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		String getTables = "select sbbh from h_equip_dispenser a inner join h_equipment b where a.equipmentid=b.equipmentid and a.ffjlx='1' and b.tenantid='a926e726-9424-4248-9493-34859f21fc84' and b.active='1'";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		Set<String> sbbhList = new HashSet<String>();
		while (rs.next()) {
			String deviceNo = rs.getString(1);
			sbbhList.add(deviceNo);
		}
		getTablesPre.close();

		List<String> l = new ArrayList<>();
		for (String item : sbbhList) {
			l.add(item);
		}
		return l;
	}
}