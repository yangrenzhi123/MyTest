package com.yang.test.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		int i;
		
		if((i = t()) == 1){
			System.out.println();
		}else{
			System.out.println();
		}
	}
	
	public static int t (){
		return 2;
	}
	
	public static void main1111(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		T1 t1 = (T1)Class.forName("com.yang.test.java.T2").newInstance();
		t1.setId(2);
		
		System.out.println(t1);
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

	public static void main3(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
		// 生成一个MD5加密计算摘要
		MessageDigest md = MessageDigest.getInstance("MD5");
		// 计算md5函数
		md.update("123".getBytes());
		// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
		// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
		System.out.println(new BigInteger(1, md.digest()).toString(16));
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

	@SuppressWarnings("restriction")
	public static void test20() throws UnsupportedEncodingException, IOException {
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		System.out.println(new String(decoder.decodeBuffer("MTIzNDU="), "GBK"));
		;
	}

	public static void test19() {
		System.out.println("1.1.1.1".indexOf("."));
	}

	@SuppressWarnings("restriction")
	public static void test18() throws IOException {
		String c = "72:65:71:44:61:74:61:3d:50:44:39:34:62:57:77:67:49:48:5a:6c:63:6e:4e:70:62:32:34:39:49:6a:45:75:4d:43:49:67:5a:57:35:6a:62:32:52:70:62:6d:63:39:49:6b:64:43:53:79:49:67:50:7a:34:4b:50:45:4e:4e:55:7a:34:4b:50:47:56:69:50:67:6f:38:63:48:56:69:50:67:6f:38:0a:56:48:4a:68:62:6e:4e:44:62:32:52:6c:50:6c:42:42:57:55:56:4f:56:44:77:76:56:48:4a:68:62:6e:4e:44:62:32:52:6c:50:67:6f:38:51:30:6c:54:50:6a:49:77:4d:54:45:35:4d:44:41:77:4d:44:59:30:4d:7a:6b:79:4f:54:77:76:51:30:6c:54:50:67:6f:38:0a:51:6d:46:75:61:30:4e:76:5a:47:55:2b:4d:54:41:79:50:43:39:43:59:57:35:72:51:32:39:6b:5a:54:34:4b:50:45:6c:45:50:6e:4e:70:59:32:68:76:64:57:4e:6c:63:32:68:70:4c:6e:6b:75:4d:6a:41:78:4d:54:77:76:53:55:51:2b:43:6a:78:55:63:6d:46:75:0a:52:47:46:30:5a:54:34:79:4d:44:45:32:4d:54:41:7a:4d:54:77:76:56:48:4a:68:62:6b:52:68:64:47:55:2b:43:6a:78:55:63:6d:46:75:56:47:6c:74:5a:54:34:78:4e:6a:4d:79:4d:44:55:7a:4e:44:4d:38:4c:31:52:79:59:57:35:55:61:57:31:6c:50:67:6f:38:0a:5a:6c:4e:6c:63:57:35:76:50:6a:49:77:4d:54:59:78:4d:44:49:31:4d:44:41:78:50:43:39:6d:55:32:56:78:62:6d:38:2b:43:6a:78:54:5a:58:4a:70:59:57:78:4f:62:7a:35:44:54:55:30:32:4d:44:59:30:4d:44:63:77:4d:44:41:38:4c:31:4e:6c:63:6d:6c:68:0a:62:45:35:76:50:67:6f:38:55:6d:56:30:51:32:39:6b:5a:54:34:77:50:43:39:53:5a:58:52:44:62:32:52:6c:50:67:6f:38:55:6d:56:30:54:58:4e:6e:50:6a:77:76:55:6d:56:30:54:58:4e:6e:50:67:6f:38:4c:33:42:31:59:6a:34:4b:50:47:39:31:64:44:34:4b:0a:50:45:39:75:62:45:4a:68:64:45:59:2b:4d:54:77:76:54:32:35:73:51:6d:46:30:52:6a:34:4b:50:46:4e:6c:64:48:52:73:5a:55:31:76:5a:47:55:2b:4d:44:77:76:55:32:56:30:64:47:78:6c:54:57:39:6b:5a:54:34:4b:50:46:52:76:64:47:46:73:54:6e:56:74:0a:50:6a:45:38:4c:31:52:76:64:47:46:73:54:6e:56:74:50:67:6f:38:56:47:39:30:59:57:78:42:62:58:51:2b:4d:54:49:77:50:43:39:55:62:33:52:68:62:45:46:74:64:44:34:4b:50:46:4a:6c:63:46:4a:6c:63:32:56:79:64:6d:56:6b:4d:54:34:38:4c:31:4a:6c:0a:63:46:4a:6c:63:32:56:79:64:6d:56:6b:4d:54:34:4b:50:46:4a:6c:63:46:4a:6c:63:32:56:79:64:6d:56:6b:4d:6a:34:38:4c:31:4a:6c:63:46:4a:6c:63:32:56:79:64:6d:56:6b:4d:6a:34:4b:50:48:4a:6b:50:67:6f:38:61:56:4e:6c:63:57:35:76:50:6a:41:38:0a:4c:32:6c:54:5a:58:46:75:62:7a:34:4b:50:45:39:79:5a:47:56:79:54:6d:38:2b:4d:54:77:76:54:33:4a:6b:5a:58:4a:4f:62:7a:34:4b:50:46:4a:6c:61:57:31:69:64:58:4a:7a:5a:55:35:76:50:6a:77:76:55:6d:56:70:62:57:4a:31:63:6e:4e:6c:54:6d:38:2b:0a:43:6a:78:53:5a:57:6c:74:59:6e:56:79:63:32:56:4f:64:57:30:2b:50:43:39:53:5a:57:6c:74:59:6e:56:79:63:32:56:4f:64:57:30:2b:43:6a:78:54:64:47:46:79:64:45:52:68:64:47:55:2b:50:43:39:54:64:47:46:79:64:45:52:68:64:47:55:2b:43:6a:78:54:0a:64:47:46:79:64:46:52:70:62:57:55:2b:50:43:39:54:64:47:46:79:64:46:52:70:62:57:55:2b:43:6a:78:51:59:58:6c:55:65:58:42:6c:50:6a:49:38:4c:31:42:68:65:56:52:35:63:47:55:2b:43:6a:78:51:59:58:6c:42:59:32:4e:4f:62:7a:34:79:4d:44:45:78:0a:4d:44:41:79:4e:54:41:35:4d:6a:41:77:4d:44:41:30:4d:54:49:78:50:43:39:51:59:58:6c:42:59:32:4e:4f:62:7a:34:4b:50:46:42:68:65:55:46:6a:59:30:35:68:62:57:56:44:54:6a:37:42:32:72:54:79:78:75:76:52:71:64:44:53:78:4d:54:53:77:4d:43:32:0a:30:65:71:30:7a:63:6a:72:76:66:72:43:79:72:6d:2b:50:43:39:51:59:58:6c:42:59:32:4e:4f:59:57:31:6c:51:30:34:2b:43:6a:78:51:59:58:6c:42:59:32:4e:4f:59:57:31:6c:52:55:34:2b:50:43:39:51:59:58:6c:42:59:32:4e:4f:59:57:31:6c:52:55:34:2b:0a:43:6a:78:53:5a:57:4e:42:59:32:4e:4f:62:7a:34:33:4d:44:51:79:4e:54:49:31:4e:44:59:34:4d:54:49:38:4c:31:4a:6c:59:30:46:6a:59:30:35:76:50:67:6f:38:55:6d:56:6a:51:57:4e:6a:54:6d:46:74:5a:55:4e:4f:50:73:48:57:73:38:6d:36:7a:7a:77:76:0a:55:6d:56:6a:51:57:4e:6a:54:6d:46:74:5a:55:4e:4f:50:67:6f:38:55:6d:56:6a:51:57:4e:6a:54:6d:46:74:5a:55:56:4f:50:6a:77:76:55:6d:56:6a:51:57:4e:6a:54:6d:46:74:5a:55:56:4f:50:67:6f:38:55:33:6c:7a:53:55:39:47:62:47:63:2b:4d:6a:77:76:0a:55:33:6c:7a:53:55:39:47:62:47:63:2b:43:6a:78:4a:63:31:4e:68:62:57:56:44:61:58:52:35:50:6a:45:38:4c:30:6c:7a:55:32:46:74:5a:55:4e:70:64:48:6b:2b:43:6a:78:51:63:6d:39:77:50:6a:45:38:4c:31:42:79:62:33:41:2b:43:6a:78:53:5a:57:4e:4a:0a:51:30:4a:44:51:32:39:6b:5a:54:34:77:4d:44:41:77:50:43:39:53:5a:57:4e:4a:51:30:4a:44:51:32:39:6b:5a:54:34:4b:50:46:4a:6c:59:30:4e:70:64:48:6c:4f:59:57:31:6c:50:74:62:51:79:62:33:4b:30:44:77:76:55:6d:56:6a:51:32:6c:30:65:55:35:68:0a:62:57:55:2b:43:6a:78:53:5a:57:4e:43:59:57:35:72:54:6d:38:2b:4d:54:41:30:4e:6a:41:7a:4d:44:51:35:4d:6a:4d:77:50:43:39:53:5a:57:4e:43:59:57:35:72:54:6d:38:2b:43:6a:78:53:5a:57:4e:43:59:57:35:72:54:6d:46:74:5a:54:37:57:30:4c:6e:36:0a:30:76:6a:51:30:4c:76:77:76:75:61:2f:71:72:65:69:78:2f:6a:57:70:39:44:51:50:43:39:53:5a:57:4e:43:59:57:35:72:54:6d:46:74:5a:54:34:4b:50:45:4e:31:63:6e:4a:55:65:58:42:6c:50:6a:41:77:4d:54:77:76:51:33:56:79:63:6c:52:35:63:47:55:2b:0a:43:6a:78:51:59:58:6c:42:62:58:51:2b:4d:54:49:77:50:43:39:51:59:58:6c:42:62:58:51:2b:43:6a:78:56:63:32:56:44:62:32:52:6c:50:6a:77:76:56:58:4e:6c:51:32:39:6b:5a:54:34:4b:50:46:56:7a:5a:55:4e:4f:50:72:4c:69:79:74:51:38:4c:31:56:7a:0a:5a:55:4e:4f:50:67:6f:38:52:57:35:54:64:57:31:74:59:58:4a:35:50:6a:77:76:52:57:35:54:64:57:31:74:59:58:4a:35:50:67:6f:38:55:47:39:7a:64:46:4e:6a:63:6d:6c:77:64:44:34:38:4c:31:42:76:63:33:52:54:59:33:4a:70:63:48:51:2b:43:6a:78:54:0a:64:57:31:74:59:58:4a:35:50:6a:77:76:55:33:56:74:62:57:46:79:65:54:34:4b:50:46:4a:6c:5a:6a:34:38:4c:31:4a:6c:5a:6a:34:4b:50:45:39:79:5a:57:59:2b:50:43:39:50:63:6d:56:6d:50:67:6f:38:52:56:4a:51:55:33:46:75:50:6a:77:76:52:56:4a:51:0a:55:33:46:75:50:67:6f:38:51:6e:56:7a:51:32:39:6b:5a:54:34:38:4c:30:4a:31:63:30:4e:76:5a:47:55:2b:43:6a:78:46:55:6c:42:6a:61:47:56:6a:61:32:35:76:50:6a:77:76:52:56:4a:51:59:32:68:6c:59:32:74:75:62:7a:34:4b:50:45:4e:79:64:6d:39:31:0a:61:46:52:35:63:47:55:2b:50:43:39:44:63:6e:5a:76:64:57:68:55:65:58:42:6c:50:67:6f:38:51:33:4a:32:62:33:56:6f:54:6d:46:74:5a:54:34:38:4c:30:4e:79:64:6d:39:31:61:45:35:68:62:57:55:2b:43:6a:78:44:63:6e:5a:76:64:57:68:4f:62:7a:34:38:0a:4c:30:4e:79:64:6d:39:31:61:45:35:76:50:67:6f:38:55:6d:56:7a:64:57:78:30:50:6a:59:38:4c:31:4a:6c:63:33:56:73:64:44:34:4b:50:47:6c:53:5a:58:52:44:62:32:52:6c:50:6b:49:77:4d:6a:63:34:50:43:39:70:55:6d:56:30:51:32:39:6b:5a:54:34:4b:0a:50:47:6c:53:5a:58:52:4e:63:32:63:2b:79:63:2f:4c:7a:64:44:51:77:2f:75:68:6f:74:44:51:75:73:57:79:75:39:4b:37:31:73:49:38:4c:32:6c:53:5a:58:52:4e:63:32:63:2b:43:6a:78:53:5a:58:42:53:5a:58:4e:6c:63:6e:5a:6c:5a:44:4d:2b:50:43:39:53:0a:5a:58:42:53:5a:58:4e:6c:63:6e:5a:6c:5a:44:4d:2b:43:6a:78:53:5a:58:42:53:5a:58:4e:6c:63:6e:5a:6c:5a:44:51:2b:50:43:39:53:5a:58:42:53:5a:58:4e:6c:63:6e:5a:6c:5a:44:51:2b:43:6a:77:76:63:6d:51:2b:43:6a:77:76:62:33:56:30:50:67:6f:38:0a:4c:32:56:69:50:67:6f:38:4c:30:4e:4e:55:7a:34:4b";
		String[] a = c.split(":");
		byte[] b = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = (byte) Integer.parseInt(a[i], 16);
		}

		String r = new String(b, "UTF-8");
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		String d = new String(decoder.decodeBuffer(r), "GBK");
		System.out.println(d);
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

	public static void test7() {
		String s = (String) null;
		System.out.println(s);
	}

	public static void test6() {
		File file = new File("D:/222");
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static void test5() throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");
		System.out.println(fmt.format(new Date()));
		System.out.println(fmt.parse("2016-07-19-16.44.30.711188"));
	}

	public static void test4() {
		List<String> l = new ArrayList<String>();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		l.add("5");
		l.add("6");
		String[] s = l.toArray(new String[] {});
		System.out.println(s);
	}

	public static void test3() {
		String s = "1.0000";
		System.out.println(s.split("\\.")[0]);
	}

	public static void test2() {
		System.getProperty("endPoint", "http://wsbeta.fedex.com:443/web-services/openship");
	}

	public static void test() {
		System.out.println(new Date().toLocaleString());
	}
}

class T1 {
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}
}

class T2 extends T1 {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}