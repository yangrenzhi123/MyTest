package com.yang.test.java.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@SuppressWarnings("resource")
public class UDPServer {

	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket(8800);
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);

		while (true) {
			socket.receive(packet);
			String info = new String(data, 0, packet.getLength());
			System.out.println(packet.getAddress() + ":" + packet.getPort() + " " + info);
		}
	}
}