package com.yang.test.java.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.util.StringUtils;

public class ShowProcesslist4 {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.10.91:3306?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		doIt();
	}

	public static void doIt() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		FileChannel fileChannel = getFileChannel();
		ByteBuffer buf = getByteBuffer();
		
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn.setAutoCommit(true);

		PreparedStatement stmt = conn.prepareStatement("show full processlist");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			long Time = rs.getString("Time") != null ? Long.parseLong(rs.getString("Time")) : 0;
			String user = rs.getString("user");
			String host = rs.getString("host");
			String state = rs.getString("state");
			String info = rs.getString("info") != null ? rs.getString("info").replaceAll("\r", "").replaceAll("\n", "") : "";
			if (Time > 5 && !StringUtils.isNullOrEmpty(info)) {
				String s = id + "\t" + Time + "\t" + user + "\t" + host + "\t" + state + "\t" + info + "\r\n";
				System.out.println(s.length());
				System.out.println(s);
				
				buf.clear();
				buf.put(s.getBytes());
				buf.flip();
				while (buf.hasRemaining()) {
					fileChannel.write(buf);
				}
			}
		}
		stmt.close();
	}
	public static FileChannel getFileChannel() {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("C:/show-full-processlist-" + System.currentTimeMillis(), "rw");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			raf.seek(raf.length());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} // 不覆盖源文件
		FileChannel fileChannel = raf.getChannel();
		return fileChannel;
	}

	public static ByteBuffer getByteBuffer() {
		ByteBuffer buf = ByteBuffer.allocate(102400);
		return buf;
	}
}