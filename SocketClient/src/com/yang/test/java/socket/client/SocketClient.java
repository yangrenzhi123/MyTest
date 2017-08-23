package com.yang.test.java.socket.client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yang.test.java.socket.common.SocketUtil;

public class SocketClient {

	public static Socket request = null;

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		while ((i++) < 100) {
			requestToServer();
			
			synchronized (SocketUtil.class) {
				SocketUtil.class.wait();
			}
		}
	}
	
	public static void requestToServer(){

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					SocketClient.request = new Socket("127.0.0.1", 8081);

					SocketUtil.writeStr2Stream("F0002", SocketClient.request.getOutputStream());
					while (true) {
						String got = SocketUtil.readStrFromStream(SocketClient.request.getInputStream());
						System.out.println("Server: " + got);
						if("quit".equals(got)){
							SocketClient.request.close();
							break;
						}
						if(got.startsWith("wait-")){
							SocketClient.request.close();
							String order = got.split("-")[1];
							try {
								Thread.sleep(Long.parseLong(order));
							} catch (NumberFormatException e) {
								e.printStackTrace();
								SocketClient.request.close();
							} catch (InterruptedException e) {
								e.printStackTrace();
								SocketClient.request.close();
							}

							synchronized (SocketUtil.class) {
								SocketUtil.class.notifyAll();
							}
							break;
						}
					}
				} catch (IOException e) {
					try {
						SocketClient.request.close();
					} catch (IOException e1) {
						e1.printStackTrace();
						e.printStackTrace();
					}
					e.printStackTrace();
					synchronized (SocketUtil.class) {
						SocketUtil.class.notifyAll();
					}
				}
			}
		}));
	}
}