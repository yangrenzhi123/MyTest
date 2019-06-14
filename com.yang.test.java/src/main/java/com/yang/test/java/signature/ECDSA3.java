package com.yang.test.java.signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class ECDSA3 {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		// 摘要算法
		String sf = "SHA1withECDSA";

		// 公钥
		String publicKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE1wVfIjS3ttdFG43bEUEANr+H7XdiIR+lHwtzBllDgmpVIoM/F3w8pmmJw19RAlICmLkM/BMv7o8pSrjK8fXY8w==";
		String sign = "MEQCIHRWpXWwWHe9fF6+gY8CeFo7YVYNVxVSPIy2rxfhYJZ+AiB3QF6HpU6NdJokaU7UnH9D7Xdji7WCPeKqa4jVRPL9yg==";
		String plaintext = "LYHZP00001CX01ZJLY012015100006741139078741067042816";

		// 验证签名
		byte[] publicKeyByte = decryptBASE64(publicKeyStr);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("EC");
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Signature signature = Signature.getInstance(sf);
		signature.initVerify(publicKey);
		signature.update(plaintext.getBytes());
		boolean bool = signature.verify(decryptBASE64(sign));
		System.out.println("验证：" + bool);
	}

	public static byte[] decryptBASE64(String key) {
		return Base64.decodeBase64(key);
	}

	public static String encryptBASE64(byte[] key) {
		return Base64.encodeBase64String(key);
	}
}