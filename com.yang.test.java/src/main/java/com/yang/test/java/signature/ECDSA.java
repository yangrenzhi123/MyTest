package com.yang.test.java.signature;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

@SuppressWarnings({ "restriction" })
public class ECDSA {

	private static String src = "我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是";

	public static void main(String[] args) {
		jdkECDSA();
	}

	public static byte[] decryptBASE64(String key) {
		return Base64.decodeBase64(key);
	}

	public static String encryptBASE64(byte[] key) {
		return Base64.encodeBase64String(key);
	}
	
	public static void jdkECDSA() {
//		SHA1withECDSA
//		私钥：MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDKyuPTd3fqxj3JiuC+LjHpu1BNUJ3CAzNKWk85OkuKQA==
//		签名：3046022100F7DB1982A69217AABC7F8C3C8E8B325D275D424091FBC438FB1021A637E0ED6D022100E0516C5FC8602251AE9ABA8EE34EB09B8F0E86EBE7468F96B05F3E9C6CC609F6
//		公钥：MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE4kZzizUnz2g4D+VxhFv43UkHaWF+IkpdQ3DJDH6sS9Tb6/kZhNrYEHimLSZF2ToCBnZg0UOBytKlKTOf7DkTSA==
//		验证：true
		
//		SHA256withECDSA
//		私钥：MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCCcN0ske2U1aW5QU1ez10UoadnrjPAtstQarPbwFjNC4Q==
//		签名：3046022100B44917942EBE23C45480E3DA4A6647D38E29E6D6FC18D7F121354175B8A2DF07022100BDD75EB90BD2C463CF2455C2312F832BCBA459D05910D4B4A1619B3BD2FF2C25
//		公钥：MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE59jtLL+HSYRqOLYDVQvgrKAn8pzx0KM9HrvuATwbshGBEIV9xp0KuhDLrBc4lLNNTc8kzo2pRjyewPLakvwlNA==
//		验证：true
		
		
		
		try {
			String sf = "SHA1withECDSA";
			
			// 1.初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
			keyPairGenerator.initialize(256);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
			ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();

			// 2.执行签名
			byte[] privateKeyByte = ecPrivateKey.getEncoded();
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
			System.out.println("私钥：" + encryptBASE64(privateKeyByte));
			KeyFactory keyFactory = KeyFactory.getInstance("EC");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance(sf);
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] res = signature.sign();
			System.out.println("签名：" + HexBin.encode(res));

			// 3.验证签名
			byte[] publicKeyByte = ecPublicKey.getEncoded();
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
			System.out.println("公钥：" + encryptBASE64(publicKeyByte));
			keyFactory = KeyFactory.getInstance("EC");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance(sf);
			signature.initVerify(publicKey);
			signature.update(src.getBytes());
			boolean bool = signature.verify(res);
			System.out.println("验证：" + bool);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}