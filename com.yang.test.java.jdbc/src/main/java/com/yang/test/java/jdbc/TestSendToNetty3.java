package com.yang.test.java.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestSendToNetty3 {

	public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
	public static int count;
	public static int totalCount;

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException, SQLException {
		System.out.println("开始加载sbbh...");
		long c = System.currentTimeMillis();
		List<String> sbbhList = GetSbbh.getSbbh();
		System.out.println("sbbh加载完毕，耗时" + (System.currentTimeMillis() - c));

		int c1 = 10; // 设备数
		int size = sbbhList.size();
		if (size < c1) {
			c1 = size;
		}

		List<OutputStream> osl = new ArrayList<OutputStream>();
		List<InputStream> isl = new ArrayList<InputStream>();
		List<Socket> sl = new ArrayList<Socket>();
		for (int i = 0; i < c1; i++) {
			Socket request = new Socket("192.168.10.239", 3113);
			OutputStream os = request.getOutputStream();
			InputStream is = request.getInputStream();
			isl.add(is);
			osl.add(os);
			sl.add(request);
		}

		List<String> deviceNos = new ArrayList<>();
		for (int i = 0; i < c1; i++) {
			int z = new Random().nextInt(sbbhList.size());
			String deviceNo = sbbhList.get(z);
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
			sbbhList.remove(z);
		}
		
		for (int i = 0; i < c1; i++) {
			OutputStream os = osl.get(i);
			InputStream is = isl.get(i);
			System.out.println(deviceNos.get(i));
			
			byte[] bs = hexString2Bytes("552000A00002" + deviceNos.get(i) + "31322C3534303237383738342C302C30030455");
			os.write(bs);
			byte[] registryResult = new byte[1024];
			is.read(registryResult);
			System.out.println("注册结果："+new String(registryResult));
		}
		
		Thread.sleep(30000L);
		
		for (Socket request : sl) {
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
	 * 获取_rqcode 16进制 32位 不足补0
	 */
	public static String rqcodeTo16(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch).toUpperCase();
			str = str + s4;
		}
		if (str.length() < 64) {
			int num = 64 - str.length();
			for (int i = 0; i < num; i++) {
				str = str + "0";
			}
		}
		return str;
	}
}