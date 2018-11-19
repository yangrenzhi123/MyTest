package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("resource")
public class SimpleClient2 {

	public static Socket request = null;

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {

		Socket request = new Socket("127.0.0.1", 8099);
		OutputStream os = request.getOutputStream();
		
		byte[] a = new byte[1];
		while (true) {
			System.in.read(a);

			os.write(a);
		}
	}
}