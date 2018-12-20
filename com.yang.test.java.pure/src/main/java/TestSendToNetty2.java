import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TestSendToNetty2 {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		int count = Integer.parseInt(args[0]);

		long a = System.currentTimeMillis();
		List<OutputStream> osl = new ArrayList<OutputStream>();
		List<InputStream> isl = new ArrayList<InputStream>();
		List<Socket> sl = new ArrayList<Socket>();
		for (int i = 0; i < count; i++) {
			Socket request = new Socket("192.168.10.238", 3113);
			OutputStream os = request.getOutputStream();
			InputStream is = request.getInputStream();
			isl.add(is);
			osl.add(os);
			sl.add(request);
		}
		System.out.println("连接耗时：" + (System.currentTimeMillis() - a));

		
		Thread.sleep(3600000);
		
		for (Socket request : sl) {
			request.close();
		}
	}
}