package com.yang.test.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.net.InetAddress;

public class Client {

	public void query(String host, int port) throws IOException {
		InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
		SocketChannel channel = SocketChannel.open();
		channel.connect(address);
		channel.socket().setSoTimeout(1000000);

		new Thread(new Runnable() {
			public void run() {
				try {
					ByteBuffer buf = ByteBuffer.allocate(1);
					while(true) {
						channel.read(buf);
						System.out.println(new String(buf.array()));
						buf.clear();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		ByteBuffer b1 = ByteBuffer.allocate(1024);
		byte[] a = new byte[100];
		while (true) {
			System.in.read(a);

			b1.clear();
			b1.put(a);
			b1.flip();
			channel.write(b1);
		}
		
	}

	public static void main(String[] args) throws IOException {
		new Client().query("127.0.0.1", 8777);
	}
}