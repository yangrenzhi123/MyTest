package com.yang.test.java.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import com.yang.test.java.socket.udp.jm.DESUtil;

public class UDPClient3 {

	public static void main(String[] args) throws Exception {
		InetAddress address = InetAddress.getByName("localhost");
		int port = 8800;

		DatagramSocket socket = new DatagramSocket();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String mw = DESUtil.encryption(sc.next(), "123456");
			
			
			byte[] data = mw.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
		}
	}
}