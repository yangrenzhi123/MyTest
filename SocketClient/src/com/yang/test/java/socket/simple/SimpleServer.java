package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8099);
		int i = 0;
		while (true) {
			final Socket socket = serverSocket.accept();
			new Thread(new Runnable() {
				public void run() {
					try {
						A:while(true) {
							InputStream is = socket.getInputStream();
							while(true) {
								byte[] b = new byte[2];
								int a = is.read(b);
								int byteArrayAvalibe = 2;
								if(a == -1) {
									byteArrayAvalibe = byteArrayAvalibe(b);
								}
								if (a == -1 && byteArrayAvalibe == 0) {
									System.out.println("Á¬½ÓÒì³£");
									break A;
								}
								
								byte[] c = new byte[byteArrayAvalibe];
								System.arraycopy(b, 0, c, 0, byteArrayAvalibe);
								
								System.out.println("--" + new String(c));
								if(a == -1) {
									break;
								}
							}
						}
					}catch(Exception e) {
						try {
							socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				}
			}).start();

			if (i++ > 10000) {
				break;
			}
		}
		serverSocket.close();
	}
	
	public static int byteArrayAvalibe(byte[] b) {
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			if (b[i] == 0) {
				j = i;
				break;
			}
		}
		return j;
	}
}