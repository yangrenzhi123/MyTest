package com.yang.test.java.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import com.yang.test.java.socket.udp.jm.DESUtil;

public class UDPClientA {

	public static void main(String[] args) throws IOException {
		byte[] data = "hello".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.100"), 5114);

		final DatagramSocket socket = new DatagramSocket();
		socket.send(packet);

		new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						byte[] answer = new byte[1024];
						DatagramPacket packet = new DatagramPacket(answer, answer.length);

						socket.receive(packet);
						System.out.println(packet.getAddress() + ":" + packet.getPort() + " " + new String(answer, 0, packet.getLength()));
					}catch(Exception e) {
						socket.close();
						e.printStackTrace();
						break;
					}
				}
				socket.close();
			}
		}).start();
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String ipPort = sc.next();
			String[] ipAndPort = ipPort.split(":");
			
			
			packet = new DatagramPacket(data, data.length, InetAddress.getByName(ipAndPort[0]), Integer.parseInt(ipAndPort[1]));
			socket.send(packet);
			System.out.println("信息已发送");
		}
	}
}