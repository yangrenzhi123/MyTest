package com.yang.test.java.dbcp;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class App {

	public static void main(String[] args) throws Exception {
		Properties pp = new Properties();
		pp.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		pp.setProperty("url", "jdbc:mysql://192.168.197.81:3307/test?characterEncoding=utf-8");
		pp.setProperty("username", "root");
		pp.setProperty("password", "123456");
		
		DataSource ds = BasicDataSourceFactory.createDataSource(pp);
		
		Connection c = ds.getConnection();
		c.close();
		
		System.out.println();
	}
}