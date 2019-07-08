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
		String publicKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEkufAcKuERJlJXno+SSbq23CPRpfot0sOUSD8xWWLfh8qdUwYRjrsy0ARmDZK1mrZ+/FCUtlfBWUgLVuK4BnKUA==";
		String sign = "MEUCIA5k1vANus6Ib2+0Gr2Bd1gVTsCFrJq1YkmkxXH0v8ZsAiEAzKrHnls8zQ6pPLXo/Mlqa1e0k0f08U0uaVTs5u3N60k=";
		String plaintext = "114702489670281216018676340124";

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