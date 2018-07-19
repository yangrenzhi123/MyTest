package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("resource")
public class SimpleServer {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(8099);
		while (true) {
			final Socket socket = serverSocket.accept();
			new Thread(new Runnable() {
				public void run() {
					try {
						byte [] b = new byte[1];
						byte [] c = new byte[]{2};
						
						InputStream is = socket.getInputStream();
						OutputStream os = socket.getOutputStream();
						
						while(true){
							is.read(b);
							
							System.out.println(b[0]);
							
							os.write(c);
							os.write(c);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}