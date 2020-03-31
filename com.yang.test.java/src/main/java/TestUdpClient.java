import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestUdpClient {

	public static void main(String[] args) throws IOException {
		byte[] buf = "i am test".getBytes();

		InetAddress address = InetAddress.getByName("192.168.10.85");
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, 13139);

		DatagramSocket datagramSocket = new DatagramSocket();
		datagramSocket.send(datagramPacket);
		datagramSocket.close();
	}
}