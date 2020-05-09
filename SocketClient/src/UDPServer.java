

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static void main(String[] args) throws IOException {
		byte[] data = new byte[1024];
		final DatagramPacket packet = new DatagramPacket(data, data.length);

		DatagramSocket socket = new DatagramSocket(5114);
		while(true) {
			socket.receive(packet);
			String info = new String(data, 0, packet.getLength());
			System.out.println(packet.getAddress() + ":" + packet.getPort() + " " + info);


			byte[] answer = "server got".getBytes();
			DatagramPacket p = new DatagramPacket(answer, answer.length, packet.getAddress(), packet.getPort());
			socket.send(p);
			
			if(info.equals("exit")) {
				break;
			}
		}
		socket.close();
	}
}