package com.yang.test.java.socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("resource")
public class SimpleClient {

	public static Socket request = null;

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {

		Socket request = new Socket("115.236.86.197", 9038);
		OutputStream os = request.getOutputStream();
		InputStream is = request.getInputStream();
		
		while(true) {
			byte[] bs = hexString2Bytes("554000E0010200011160111111"+getNowHex()+"4C595A483135303838363532303930310000000000000000000000000000000003E80301000000000000441455");
			
			os.write(bs);
			byte[] c = new byte[1024];
			is.read(c);
			String s = new String(c);
			System.out.println(s);
			
			Thread.sleep(10000L);
		}
	}
	
	
	public static String getNowHex() {
		DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
		String s = df.format(new Date());
		String s2= intToHex(Integer.parseInt(s.substring(0, 2)))+intToHex(Integer.parseInt(s.substring(2, 4)))
		+intToHex(Integer.parseInt(s.substring(4, 6)))+intToHex(Integer.parseInt(s.substring(6, 8)))+intToHex(Integer.parseInt(s.substring(8, 10)))+intToHex(Integer.parseInt(s.substring(10, 12)));
		return s2;
	}
	
	public static String intToHex(int i) {
		String s = Integer.toHexString(i).toUpperCase();
		if(s.length() == 1) {
			s = "0"+s;
		}
		return s;
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