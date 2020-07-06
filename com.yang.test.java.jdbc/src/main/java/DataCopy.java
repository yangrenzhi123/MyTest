

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataCopy {

	static final String schema = "lyzhhw4";
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.20:3306/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.92:13307/"+schema+"?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";
	static final String TABLE_NAME_1 = "h_exchange_score_record";
	static final String TABLE_NAME_2 = "h_exchange_score_record";
	static final String PRIMARY = "exchangescorerecordzzid";
	static final int COLUMN_NUM = 34;
	static final int LIMIT = 10000;
	static long last = 12609069;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn1.setAutoCommit(true);

		Connection conn2 = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn2.setAutoCommit(true);

		while (true) {
			System.out.println("last：" + last);
			int num = 0;
			PreparedStatement stmt1 = conn1.prepareStatement("select * from " + TABLE_NAME_1 + " where "+PRIMARY+">"+last+" order by " + PRIMARY + " limit " + LIMIT);
			ResultSet rs1 = stmt1.executeQuery();
			
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
			stmt2.executeUpdate();
			stmt2.close();
			stmt1.close();
			if (num < LIMIT) {
				break;
			}
		}
	}
}