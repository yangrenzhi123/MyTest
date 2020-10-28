package com.yang.test.java.encryption.symmetric;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesTest {
	private static String KEY = "*****";
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		KEY = scanner.next();
		scanner.close();
		
		encrypt("C:/Windows/Temp/新建文件夹/1602425489320.xlsx", "C:/Users/yrz/OneDrive/工资条/工资条.xlsx");
		decrypt("C:/Users/yrz/OneDrive/工资条/工资条.xlsx", "C:/Windows/Temp/新建文件夹/"+System.currentTimeMillis()+".xlsx");
		
		//simpleEncrypt();
	}

	public static void encrypt(String source, String target) throws Exception {
		File f2 = new File(target);
		FileOutputStream fos = new FileOutputStream(f2);
		BufferedOutputStream bos = new BufferedOutputStream(fos);


		byte[] key = KEY.getBytes();
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
		
		
		File f1 = new File(source);
		FileInputStream fis = new FileInputStream(f1);
		byte[] b = new byte[2047];
		while (fis.read(b) != -1) {
			byte[] targetByte = cipher.doFinal(b);
			bos.write(targetByte);
		}

		fis.close();
		bos.close();
	}

	public static void decrypt(String source, String target) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		File f2 = new File(target);
		FileOutputStream fos = new FileOutputStream(f2);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		

		byte[] key = KEY.getBytes();
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
		
		
		File f1 = new File(source);
		FileInputStream fis = new FileInputStream(f1);
		byte[] b = new byte[2048];
		while(fis.read(b) != -1) {
			byte[] targetByte = cipher.doFinal(b);
			bos.write(targetByte);
		}
		
		fis.close();
		bos.close();
	}

	public static void simpleEncrypt() throws InterruptedException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] key = KEY.getBytes();
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
		byte[] t = cipher.doFinal("我是明文".getBytes());
		
		
		String s = new String(t);
		System.out.println(s);
		
		simpleDecrypt(t);
	}

	public static void simpleDecrypt(byte[] beDecrypt) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		byte[] key = KEY.getBytes();
		
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
		byte[] b = cipher.doFinal(beDecrypt);
		

		String s = new String(b);
		System.out.println(s);
	}
}