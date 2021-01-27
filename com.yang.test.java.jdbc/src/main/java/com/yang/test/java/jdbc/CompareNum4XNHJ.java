package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CompareNum4XNHJ {
	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.90:4306/" + schema + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.228:5306/" + schema + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL3 = "jdbc:mysql://192.168.10.229:5306/" + schema + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL4 = "jdbc:mysql://192.168.10.223:5306/" + schema + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "lyzhhw4performancetesting";

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		DecimalFormat df = new DecimalFormat("####,####");
		Class.forName(DRIVER);

		final Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn1.setAutoCommit(true);

		List<String> tables = getTables(conn1);

		final Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn2.setAutoCommit(true);
		final Connection conn3 = DriverManager.getConnection(DB_URL3, USER, PASS);
		conn3.setAutoCommit(true);
		final Connection conn4 = DriverManager.getConnection(DB_URL4, USER, PASS);
		conn4.setAutoCommit(true);

		for (final String table : tables) {
			if (table.startsWith("vw_") || table.startsWith("h_etl_")) {
				continue;
			}

			final CountDownLatch latch = new CountDownLatch(4);

			final DB db1 = new DB();
			final DB db2 = new DB();
			final DB db3 = new DB();
			final DB db4 = new DB();

			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt = conn1.prepareStatement("select count(1) as num from " + table);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						db1.setNum(rs.getInt("num"));
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}
			}).start();

			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt = conn2.prepareStatement("select count(1) as num from " + table);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						db2.setNum(rs.getInt("num"));
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}
			}).start();

			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt = conn3.prepareStatement("select count(1) as num from " + table);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						db3.setNum(rs.getInt("num"));
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}
			}).start();

			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt = conn4.prepareStatement("select count(1) as num from " + table);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						db4.setNum(rs.getInt("num"));
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}
			}).start();

			latch.await();
			if (db1.getNum() == db2.getNum() && db1.getNum() == db3.getNum() && db1.getNum() == db4.getNum()) {
				System.out.println("表" + table + "，一致，数量：" + df.format(db1.getNum()));
			} else {
				System.err.println("表" + table + "，不一致，DB1：" + db1.getNum() + "，DB2：" + db2.getNum() + "，DB3：" + db3.getNum() + "，DB4：" + db4.getNum());
			}
		}
	}

	public static List<String> getTables(Connection conn) throws SQLException {
		List<String> l = new ArrayList<>();

		String getTables = "select table_name from information_schema.tables where table_schema='" + schema + "'";
		PreparedStatement getTablesPre = conn.prepareStatement(getTables);
		ResultSet rs = getTablesPre.executeQuery();

		while (rs.next()) {
			l.add(rs.getString(1));
		}
		getTablesPre.close();
		return l;
	}
}

class DB {
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}