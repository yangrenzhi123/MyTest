package com.yang.test.java.jdbc.zs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

public class RelaySpeed {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.224:3307?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	static long lastMaster = 0;
	static long lastSlave = 0;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, InterruptedException {
		while(true) {
			doIt();
			
			Thread.sleep(10000L);
		}
	}
	
	public static void doIt() throws ClassNotFoundException, SQLException, ParseException {
		DecimalFormat df = new DecimalFormat("##,####,0000");
		
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		PreparedStatement stmt = conn.prepareStatement("show slave status");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String s2 = rs.getString("Read_Master_Log_Pos");
			String s4 = rs.getString("Exec_Master_Log_Pos");
			long master = df.parse(s2).longValue();
			long slave = df.parse(s4).longValue();
			long masterDistance = master - lastMaster;
			long slaveDistance = slave - lastSlave;
			System.out.println(df.format(masterDistance) + "\t" + df.format(slaveDistance) + "\t" + df.format(slaveDistance - masterDistance));
			lastMaster = df.parse(s2).longValue();
			lastSlave = df.parse(s4).longValue();
		}
		
		
		stmt.close();
	}
}