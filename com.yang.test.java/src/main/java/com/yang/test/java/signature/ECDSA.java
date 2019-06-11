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
//		私钥：MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCBnMFiczsIsDskq7We4E/YRVyJmwzIUfnLpcieKpAiHUQ==
//		签名：MEUCIQC8FPKm1R99s8KZd2p+cO79pcyk2CSH6C8YSukH6i6N8wIgItpM+lFefQce5ePCFtQvPIa0Zj67ySaM7CROv+MEYI4=
//		公钥：MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEKYHIKPbQEqzWnSUBO9LJIs6ghk1sw5XxMPn52vUJHnFUJAmGLnfPgYjPyh1p7P+rgHZQr82fROUy8Gt+qhtb3Q==
//		验证：true
		
//		SHA256withECDSA
//		私钥：MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCBPqih96bCL7lRiSjxMRHj6RHC5o4K6jcSpYZZs0wUbJg==
//		签名：MEYCIQCx2tBg3VRNzpDE5OBfltmz/oKfY2zMYGcoMfNPcD/pmgIhAIh3Mag35yKYFumb5AaG3QdXu2zRFQ/ov7xpC2ViZJRj
//		公钥：MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE3HjmXWNHAJCBlmvrEsrdLsVTJ+7O4ld0UwlgROJLLnAfzqf57kADO9vObMiDZNlLVWUxWiV4Bd9LphYVZ/IacQ==
//		验证：true
		
		
		
		try {
			String sf = "SHA256withECDSA";
			
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
			System.out.println("签名：" + encryptBASE64(res));

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