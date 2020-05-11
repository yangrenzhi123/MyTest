package com.yang.test.java.socket.simple3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("127.0.0.1", 8099);

		final OutputStream os = socket.getOutputStream();
		final InputStream is = socket.getInputStream();

		os.write("aaa".getBytes());
		byte[] b = new byte[5];
		is.read(b);
		System.out.println(new String(b));
		
		os.write("aaa".getBytes());
		is.read(b);
		System.out.println(new String(b));

		os.close();
		is.close();
		socket.close();
	}
}