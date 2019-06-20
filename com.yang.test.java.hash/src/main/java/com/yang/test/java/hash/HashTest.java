package com.yang.test.java.hash;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		md5();
		sha1();
		sha256();
	}

	public static void md5() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update("123456".getBytes());
		System.out.println(new BigInteger(1, md.digest()).toString(16)); // 16表示16进制
	}

	public static void sha1() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update("123456".getBytes());
		System.out.println(new BigInteger(1, md.digest()).toString(16)); // 16表示16进制
	}

	public static void sha256() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update("123456".getBytes());
		System.out.println(new BigInteger(1, md.digest()).toString(16)); // 16表示16进制
	}

	public static void t1() throws NoSuchAlgorithmException {
		String stringA = "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
		String stringSignTemp = stringA + "&key=192006250b4c09247ec02edce69f6a2d";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(stringSignTemp.getBytes());
		String sign = new BigInteger(1, md.digest()).toString(16).toUpperCase();
		System.out.println(sign);
	}

	public static void md5ForFile() throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		File a = new File("C:/1.sql");
		InputStream is = new FileInputStream(a);
		System.out.println(new BigInteger(1, md.digest()).toString(16));
		is.close();

		a = new File("C:/2.sql");
		is = new FileInputStream(a);
		System.out.println(new BigInteger(1, md.digest()).toString(16));
		is.close();
	}
}