package com.yang.test.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
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
	
	public static void t2(){
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.format(null);
			
			System.out.println(1);
		}catch(Exception e){
			e.printStackTrace();
		}
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
	
	public static void tt(){
		System.out.println(Math.sqrt(9));
	}

	public static void main111(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		System.out.println(t.getYear() + 1900);
	}
	
	public static void main1(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
		int str1=123;
		DecimalFormat df=new DecimalFormat("000000");
		String str2=df.format(str1);
		System.out.println(str2);
	}

	public static void main11(String[] args) throws FileNotFoundException {
		Random r = new Random();
		for(int i = 0;i<100;i++){
			System.out.println(r.nextInt(2));
		}
	}

	public static void searchFile(String directory) {
		File f = new File(directory);
		for (String item : f.list()) {
			File f2 = new File(directory + "/" + item);
			if (f2.isDirectory()) {
				searchFile(directory + "/" + item);
			} else {
				System.out.println(directory + "/" + item);
			}
		}
	}

	public static void test27() {
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.version"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.dir"));
		System.out.println();
	}

	public static void test26() {
		BigDecimal b = new BigDecimal(100000.0000001);
		b = b.setScale(2, BigDecimal.ROUND_DOWN);
		System.out.println(b.scale());
		System.out.println(b.toPlainString());
	}

	public static void test25() {
		Integer i = new Integer(25);
		System.out.println(i == new Integer(25));
	}

	public static void test24() throws IOException {
		File f = new File(
				"D:\\projects\\test\\com.yang.test.java\\src\\main\\java\\com\\yang\\test\\java\\conf.properties");
		InputStream is = new FileInputStream(f);

		Properties prop = new Properties();
		prop.load(is);

		String s = (String) prop.get("abc.fileFolder");

		File f2 = new File(s);
		System.out.println(f2);
	}

	public static void test23() throws IOException {
		File file = new File("D:\\7505.6644500029583378.171537279779776-D-03");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] b = new byte[is.available()];
			is.read(b);

			String s = new String(b, "GBK");
			System.out.println(s);

			if (is != null)
				is.close();
		} catch (Exception e) {
			if (is != null)
				is.close();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static void startT() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				Timer timer = new Timer("123");
				timer.schedule(new TimerTask() {
					public void run() {
						System.out.println(System.currentTimeMillis());
					}
				}, 0, 10000);
			}
		});
		t.setName("定时线程");
		t.start();
	}

	public static void z1() {
		String str = "中国";
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]+$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
	}

	public static void z2() {
		String str = "1234fdfgfg";
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(str);
		System.out.println(m.matches());
	}

	public static void z3() {
		String str = "ceponline...111";
		Pattern pattern = Pattern.compile("^[a-z0-9A-Z]+$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
	}

	public static synchronized void z4() {
		System.out.println(1);
	}

	public static void test22() {
		System.out.println(new BigDecimal(6.105).multiply(new BigDecimal(10000)));
	}

	public static void test21() {
		Timer timer = new Timer("定时程序 - 银行交易明细及余额查询");
		timer.schedule(new TimerTask() {
			public void run() {
				z4();
			}
		}, 0, 10000);
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

	public static void test15() {
		/*
		 * Thread t = new Thread(new Runnable() { public void run() {
		 * System.out.println(111); } }); t.setName("测试线程1"); t.start();
		 */

		Thread t = new Thread(new Runnable() {
			public void run() {
				startT();
			}
		});
		t.setName("定时线程");
		t.start();
	}

	public static void test14() {
		z1();
		z2();
		z3();
	}

	/**
	 * 线程同步测试
	 * 
	 */
	public static void test13() {
		for (int i = 0; i < 10; i++) {
			startT();
		}
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