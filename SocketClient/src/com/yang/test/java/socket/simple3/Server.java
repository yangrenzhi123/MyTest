package com.yang.test.java.socket.simple3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		final ServerSocket serverSocket = new ServerSocket(8099);
		final Socket socket = serverSocket.accept();
		final InputStream is = socket.getInputStream();
		final OutputStream os = socket.getOutputStream();
		while (true) {
			byte[] b = new byte[2];
			System.out.print("��ʼ��ȡ����");
			int a = is.read(b);
			System.out.print("��a��" + a);
			System.out.println("�����ݣ�" + new String(b));

			os.write("i got".getBytes());
			
			if (a == -1) {
				System.out.println("�����쳣");
				break;
			}
		}

		serverSocket.close();
	}
}