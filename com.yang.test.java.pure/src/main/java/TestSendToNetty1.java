import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestSendToNetty1 {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Random r = new Random();
		
		int c1 = 1; //设备数
		int c2 = 1; //发送次数

		long a = System.currentTimeMillis();
		List<OutputStream> osl = new ArrayList<OutputStream>();
		List<InputStream> isl = new ArrayList<InputStream>();
		List<Socket> sl = new ArrayList<Socket>();
		for (int i = 0; i < c1; i++) {
			Socket request = new Socket("192.168.10.89", 3113);
			OutputStream os = request.getOutputStream();
			InputStream is = request.getInputStream();
			isl.add(is);
			osl.add(os);
			sl.add(request);
		}
		System.out.println("连接耗时："+(System.currentTimeMillis() - a));

		

		final CountDownLatch latch = new CountDownLatch(c1);

		
		a = System.currentTimeMillis();
		List<Thread> tl = new ArrayList<Thread>();
		for (int i = 0; i < c1; i++) {
			final int j = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					OutputStream os = osl.get(j);
					InputStream is = isl.get(j);
					
					for(int m=0;m<c2;m++) {
						String s = 
						timeTo16()
						+"LYZH201908172001" //卡号
						+"03" // 垃圾类型
						+turnHex(intToHex(r.nextInt(1000)))
						+"00" //重量
						+"000000000000A4B655";
						StringBuilder sb = new StringBuilder();
						for(int i=0;i<s.length()/2;i++) {
							String s1 = s.substring(i*2, (i+1)*2);
							if(s1.equals("55")) {
								s1 = "5401";
							}
							if(s1.equals("54")) {
								s1 = "5402";
							}
							sb.append(s1);
						}
						byte[] bs = hexString2Bytes("554000E00102"+"00012190100576"+sb.toString()+"55");
						try {
							os.write(bs);
							is.read(new byte[1024]);
						} catch (IOException e) {
							e.printStackTrace();
						}
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
	
	public static String turnHex(String hex) {
		String s2 = hex.substring(0, 2);
		String s1 = hex.substring(2, 4);
		return s1 + s2;
	}
	
	public static String intToHex(int i) {
		String s = Integer.toHexString(i).toUpperCase();
		if (s.length() == 1) {
			s = "000" + s;
		}
		if (s.length() == 2) {
			s = "00" + s;
		}
		if (s.length() == 3) {
			s = "0" + s;
		}
		return s;
	}
	
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/*
	 * 获取当前时间的16进制
	 */
	public static String timeTo16() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		String time1[] = time.split(" ");
		String time2 = time1[0];
		String time3 = time1[1];
		String time4[] = time2.split("-");
		String time5[] = time3.split(":");
		int yer = Integer.parseInt(time4[0]);
		String yerHex = Integer.toHexString(yer).toUpperCase();
		if (yerHex.length() < 2) {
			yerHex = "0" + yerHex;
		}
		int mon = Integer.parseInt(time4[1]);
		String monHex = Integer.toHexString(mon).toUpperCase();
		if (monHex.length() < 2) {
			monHex = "0" + monHex;
		}
		int da = Integer.parseInt(time4[2]);
		String daHex = Integer.toHexString(da).toUpperCase();
		if (daHex.length() < 2) {
			daHex = "0" + daHex;
		}
		int hh = Integer.parseInt(time5[0]);
		String hhHex = Integer.toHexString(hh).toUpperCase();
		if (hhHex.length() < 2) {
			hhHex = "0" + hhHex;
		}
		int mm = Integer.parseInt(time5[1]);
		String mmHex = Integer.toHexString(mm).toUpperCase();
		if (mmHex.length() < 2) {
			mmHex = "0" + mmHex;
		}
		int ss = Integer.parseInt(time5[2]);
		String ssHex = Integer.toHexString(ss).toUpperCase();
		if (ssHex.length() < 2) {
			ssHex = "0" + ssHex;
		}
		String ret = yerHex + monHex + daHex + hhHex + mmHex + ssHex;
		return ret;
	}
	
	/*
	 * 获取_rqcode 16进制   32位 不足补0
	 */
	public static String rqcodeTo16(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch);
	        str = str + s4;
	    }
	    if(str.length()<64) {
	    	int num = 64-str.length();
	    	for(int i = 0;i<num;i++) {
	    		str = str+"0";
	    	}
	    }
	    return str;
	}
}