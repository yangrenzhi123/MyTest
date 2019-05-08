package com.yang.test.java.rsa;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RsaTest2 {
	public static final String KEY_ALGORITHM = "RSA";

	public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		String pKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJYKxMfKaNqPFLh1v5/woFfFDH01C/o4RBgjdQTBoWqy7Bih2DlxUim18BzcXhlK9HSam3W2T4FaKZ8fHF7AMtMCAwEAAQ==";
		System.out.println("公钥：" + pKey);

		String str = "我是原文，我是原文，我是原文！";
		System.out.println("原文:" + str);

		// 使用公钥对数据进行签名
		long a = System.currentTimeMillis();
		byte[] publicKey = Base64.decodeBase64(pKey);
		System.out.println(System.currentTimeMillis() - a);


		a = System.currentTimeMillis();
		byte[] code2 = RsaTest2.encryptByPublicKey(str.getBytes(), publicKey);
		System.out.println(System.currentTimeMillis() - a);
		System.out.println("加密后的数据：" + Base64.encodeBase64String(code2));
	}
}