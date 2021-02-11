

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SimpleInsert {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.43.185:3307/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(false); //###


		PreparedStatement stmt = conn.prepareStatement("insert into order_info_0 (id, ds, tenantid, create_time) values ("+System.currentTimeMillis()+",1, 1, now());");
		stmt.executeUpdate();
		stmt.close();
		conn.commit(); //###
		conn.close();
	}
}