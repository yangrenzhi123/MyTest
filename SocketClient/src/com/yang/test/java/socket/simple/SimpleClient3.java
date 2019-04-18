package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient3 {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		for (int i = 0; i < 1; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						Socket request = new Socket("127.0.0.1", 3113);

						OutputStream os = request.getOutputStream();

						String s = "";
						for(int i=0;i<2048;i++) {
							s = s + "1";
						}
						
						os.write(s.getBytes());
						
						byte[] b = new byte[1024];
						InputStream is = request.getInputStream();
						is.read(b);
						System.out.println(new String(b));

						os.close();
						request.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}