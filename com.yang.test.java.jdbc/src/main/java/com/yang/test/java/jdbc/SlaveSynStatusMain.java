package com.yang.test.java.jdbc;

import java.sql.SQLException;

public class SlaveSynStatusMain {

	/**注意还有canal.server*/
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SlaveSynStatus228.doIt();
		SlaveSynStatus229.doIt();
		//SlaveSynStatus223.doIt();
	}
}