package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient3 {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		Socket request = new Socket("127.0.0.1", 8099);

		OutputStream os = request.getOutputStream();

		os.write("1".getBytes());
		byte[] b = new byte[1024];
		InputStream is = request.getInputStream();
		is.read(b);
		System.out.println(new String(b));
		
		os.write("1".getBytes());
		b = new byte[1024];
		is = request.getInputStream();
		is.read(b);
		System.out.println(new String(b));
		
		Thread.sleep(100000L);
		
		os.close();
		request.close();
	}
}