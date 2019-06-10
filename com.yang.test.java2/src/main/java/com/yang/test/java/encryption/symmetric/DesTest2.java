package com.yang.test.java.encryption.symmetric;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesTest2 {

	private static final String KEY = "1234567890";

	public static void main(String[] args) throws Exception {
		simpleEncrypt();
	}

	public static void simpleEncrypt() throws InterruptedException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] key = KEY.getBytes();
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey));
		byte[] b = cipher.doFinal("12345678".getBytes());
		System.out.println(b.length);
		
		
		String s = new String(b);
		System.out.println(s);
		
		simpleDecrypt(b);
	}

	public static void simpleDecrypt(byte[] beDecrypt) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		byte[] key = KEY.getBytes();
		
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey));
		byte[] b = cipher.doFinal(beDecrypt);
		

		String s = new String(b);
		System.out.println(s);
	}
}