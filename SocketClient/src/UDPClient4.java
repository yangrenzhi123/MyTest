
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient4 {

	public static void main(String[] args) throws IOException {
		byte[] data = "Œ“¥©Õ∏¡À".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("172.17.2.113"), 58213);

		DatagramSocket socket = new DatagramSocket();
		socket.send(packet);

		socket.close();
	}
}