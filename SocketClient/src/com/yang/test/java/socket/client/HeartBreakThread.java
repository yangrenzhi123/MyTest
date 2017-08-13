package com.yang.test.java.socket.client;
import java.io.IOException;
import java.net.Socket;

import com.yang.test.java.socket.common.SocketUtil;

public class HeartBreakThread implements Runnable {

	private Socket request;

	private boolean isKeepAlive = true;

	public HeartBreakThread(Socket request) {
		this.request = request;
	}

	@Override
	public void run() {
		while (isKeepAlive) {
			try {
				SocketUtil.writeStr2Stream("Heart break", request.getOutputStream());
				Thread.sleep(3000);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("HeartBreaking end.");
	}

	public Socket getRequest() {
		return request;
	}

	public void setRequest(Socket request) {
		this.request = request;
	}

	public boolean isKeepAlive() {
		return isKeepAlive;
	}

	public void setKeepAlive(boolean isKeepAlive) {
		this.isKeepAlive = isKeepAlive;
	}
}