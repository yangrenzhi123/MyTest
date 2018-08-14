package com.yang.test.java.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@SuppressWarnings("resource")
public class UDPServer {

	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket(8800);
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);

		while(true) {
			socket.receive(packet);
			String info = new String(data, 0, packet.getLength());
			
			
			
			System.out.println(info);
			
	        InetAddress address = packet.getAddress();
	        int port = packet.getPort();
	        System.out.println(address+":"+port);
		}
	}
}