package com.yang.test.java.jdbc;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowProcesslist {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.20:3306?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		doIt();
	}

	public static void doIt() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		RandomAccessFile raf = new RandomAccessFile("C:/1.txt", "rw");
		raf.seek(raf.length()); //不覆盖源文件
		FileChannel fileChannel = raf.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(10240);
		
		System.out.println("准备连接...");
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);
		System.out.println("连接完成");
		
		while(true) {
			System.out.println("开始执行..");
			
			boolean have = false;
			
			PreparedStatement stmt = conn.prepareStatement("show full processlist");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String user = rs.getString("user");
				String host = rs.getString("host");
				String state = rs.getString("state");
				String info = rs.getString("info");
				if(info != null && (info.contains("h_tenant_group") || info.contains("h_tenant_group"))) {
					have = true;
					
					String s = id + "\t" + user + "\t" + host + "\t" + state + "\t" + info;
					
					System.out.println(s);
					
					String newData = s + "\r\n";
					buf.clear();
					buf.put(newData.getBytes());
					buf.flip();
					while (buf.hasRemaining()) {
						fileChannel.write(buf);
					}
				}
			}

			if(have == true) {
				System.out.println("==============================================");
				String newData = "==============================================\r\n";
				buf.clear();
				buf.put(newData.getBytes());
				buf.flip();
				while (buf.hasRemaining()) {
					fileChannel.write(buf);
				}
			}
			stmt.close();
			
			Thread.sleep(500L);
		}
	}
}