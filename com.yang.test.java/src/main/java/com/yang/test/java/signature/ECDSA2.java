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
		//密钥
		String key = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCCH5Zr4i+aibPFc2zxT+9mWL3oeQ1ayVHs3MyEBj6tCdw==";
		
		byte[] keyByte = decryptBASE64(key);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("EC");
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Signature signature = Signature.getInstance("SHA1withECDSA");
		signature.initSign(privateKey);
		signature.update("114702489670281216018676340124".getBytes());
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