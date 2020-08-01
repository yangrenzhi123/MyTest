

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlQueryByidBat {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://172.17.134.10:3306/test";
	static final String USER = "root";
	static final String PASS = "123456";
	
	static final int num = 100;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// 准备id
		boolean first = true;
		long[] ids = new long[100000];
		int i=0;
		FileReader fr = new FileReader("C:/1.txt");
		BufferedReader bf = new BufferedReader(fr);
		String str;
		while ((str = bf.readLine()) != null) {
			if(first == true) {
				first = false;
				
				byte[] bs = str.getBytes();
				byte[] rs = new byte[bs.length - 3];
				for(int j = 3;j<bs.length;j++) {
					rs[j-3] = bs[j];
				}

				ids[i++] = Long.parseLong(new String(rs));
			}else {
				ids[i++] = Long.parseLong(str);
			}
		}
		bf.close();
		
		// 准备连接
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		PreparedStatement stmt = conn.prepareStatement("select * from h_recycle_record where recyclerecordzzid = ?");
		
		// 执行
		exec(ids, stmt);
		
		// 释放资源
		conn.close();
	}

	public static void exec(long[] ids, PreparedStatement stmt) throws IOException, SQLException {
		long a = System.currentTimeMillis();

		int j = 0;
		for (int i = 0; i < ids.length; i++) {
			long id = ids[i];
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				j++;
				System.out.println(id + "，ids已执行到下标：" + i + "，耗时：" + (System.currentTimeMillis() - a));
			}
		}

		System.out.println("总耗时：" + (System.currentTimeMillis() - a) + "，未找到的数据量：" + j);
	}
}