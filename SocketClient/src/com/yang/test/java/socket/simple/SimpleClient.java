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

		Socket request = new Socket("115.236.86.197", 9038);
		OutputStream os = request.getOutputStream();
		InputStream is = request.getInputStream();
		
		byte[] bs = new byte[] {0x55, 0x00, 0x01, 0x21, (byte) 0x80, 0x70, 0x26, 0x41, (byte) 0xE5, 0x00,         0x00, 0x01, 0x00,        0x10, 0x10, 0x55};
		//os.write("55 00 01 21 80 70 26 41 E5 00 10 00 00 10 10 23".getBytes());
		os.write(bs);
		byte[] c = new byte[1024];
		is.read(c);
		String s = new String(c);
		System.out.println(s);
	}
}