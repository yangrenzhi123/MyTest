package com.yang.test.java;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Test {
	public static String s = "123";

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		//System.out.println(convertHexToString("4C595A4831353038383635323039303100000000000000000000000000000000"));
		//System.out.println(strTo16("LYZH150886520901"));
		
		//jinzhi();
		
		System.out.println(hexString2Bytes("FF")[0]);
		//System.out.println((byte) 0xFF);
		
		//test();
		
		//userFlag2Hex();
		
		
		//test9();
	}

	public static String userFlag2Hex() {
		String s = strTo16("LYZH150886520901");
		String t = "";
		for (int i = 0; i < (64 - s.length()); i++) {
			t = t + "0";
		}
		return s + t;
	}

	public static void test() {
		DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
		String s = df.format(new Date());
		String s2= intToHex(Integer.parseInt(s.substring(0, 2)))+intToHex(Integer.parseInt(s.substring(2, 4)))
		+intToHex(Integer.parseInt(s.substring(4, 6)))+intToHex(Integer.parseInt(s.substring(6, 8)))+intToHex(Integer.parseInt(s.substring(8, 10)))+intToHex(Integer.parseInt(s.substring(10, 12)));
		System.out.println(s2);
	}
	
	public static String strTo16(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch).toUpperCase();
	        str = str + s4;
	    }
	    return str;
	}
	
	
	// 16进制字符串转AscII字符串
	public static String convertHexToString(String hex) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < hex.length() - 1; i += 2) {
			String output = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);
			temp.append(decimal);
		}
		return sb.toString();
	}

	public static void jinzhi() {
		// 10转16进制
		System.out.println(intToHex(1000));

		// 16转10进制
		System.out.println(Integer.valueOf("FC18", 16));
		
		// 解决负数问题
		BigInteger bi = new BigInteger("03E8", 16);
		System.out.println(bi.intValue());
		
		// 字节转16
        String s = Integer.toHexString("E".getBytes()[0] & 0xFF);
        if (s.length() == 1){
            System.out.println("0" + s);
        }else{
            System.out.println(s);
        }
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

	public static void t3(){
		BigDecimal x1 = new BigDecimal(5);
		BigDecimal x2 = new BigDecimal(6);
		BigDecimal x3 = new BigDecimal(7);

		BigDecimal y1 = new BigDecimal(5);
		BigDecimal y2 = new BigDecimal(6);
		BigDecimal y3 = new BigDecimal(7);
		
		BigDecimal x1y1 = x1.multiply(y1);
		BigDecimal x2y2 = x2.multiply(y2);
		BigDecimal x3y3 = x3.multiply(y3);
		
		BigDecimal x1x1 = x1.multiply(x1);
		BigDecimal x2x2 = x2.multiply(x2);
		BigDecimal x3x3 = x3.multiply(x3);
		BigDecimal y1y1 = y1.multiply(y1);
		BigDecimal y2y2 = y2.multiply(y2);
		BigDecimal y3y3 = y3.multiply(y3);
		
		BigDecimal addResult1 = x1y1.add(x2y2).add(x3y3);
		BigDecimal addResult2 = x1x1.add(x2x2).add(x3x3);
		BigDecimal addResult3 = y1y1.add(y2y2).add(y3y3);
		
		double s1 = Math.sqrt(addResult2.doubleValue());
		double s2 = Math.sqrt(addResult3.doubleValue());
		
		BigDecimal result = addResult1.divide(new BigDecimal(s1).multiply(new BigDecimal(s2)), 16, BigDecimal.ROUND_DOWN);
		System.out.println(result);
	}

	public static void t(){
		List<BigDecimal> l = new ArrayList<BigDecimal>();
		l.add(new BigDecimal(2));
		l.add(new BigDecimal(1));
		
		Collections.sort(l, new Comparator<BigDecimal>() {
			public int compare(BigDecimal o1, BigDecimal o2) {
				return o1.compareTo(o2) == 1 ? 1: -1;
			}
		});
		
		System.out.println();
	}

	public static void test17() {
		tt(new Test());
	}

	public static void tt(Object a) {
		System.out.println(a.getClass());
	}

	public static void test16() {
		List<Integer> l = new ArrayList<Integer>(5);
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		System.out.println();
	}

	public static void test11() {
		List<String> l = new ArrayList<String>();
		l.add("CM");

		System.out.println(l.contains("cm"));
	}

	public static void test10() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(new Date(1441526075000L)));

		// System.out.println(dateFormat.parse("2015-09-28
		// 00:00:00").getTime());
	}

	public static void test9() throws UnsupportedEncodingException {
		encode("益生股份");
		System.out.println(URLDecoder.decode("%E8%B4%B9%E7%94%A8%E5%90%8D%E7%A7%B0%E4%B8%8D%E8%83%BD%E9%87%8D%E5%A4%8D%EF%BC%8C%E8%AF%B7%E9%87%8D%E6%96%B0%E9%80%89%E6%8B%A9%21", "utf-8"));

		/*
		 * System.out.println(URLDecoder.decode("%3D", "utf-8"));
		 * System.out.println(URLEncoder.encode("广", "gbk"));
		 * System.out.println(URLEncoder.encode("广", "utf-8"));
		 * System.out.println(URLEncoder.encode("广", "unicode"));
		 */
	}

	public static void hexToInt(String value) {
		String[] a = value.split(",");

		String result = "";
		for (String s : a) {
			result = result + Integer.parseInt(s, 16) + ",";
		}

		System.out.println(result);
	}

	public static void intToHex(String value) {
		String[] a = value.split(", ");

		String result = "";
		for (int i = 0; i < a.length; i++) {
			result = result + Integer.toHexString(Integer.parseInt(a[i])) + ",";
		}

		System.out.println(result);
	}

	public static void encode(String value) throws UnsupportedEncodingException {
		System.out.println(value + "的UTF-8编码为" + URLEncoder.encode(value, "utf-8"));
		System.out.println(value + "的GBK编码为" + URLEncoder.encode(value, "gbk"));
		System.out.println(value + "的二进制" + value.getBytes());

		String[] b = new String[value.getBytes().length];
		for (int i = 0; i < b.length; i++) {
			byte item = value.getBytes()[i];
			b[i] = Integer.toHexString(Integer.parseInt(item + ""));
		}
		System.out.println(value + "的十六进制" + b);
	}

	public static void test8() {
		System.out.println(Test.class.getResource("/").getPath());
	}
}