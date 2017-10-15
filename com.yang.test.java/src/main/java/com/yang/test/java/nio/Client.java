package com.yang.test.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.net.InetAddress;

public class Client {

	public void query(String host, int port) throws IOException {
		InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(host), port);
		SocketChannel socket = SocketChannel.open();
		socket.connect(address);
		int i = 0;
		ByteBuffer buffer = ByteBuffer.allocate(1024000);
		while (true) {
			buffer.clear();
			buffer.put((i++ + "\n").getBytes());
			buffer.flip();
			socket.write(buffer);
		}
	}

	public static void main(String[] args) throws IOException {
		new Client().query("192.168.1.102", 8099);
	}
}