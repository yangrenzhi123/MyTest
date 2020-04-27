package com.yang.test.java.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCP4Presto {

	public static void main(String[] args) throws Exception {
		Properties pp = new Properties();
		pp.setProperty("driverClassName", "com.facebook.presto.jdbc.PrestoDriver");
		pp.setProperty("url", "jdbc:presto://192.168.13.114:9999/test");
		pp.setProperty("username", "root");
		pp.setProperty("password", "123456");

		DataSource ds = BasicDataSourceFactory.createDataSource(pp);

		Connection c = ds.getConnection();

		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("show schemas from mysql");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		rs.close();
		c.close();
	}
}