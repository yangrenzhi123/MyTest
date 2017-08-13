package com.yang.test.java.socket.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.yang.test.java.socket.common.SocketUtil;

public class RequestThread implements Runnable {

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
				SocketUtil.readStrFromStream(request.getInputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (request != null) {
				try {
					request.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}