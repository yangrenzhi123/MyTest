package com.yang.test.java.jdbc.zs;

import java.sql.SQLException;

public class SlaveSynStatusMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 20 主库
		JDBCSimple22.doIt(); // 从库
		JDBCSimple26.doIt(); // 从库
		JDBCSimple224.doIt(); // 从库
		JDBCSimple2243307.doIt(); // 备主
		JDBCSimple263307.doIt(); // 备从

		// dw主192.168.10.224:3308
		JDBCSimple263308.doIt(); // dw从192.168.10.26:3308
	}
}