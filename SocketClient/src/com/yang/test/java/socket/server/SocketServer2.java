package com.yang.test.java.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.yang.test.java.socket.common.SocketUtil;

@SuppressWarnings("resource")
public class SocketServer2 {

	public static final Map<String, Socket> socketMap = new HashMap<String, Socket>();

	public static void main(String[] args) throws IOException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
				while (true) {
					String readline;
					try {
						readline = sin.readLine();
						String[] orders = readline.split("\\|");
						Socket socket = SocketServer2.socketMap.get(orders[0]);
						if (socket != null) {
							OutputStream os = socket.getOutputStream();
							SocketUtil.writeStr2Stream(orders[1], os);
						}
						System.out.println(readline);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		ServerSocket serverSocket = new ServerSocket(8080);
		while (true) {
			Socket client = serverSocket.accept();
			new Thread(new HandlerThread2(client)).start();
		}
	}
}

class HandlerThread2 implements Runnable {
	private Socket socket;

	public HandlerThread2(Socket client) {
		socket = client;
	}

	public void run() {
		try {
			String code = SocketUtil.readStrFromStream(socket.getInputStream());
			Socket exist = SocketServer2.socketMap.get(code);
			if (exist != null) {
				exist.close();
			}
			SocketServer2.socketMap.put(code, socket);

			OutputStream os = socket.getOutputStream();
			SocketUtil.writeStr2Stream("I got 2", os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}