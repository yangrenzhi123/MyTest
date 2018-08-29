package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("resource")
public class SimpleClient {

	public static Socket request = null;

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {

		Socket request = new Socket("127.0.0.1", 8080);
		OutputStream os = request.getOutputStream();
		InputStream is = request.getInputStream();
		
		while(true){
			byte[] a = new byte[100];
			System.in.read(a);
			byte[] b = new byte[1];
			b[0] = a[0];
			os.write(b);
			
			System.out.println(1);
			

			byte[] c = new byte[2];
			is.read(c);
			System.out.println(c[0]);
		}
	}
}