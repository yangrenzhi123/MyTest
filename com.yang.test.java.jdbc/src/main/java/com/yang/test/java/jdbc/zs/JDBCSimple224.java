package com.yang.test.java.jdbc.zs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JDBCSimple224 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.224:3306?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	static final int num = 10;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		doIt();
	}
	
	public static void doIt() throws ClassNotFoundException, SQLException {
		DecimalFormat df = new DecimalFormat("00,0000,0000");
		
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		PreparedStatement stmt = conn.prepareStatement("show slave status");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String s1 = rs.getString("Master_Log_File");
			String s2 = rs.getString("Read_Master_Log_Pos");
			String s3 = rs.getString("Relay_Master_Log_File");
			String s4 = rs.getString("Exec_Master_Log_Pos");
			String s5 = rs.getString("slave_io_running");
			String s6 = rs.getString("slave_sql_running");
			String s7 = rs.getString("last_error");
			String s8 = rs.getString("Seconds_Behind_Master");
			System.out.println(s1);
			System.out.println(df.format(Long.parseLong(s2)));
			System.out.println(s3);
			System.out.println(df.format(Long.parseLong(s4)));
			System.out.println(s5);
			System.out.println(s6);
			System.out.println("错误日志：" + s7);
			System.out.println("Seconds_Behind_Master：" + s8);
			System.out.println();
		}
		stmt.close();
	}
}