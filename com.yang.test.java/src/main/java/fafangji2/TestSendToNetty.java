package fafangji2;

/**
 * 箱体发送投放记录
 */
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

public class TestSendToNetty {

	public final static String URL = "192.168.10.239";
	public final static int PORT = 3113;

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
//		int c1 = Integer.parseInt(args[0]);
//		int c2 = Integer.parseInt(args[1]);
		
		int c1 = 16308; //设备数 16254:401--702;10044:401--587
		int c2 = Integer.parseInt(args[0]); //发送次数
		String type = "2";//分类箱==2;回收箱==4

		

//		List<String> devices = new ArrayList<String>();
//		FileReader fr = new FileReader("D:\\Readline");
//		BufferedReader bf = new BufferedReader(fr);
//		String str; // 按行读取字符串
//		while ((str = bf.readLine()) != null) {
//			devices.add(str);
//		}
//		bf.close();
		
		
		

		long a = System.currentTimeMillis();
		List<OutputStream> osl = new ArrayList<OutputStream>();
		List<InputStream> isl = new ArrayList<InputStream>();
		List<Socket> sl = new ArrayList<Socket>();
		for (int i = 0; i < c1; i++) {
			Socket request = new Socket(URL, PORT);
			OutputStream os = request.getOutputStream();
			InputStream is = request.getInputStream();
			isl.add(is);
			osl.add(os);
			sl.add(request);
		}
		System.out.println("连接耗时："+(System.currentTimeMillis() - a));

		
		/*分类：【分类箱00000[0001-2000][01-09]2[01-06]】
		
		00000  0001-2000  01-03 201
		 */
		final CountDownLatch latch = new CountDownLatch(c1);
		
		List<String> deviceNos = new ArrayList<>();//设备类型
		List<String> rqcodes = new ArrayList<>();//二维码
		Random random = new Random();
		for (int i = 401; i <= 702; i++) {
			//String deviceNo = String.format("%014d", 61000000000001L+i);
			//分类箱：【二分类箱00000[0001-2000][01-09]2[01-06]】 
			//回收箱：【可回收箱00000[0001-2000][01-09]4[01-02]】
			//业主二维码：LYZH6[0001-2000][01-09][001-400]01
			for(int j =1;j<=9;j++) {
				
				for(int x =1;x<=6;x++) {
					/**
					 * 反方及：00000022101802
						回收箱：00000022101402
						分类箱：00000022101202
						二维码：LYZH602210107801
					 */
					//String deviceNo = "00000022101202";
					//String rqcode = "LYZH602210107801";
					
					String deviceNo = "00000";
					String tenant = String.format("%04d", i);
					String communityNum = String.format("%02d", j);
					String equipEnd = String.format("%02d", x);
					deviceNo = deviceNo+tenant+communityNum+type+equipEnd;
					String userNum = 100+x+"";
					String rqcode = "LYZH6"+tenant+communityNum+userNum+"01";
					
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
					deviceNos.add(deviceNo);
					rqcodes.add(rqcode);
				}
				
			}
		}
		
		a = System.currentTimeMillis();
		List<Thread> tl = new ArrayList<Thread>();
		for (int i = 0; i < c1; i++) {
			final int j = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					OutputStream os = osl.get(j);
					InputStream is = isl.get(j);
					//LYZH618840405901
					for(int m=0;m<c2;m++) {
						String bin = "554000E00102";
						String equipNo = deviceNos.get(j);//设备号
						String rqcode = rqcodes.get(j);
						//System.out.println("设备号："+equipNo+"; 业主二维码："+rqcode);
						String type = "04";//01：玻璃/02：塑料/03：纸张/04：金属/05：厨余/06：其他/0A：有毒有害/0B：可回收/0C：可腐烂/0D：不可腐烂
						String weight = "E80301";//E803(10.00kg)      9C04(25.00kg)
						//weight = "409C01";//减重 409C（-255.36kg）
						//weight = "A00F01";//超重 
						String other = "000000000000";//其他预留位
						String area = timeTo16()+rqcodeTo16(rqcode)+type+weight+other;
						String CRC_TOP = "A4";
						String CRC_Low = "B6";
						String end = "55";
						String protocol_55= bin+equipNo+area+CRC_TOP+CRC_Low+end;
						byte[] bs = hexString2Bytes(protocol_55);
																								   //时间1301120F0B04                  //LYZH181225000101                         //垃圾类型
						//byte[] bs = hexString2Bytes("554000E00102"+/*deviceNos.get(j)*/"00000011223366"+timeTo16()+"4c595a4831383132323530303031303100000000000000000000000000000000"+"04"+"E80300000000000000A4B655");
						try {
							os.write(bs);
							is.read(new byte[1024]);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						/*try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
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
		//System.out.println("当前时间===="+ret);
		return ret;
	}
}