

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataCopy {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.229:5306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.255.93:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS1 = "lyzhhw4performancetesting";
	static final String PASS2 = "123456";
	static final String TABLE_NAME_1 = "h_recycle_record";
	static final String TABLE_NAME_2 = "h_recycle_record";
	static final String PRIMARY = "recyclerecordzzid";
	static final int COLUMN_NUM = 40;
	static final int LIMIT = 10000;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS1);
		conn1.setAutoCommit(true);

		Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS2);
		conn2.setAutoCommit(true);

		while (true) {
			PreparedStatement stmt3 = conn2.prepareStatement("select max("+PRIMARY+") from "+TABLE_NAME_2);
			ResultSet rs3 = stmt3.executeQuery();
			rs3.next();
			long last = rs3.getLong(1);
			System.out.println("last：" + last);
			
			int num = 0;
			PreparedStatement stmt1 = conn1.prepareStatement("select * from " + TABLE_NAME_1 + " where "+PRIMARY+">"+last+" and "+PRIMARY+"<=400000000 order by " + PRIMARY + " limit " + LIMIT);
			
			long b = System.currentTimeMillis();
			ResultSet rs1 = stmt1.executeQuery();
			System.out.print("扫描耗时："+(System.currentTimeMillis()-b));
			
			long c = System.currentTimeMillis();
			StringBuilder values = new StringBuilder("insert into " + TABLE_NAME_2 + " values ");
			while (rs1.next()) {
				num++;
				values.append("(");
				for (int i = 0; i < COLUMN_NUM; i++) {
					Object o = rs1.getObject(i + 1);
					if (o == null) {
						values.append("NULL");
					} else {
						values.append("'" + o.toString().replaceAll("\\\\", "\\\\").replaceAll("'", "\\\\'") + "'");
					}
					if(i != (COLUMN_NUM - 1)) {
						values.append(",");
					}
				}
				values.append("),");
				
				last = rs1.getLong(PRIMARY);
			}
			values.deleteCharAt(values.length() - 1);

			PreparedStatement stmt2 = conn2.prepareStatement(values.toString());
			System.out.print("，拼接耗时："+(System.currentTimeMillis()-c));
			
			long a = System.currentTimeMillis();
			stmt2.executeUpdate();
			System.out.println("，插入耗时："+(System.currentTimeMillis()-a));
			
			stmt2.close();
			stmt1.close();
			if (num < LIMIT) {
				break;
			}
		}
	}
}