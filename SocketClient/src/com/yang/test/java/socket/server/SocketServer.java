package com.yang.test.java.socket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.yang.test.java.socket.common.SocketUtil;

@SuppressWarnings("resource")
public class SocketServer {
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			while (true) {
				Socket client = serverSocket.accept();
				new Thread(new HandlerThread(client)).start();
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}
}

class HandlerThread implements Runnable {
	private Socket socket;

	public HandlerThread(Socket client) {
		socket = client;
		new Thread(this).start();
	}

	public void run() {
		try {
			String got = SocketUtil.readStrFromStream(socket.getInputStream());
			System.out.println("Client: " + got);

			OutputStream os = socket.getOutputStream();
			

			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			while(true){

				String readline = sin.readLine();
				SocketUtil.writeStr2Stream(readline, os);
			}
		} catch (Exception e) {
			System.out.println("服务器 run 异常: " + e.getMessage());
		}
	}
}