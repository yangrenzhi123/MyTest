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
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte[] a = new byte[100];
		while (true) {
			System.in.read(a);

			buffer.clear();
			buffer.put(a);
			buffer.flip();
			socket.write(buffer);
			
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		new Client().query("127.0.0.1", 8777);
	}
}