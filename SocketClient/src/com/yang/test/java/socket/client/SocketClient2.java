package com.yang.test.java.socket.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketClient2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		Socket socket = new Socket();
		
		boolean timeout = false;
		try{
			socket.connect(new InetSocketAddress("192.168.6.230", 7777), 3000);
		}catch(SocketTimeoutException e){
			timeout = true;
		}
		if(timeout){
			String exec = "java -Dfile.encoding=UTF-8 -Dspring.cloud.config.uri=http://127.0.0.1:3100 -jar com.xk.hb.hbsms-1.0.0.jar com.xk.hb.hbsms.ApplicationHbsmsService";
			Runtime.getRuntime().exec(exec);
			Thread.sleep(30000);
		}

		socket.close();
	}
}