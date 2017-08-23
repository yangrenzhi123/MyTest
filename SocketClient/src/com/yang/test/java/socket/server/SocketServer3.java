package com.yang.test.java.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.yang.test.java.socket.common.SocketUtil;

@SuppressWarnings("resource")
public class SocketServer3 {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8081);
		while (true) {
			final Socket conn = serverSocket.accept();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						String code = SocketUtil.readStrFromStream(conn.getInputStream());
						System.out.println(code);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, "请求处理线程").start();
		}
	}
}