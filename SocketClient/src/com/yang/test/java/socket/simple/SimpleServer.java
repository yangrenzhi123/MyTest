package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("resource")
public class SimpleServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8099);
		final Socket socket = serverSocket.accept();
		while(true) {
			InputStream is = socket.getInputStream();
			byte[] b = new byte[1];
			is.read(b);
			System.out.println(new String(b));
		}
	}
}