package com.yang.test.java.socket.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.yang.test.java.socket.common.SocketUtil;

@SuppressWarnings("resource")
public class SocketClient3 {

	public static Socket request = null;

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {

		Socket request = new Socket("127.0.0.1", 8081);
		SocketUtil.writeStr2Stream("F0002", request.getOutputStream());
		
		String got = SocketUtil.readStrFromStream(request.getInputStream());
		System.out.println("Server: " + got);
	}
}