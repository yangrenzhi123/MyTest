package com.yang.test.java.jdbc;

import java.sql.SQLException;

public class SlaveSynStatusMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SlaveSynStatus.doIt();
		SlaveSynStatus2.doIt();
		SlaveSynStatus3.doIt();
	}
}