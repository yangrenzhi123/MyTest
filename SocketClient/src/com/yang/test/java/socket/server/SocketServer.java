package com.yang.test.java.socket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.yang.test.java.socket.common.SocketUtil;

public class SocketServer {
	public static void main(String[] args) {
		System.out.println("Server...\n");
		SocketServer server = new SocketServer();
		server.init();
	}

	public void init() {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			while (true) {
				// 从请求队列中取出一个连接
				Socket client = serverSocket.accept();
				// 处理这次连接
				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	private class HandlerThread implements Runnable {
		private Socket socket;

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}

		public void run() {
			try {
				SocketUtil.readStrFromStream(socket.getInputStream());

				// 向客户端回复信息
				//PrintStream out = new PrintStream(socket.getOutputStream());
				//System.out.print("请输入:\t");
				// 发送键盘输入的一行
				//String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
				//out.println(s);

				//out.close();
			} catch (Exception e) {
				System.out.println("服务器 run 异常: " + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						socket = null;
						System.out.println("服务端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
	}
}