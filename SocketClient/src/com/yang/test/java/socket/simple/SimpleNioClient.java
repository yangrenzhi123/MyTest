package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.net.InetAddress;

public class SimpleNioClient {

	public static void main(String[] args) throws IOException {
		InetSocketAddress address = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8099);
		SocketChannel channel = SocketChannel.open();
		channel.connect(address);

		channel.socket().setSoTimeout(10000);

		ByteBuffer b1 = ByteBuffer.allocate(10);
		byte[] a = new byte[100];
		while (true) {
			System.in.read(a);

			b1.clear();
			b1.put("1".getBytes());
			b1.flip();
			channel.write(b1);
		}
	}
}