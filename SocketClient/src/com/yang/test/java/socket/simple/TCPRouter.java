package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPRouter {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8099);
		final Socket socket = serverSocket.accept();
		final Socket mysqlSocket = new Socket("192.168.10.229", 5306);
		

		
		InputStream is = socket.getInputStream();
		byte[] b = new byte[1024];
		is.read(b, 0, b.length);
		System.out.println("-----1----" + new String(b));


		OutputStream os = mysqlSocket.getOutputStream();
		os.write(b);
		InputStream mysqlIS = mysqlSocket.getInputStream();

		byte[] c = new byte[1024];
		mysqlIS.read(c);

		OutputStream receivedOS = socket.getOutputStream();
		receivedOS.write(c);

		socket.close();
		serverSocket.close();
		mysqlSocket.close();
	}
}

class Together {

	public Socket mysqlSocket;
	public Socket receivedSocket;

	public Socket getMysqlSocket() {
		return mysqlSocket;
	}

	public void setMysqlSocket(Socket mysqlSocket) {
		this.mysqlSocket = mysqlSocket;
	}

	public Socket getReceivedSocket() {
		return receivedSocket;
	}

	public void setReceivedSocket(Socket receivedSocket) {
		this.receivedSocket = receivedSocket;
	}
}