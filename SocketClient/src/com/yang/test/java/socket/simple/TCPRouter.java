package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPRouter {

	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(8099);
		final Socket receivedSocket = serverSocket.accept();
		
		final Socket mysqlSocket = new Socket("192.168.10.229", 5306);
		new Thread(new Runnable() {
			public void run() {
				try {
					A:while(true) {
						InputStream mysqlIS = mysqlSocket.getInputStream();
						while(true) {
							int length = 10;
							byte[] b = new byte[length];
							
							int a = mysqlIS.read(b);
							
							int byteArrayAvalibe = a;
							if(a == -1) {
								byteArrayAvalibe = byteArrayAvalibe(b);
							}
							if (a == -1 && byteArrayAvalibe == 0) {
								System.out.println("连接异常");
								break A;
							}
							
							byte[] c = new byte[byteArrayAvalibe];
							System.arraycopy(b, 0, c, 0, byteArrayAvalibe);
							OutputStream receivedOS = receivedSocket.getOutputStream();
							receivedOS.write(c);
							
							if(a == -1) {
								break;
							}
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					A:while(true) {
						InputStream is = receivedSocket.getInputStream();
						while(true) {
							int length = 10;
							byte[] b = new byte[length];

							int a = is.read(b);
							
							int byteArrayAvalibe = a;
							if(a == -1) {
								byteArrayAvalibe = byteArrayAvalibe(b);
							}
							if (a == -1 && byteArrayAvalibe == 0) {
								System.out.println("连接异常");
								break A;
							}
							
							byte[] c = new byte[byteArrayAvalibe];
							System.arraycopy(b, 0, c, 0, byteArrayAvalibe);
							OutputStream os = mysqlSocket.getOutputStream();
							os.write(c);
							
							if(a == -1) {
								break;
							}
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		Thread.sleep(1000000);
		
		receivedSocket.close();
		serverSocket.close();
		mysqlSocket.close();
	}
	
	public static int byteArrayAvalibe(byte[] b) {
		int j = 0;
		for (int i = b.length - 1; i >= 0; i--) {
			if (b[i] != 0) {
				j = i;
				break;
			}
		}
		return j;
	}
}

class Together {

	public Socket mysqlSocket;
	public Socket receivedSocket;

	public Socket getMysqlSocket() {
		return mysqlSocket;
	}

	public void setMysqlSocket(Socket mysqlSocket) {
		this.mysqlSocket = mysqlSocket;
	}

	public Socket getReceivedSocket() {
		return receivedSocket;
	}

	public void setReceivedSocket(Socket receivedSocket) {
		this.receivedSocket = receivedSocket;
	}
}