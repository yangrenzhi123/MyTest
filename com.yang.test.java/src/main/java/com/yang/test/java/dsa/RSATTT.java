package com.yang.test.java.dsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

@SuppressWarnings({"restriction", "static-access"})
public class RSATTT {

	private static String src = "我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是";

	public static void main(String[] args) {
		jdkRSA();
	}

	public static void jdkRSA() {
		try {
			String signatureStr = "SHA1withRSA";
			
			// 1.初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024); //1024位的RSA密钥基本安全，2048位的密钥极其安全。https://www.cnblogs.com/cjm123/p/8243424.html
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			

			// 2.执行签名
			byte[] privateKeyByte = rsaPrivateKey.getEncoded();
			System.out.println("私钥长度："+privateKeyByte.length);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			java.security.Signature signature = java.security.Signature.getInstance(signatureStr);
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] res = signature.sign();
			System.out.println("签名长度："+res.length);
			String sign = HexBin.encode(res);
			System.out.println("签名：" + sign + "，长度：" + sign.length());

			// 3.验证签名
			byte[] publicKeyByte = rsaPublicKey.getEncoded();
			System.out.println("公钥长度："+publicKeyByte.length);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
			keyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance(signatureStr);
			signature.initVerify(publicKey);
			signature.update(src.getBytes());
			boolean bool = signature.verify(res);
			System.out.println("验证：" + bool);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}