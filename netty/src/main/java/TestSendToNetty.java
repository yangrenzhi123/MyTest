import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestSendToNetty {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		int count = Integer.parseInt(args[0]); //由于设备号限制，不要大于这个值

//		List<String> devices = new ArrayList<String>();
//		FileReader fr = new FileReader("D:\\Readline");
//		BufferedReader bf = new BufferedReader(fr);
//		String str; // 按行读取字符串
//		while ((str = bf.readLine()) != null) {
//			devices.add(str);
//		}
//		bf.close();
		
		
		

		long a = System.currentTimeMillis();
		final List<OutputStream> osl = new ArrayList<OutputStream>();
		final List<InputStream> isl = new ArrayList<InputStream>();
		List<Socket> sl = new ArrayList<Socket>();
		for (int i = 0; i < count; i++) {
			Socket request = new Socket("192.168.10.238", 3113);
			OutputStream os = request.getOutputStream();
			InputStream is = request.getInputStream();
			isl.add(is);
			osl.add(os);
			sl.add(request);
		}
		System.out.println("连接耗时："+(System.currentTimeMillis() - a));

		

		final CountDownLatch latch = new CountDownLatch(count);
		
		final List<String> deviceNos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			String deviceNo = String.format("%014d", i);
			String aa = deviceNo.substring(0, 2);
			String bb = deviceNo.substring(2, 4);
			String cc = deviceNo.substring(4, 6);
			String dd = deviceNo.substring(6, 8);
			String ee = deviceNo.substring(8, 10);
			String ff = deviceNo.substring(10, 12);
			String gg = deviceNo.substring(12, 14);
			if(aa.equals("55")) {
				aa = "5401";
			}
			if(aa.equals("54")) {
				aa = "5402";
			}

			if(bb.equals("55")) {
				bb = "5401";
			}
			if(bb.equals("54")) {
				bb = "5402";
			}

			if(cc.equals("55")) {
				cc = "5401";
			}
			if(cc.equals("54")) {
				cc = "5402";
			}

			if(dd.equals("55")) {
				dd = "5401";
			}
			if(dd.equals("54")) {
				dd = "5402";
			}

			if(ee.equals("55")) {
				ee = "5401";
			}
			if(ee.equals("54")) {
				ee = "5402";
			}

			if(ff.equals("55")) {
				ff = "5401";
			}
			if(ff.equals("54")) {
				ff = "5402";
			}

			if(gg.equals("55")) {
				gg = "5401";
			}
			if(gg.equals("54")) {
				gg = "5402";
			}
			
			deviceNo = aa + bb + cc + dd + ee + ff + gg;
			
			long b = Long.parseLong(deviceNo);
			deviceNo = String.format("%014d", b);
			deviceNos.add(deviceNo);
		}
		
		a = System.currentTimeMillis();
		List<Thread> tl = new ArrayList<Thread>();
		for (int i = 0; i < count; i++) {
			final int j = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					OutputStream os = osl.get(j);
					InputStream is = isl.get(j);
					
					//for(int m=0;m<15;m++) {
						//byte[] bs = hexString2Bytes("554000E0010200000000000012120C0B0A0B11000000000000000000000000000000000000000000000000000000000000000004E80301000000000000A4B655");

						byte[] bs = hexString2Bytes("554000E00102"+deviceNos.get(j)+"120A1D0108040000000000000000000000000000000000000000000000000000000000000000043E88011F0000000000A4B655");
						
						//byte[] bs = hexString2Bytes("554000E00102"+devices.get(j)+"120A1D0108040000000000000000000000000000000000000000000000000000000000000000043E88011F0000000000A4B655");
						try {
							os.write(bs);
							is.read(new byte[1024]);
						} catch (IOException e) {
							e.printStackTrace();
						}
					//}
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