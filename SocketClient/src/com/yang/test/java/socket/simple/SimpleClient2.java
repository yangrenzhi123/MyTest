package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket request = new Socket("192.168.3.17", 8099);

		OutputStream os = request.getOutputStream();

		os.write(1);

		os.close();
		
		request.close();
	}
}