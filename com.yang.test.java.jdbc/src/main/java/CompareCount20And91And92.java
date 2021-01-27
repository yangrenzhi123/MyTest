

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.yang.test.java.jdbc.zs.TableCount;

public class CompareCount20And91And92 {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.20:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.91:3307/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL3 = "jdbc:mysql://192.168.10.92:13307/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";
	static final TableCount a = new TableCount();
	static final TableCount b = new TableCount();
	static final TableCount c = new TableCount();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		DecimalFormat df = new DecimalFormat("####,####");
		Class.forName(DRIVER);

		final Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn1.setAutoCommit(true);
		final Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn2.setAutoCommit(true);
		final Connection conn3 = DriverManager.getConnection(DB_URL3, USER, PASS);
		conn3.setAutoCommit(true);
		
		List<String> tables = getTables(conn1);
		for(final String table : tables) {
			if(table.startsWith("vw_")) {
				continue;
			}
			if(!table.equals("h_exchange_score_record")) {
				continue;
			}

			final CountDownLatch latch = new CountDownLatch(3);
			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt = conn1.prepareStatement("select count(1) as num from "+ table);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						a.setCount(rs.getInt("num"));
						stmt.close();
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
			new Thread(new Runnable() {
				public void run() {
					try {
						PreparedStatement stmt = conn3.prepareStatement("select count(1) as num from "+ table);
						ResultSet rs = stmt.executeQuery();
						rs.next();
						c.setCount(rs.getInt("num"));
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
			int num3 = c.getCount();
			if (num1 == num2 && num2 == num3) {
				System.out.println("一致，表" + table + "，" + df.format(num1) + "---" + df.format(num2) + "---" + df.format(num3));
			} else {
				System.err.println("不一致，表" + table + "，" + df.format(num1) + "---" + df.format(num2) + "---" + df.format(num3) + "，差值："+(num1 - num2) + "，" + (num1 - num3));
			}
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