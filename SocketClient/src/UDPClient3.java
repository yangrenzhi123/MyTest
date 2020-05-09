
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient3 {

	public static void main(String[] args) throws IOException {
		byte[] data = "hello".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("172.17.2.113"), 5114);

		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);

		while (true) {
			byte[] answer = new byte[1024];
			packet = new DatagramPacket(answer, answer.length);
			socket.receive(packet);

			String message = new String(answer, 0, packet.getLength());
			System.out.println(packet.getAddress() + ":" + packet.getPort() + " " + message);

			if (message.equals("exit")) {
				break;
			}
		}

		socket.close();
	}
}