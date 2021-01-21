

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SimpleInsert {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.229:4000/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		long totalDistance = 0;
		for(int i=0;i<100;i++) {
			long a = System.currentTimeMillis();
			once();
			long distance = System.currentTimeMillis() - a;
			totalDistance = totalDistance + distance;
			System.out.println("本批总耗时：" + (distance));
		}
		System.out.println("总计耗时：" + totalDistance);
	}
	
	public static void once() {
		int num = 60;
		
		final CountDownLatch latch = new CountDownLatch(num);
		List<Thread> l = new ArrayList<>();
		for(int i=0;i<num;i++) {
			Thread t= new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
						conn.setAutoCommit(true);

						long a = System.currentTimeMillis();
						for(int i=0;i<1;i++) {
							PreparedStatement stmt = conn.prepareStatement("insert into test (name) values ('abc')");
							stmt.executeUpdate();
							stmt.close();
						}
						System.out.println(System.currentTimeMillis() - a);
						conn.close();
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						latch.countDown();
					}
				}
			});
			l.add(t);
		}
		
		for(Thread t : l) {
			t.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}