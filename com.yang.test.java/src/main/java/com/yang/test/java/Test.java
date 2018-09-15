package com.yang.test.java;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
		System.out.println(URLDecoder.decode("%E6%B1%BD%E8%BD%A6%E4%B9%8B%E5%AE%B6", "utf-8"));

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