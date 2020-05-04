package com.yang.test.java.socket.udp.jm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient3 {

	public static void main(String[] args) throws Exception {
		InetAddress address = InetAddress.getByName("127.0.0.1");
		int port = 8800;

		DatagramSocket socket = new DatagramSocket();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String mw = DESUtil.encryption(sc.next(), "fdfefe43");
			
			
			byte[] data = mw.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
		}
	}
}