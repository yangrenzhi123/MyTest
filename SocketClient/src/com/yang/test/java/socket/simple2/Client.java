package com.yang.test.java.socket.simple2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket request = new Socket("192.168.8.70", 8099);

		OutputStream os = request.getOutputStream();

		os.write("aaa".getBytes());
		os.write("aaa".getBytes());

		os.close();
		request.close();
	}
}