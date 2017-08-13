package com.yang.test.java.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yang.test.java.socket.common.SocketUtil;

public class SocketClient {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new RequestThread());
	}
}

class RequestThread implements Runnable {

	@Override
	public void run() {
		Socket request = null;
		try {
			request = new Socket("localhost", 8080);
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			String readline;

			while (true) {
				readline = sin.readLine();

				SocketUtil.writeStr2Stream(readline, request.getOutputStream());
				while(true){
					String got = SocketUtil.readStrFromStream(request.getInputStream());
					System.out.println("Server: " + got);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}