package com.yang.test.java.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient3 {

	public static void main(String[] args) throws IOException {
		byte[] data = "hello".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.0.108"), 10135);

		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);

		byte[] answer = new byte[1024];
		packet = new DatagramPacket(answer, answer.length);
		socket.receive(packet);
		System.out.println(packet.getAddress() + ":" + packet.getPort() + " " + new String(answer, 0, packet.getLength()));

		socket.close();
	}
}