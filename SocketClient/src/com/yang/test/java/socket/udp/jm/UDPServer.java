package com.yang.test.java.socket.udp.jm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

@SuppressWarnings("resource")
public class UDPServer {

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(8800);
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);

		while (true) {
			socket.receive(packet);
			String info = new String(data, 0, packet.getLength());

			String mw = DESUtil.decryption(info, "fdfefe43");
			
			System.out.println(packet.getAddress() + ":" + packet.getPort() + " " + mw);
		}
	}
}