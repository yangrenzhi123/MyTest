package com.yang.test.java.netty.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient3 {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket request = new Socket("127.0.0.1", 3113);
		OutputStream os = request.getOutputStream();

		StringBuilder s = new StringBuilder();
		s = s.append("2");
		for (int i = 0; i < 2048; i++) {
			s = s.append("1");
		}
		s = s.append("2");
		s = s.append("2");
		for (int i = 0; i < 2048; i++) {
			s = s.append("1");
		}
		s = s.append("2");

		os.write(s.toString().getBytes());

		byte[] b = new byte[1024];
		InputStream is = request.getInputStream();
		is.read(b);
		System.out.println(new String(b));
		
		Thread.sleep(10000);
		
		os.close();
		request.close();
	}
}