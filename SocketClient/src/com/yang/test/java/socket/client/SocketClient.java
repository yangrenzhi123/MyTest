package com.yang.test.java.socket.client;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		executor.execute(new RequestThread());
	}
}