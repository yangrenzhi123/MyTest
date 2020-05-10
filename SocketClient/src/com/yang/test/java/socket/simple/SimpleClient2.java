package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket request = new Socket("127.0.0.1", 8099);

		OutputStream os = request.getOutputStream();

		os.write("aaa".getBytes());
//		byte[] b = new byte[1024];
//		InputStream is = request.getInputStream();
//		is.read(b);
//		System.out.println(new String(b));
//		
//		os.write("1".getBytes());
//		b = new byte[1024];
//		is = request.getInputStream();
//		is.read(b);
//		System.out.println(new String(b));

		os.close();
		request.close();
	}
}