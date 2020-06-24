package com.yang.test.java.jdbc.zs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CompareNum20to2243307 {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.20:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.91:3307/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";
	static final TableCount a = new TableCount();
	static final TableCount b = new TableCount();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		DecimalFormat df = new DecimalFormat("####,####");
		Class.forName(DRIVER);

		Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn1.setAutoCommit(true);
		
		List<String> tables = getTables(conn1);

		Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn2.setAutoCommit(true);
		
		for(String table : tables) {
			if(table.startsWith("vw_")) {
				continue;
			}
			if(!table.equals("h_notice")) {
				continue;
			}

			final CountDownLatch latch = new CountDownLatch(2);
			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt2 = conn1.prepareStatement("select count(1) as num from "+ table);
						ResultSet rs2 = stmt2.executeQuery();
						rs2.next();
						a.setCount(rs2.getInt("num"));
						stmt2.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
					latch.countDown();
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt = conn2.prepareStatement("select count(1) as num from "+ table);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						b.setCount(rs.getInt("num"));
						stmt.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
					latch.countDown();
				}
			}).start();
			
			latch.await();
			int num1 = a.getCount();
			int num2 = b.getCount();
			System.out.println((num1 == num2 ? "一致": "不一致") + "，表"+table+"，"+df.format(num1)+"---"+df.format(num2)+(num1 != num2 ? "，差值：("+(num1-num2)+")" : ""));
		}
	}

	public static List<String> getTables(Connection conn) throws SQLException {
		List<String> l = new ArrayList<>();

		String getTables = "select table_name from information_schema.tables where table_schema='"+schema+"'";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		while (rs.next()) {
			l.add(rs.getString(1));
		}
		getTablesPre.close();
		return l;
	}
}

class TableCount{
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}