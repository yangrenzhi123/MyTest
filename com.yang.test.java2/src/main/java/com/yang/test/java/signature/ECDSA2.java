package com.yang.test.java.signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class ECDSA2 {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
//		私钥：MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDx6YYtYKaQfSfCykj89F6akwVk+BjGCS6MY7Afcjv2Lg==
//		签名：3046022100F570F03B87CC17EF2AADD9C0925DC57DCD03635A05C8D47D86E6E70F21245468022100D4E40C4C25609D66D409031874173425B82E09B0D02D5D19208E0EA56545214E
//		公钥：MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE9O6ymQa1sdvr2ZBTRjbweuslQDGRN5V239l7pMh5SpQGUaZi6WSAIqrsotUgXqv8ta1V7h5u5GqWbZVpDE1JWw==
//		验证：true
		
		//密钥
		String key = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDx6YYtYKaQfSfCykj89F6akwVk+BjGCS6MY7Afcjv2Lg==";
		
		byte[] keyByte = decryptBASE64(key);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("EC");
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Signature signature = Signature.getInstance("SHA1withECDSA");
		signature.initSign(privateKey);
		signature.update("APPID1558332074".getBytes());
		byte[] res = signature.sign();
		System.out.println("签名：" + encryptBASE64(res));
	}

	public static byte[] decryptBASE64(String key) {
		return Base64.decodeBase64(key);
	}

	public static String encryptBASE64(byte[] key) {
		return Base64.encodeBase64String(key);
	}
}