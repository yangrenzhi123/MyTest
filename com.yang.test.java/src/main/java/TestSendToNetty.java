import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestSendToNetty {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		int count = 100;

		long a = System.currentTimeMillis();
		List<OutputStream> osl = new ArrayList<OutputStream>();
		List<Socket> sl = new ArrayList<Socket>();
		for (int i = 0; i < count; i++) {
			Socket request = new Socket("192.168.10.238", 3113);
			OutputStream os = request.getOutputStream();
			osl.add(os);
			sl.add(request);
		}
		System.out.println("连接耗时："+(System.currentTimeMillis() - a));

		

		final CountDownLatch latch = new CountDownLatch(count);
		
		a = System.currentTimeMillis();
		List<Thread> tl = new ArrayList<Thread>();
		for (int i = 0; i < count; i++) {
			final int j = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					OutputStream os = osl.get(j);
					byte[] bs = hexString2Bytes("554000E0010200012180702405120A1D0108040000000000000000000000000000000000000000000000000000000000000000043E88011F0000000000A4B655");
					try {
						os.write(bs);
					} catch (IOException e) {
						e.printStackTrace();
					}
					latch.countDown();
				}
			});
			tl.add(t);
		}
		System.out.println("线程创建耗时："+(System.currentTimeMillis() - a));
		

		
		a = System.currentTimeMillis();
		for(Thread t : tl) {
			t.start();
		}
		latch.await();
		System.out.println("执行耗时："+(System.currentTimeMillis() - a));
		
		for(Socket request : sl) {
			request.close();
		}
	}
	
	public static byte[] hexString2Bytes(String hex) {
		if ((hex == null) || (hex.equals(""))) {
			return null;
		} else if (hex.length() % 2 != 0) {
			return null;
		} else {
			hex = hex.toUpperCase();
			int len = hex.length() / 2;
			byte[] b = new byte[len];
			char[] hc = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int p = 2 * i;
				b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
			}
			return b;
		}
	}
	
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}