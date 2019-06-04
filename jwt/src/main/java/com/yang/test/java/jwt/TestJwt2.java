package com.yang.test.java.jwt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class TestJwt2 {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
		testJWT();
	}

	private static final String MAC_INSTANCE_NAME = "HMacSHA256";

	public static String Hmacsha256(String secret, String message) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac hmac_sha256 = Mac.getInstance(MAC_INSTANCE_NAME);
		SecretKeySpec key = new SecretKeySpec(secret.getBytes(), MAC_INSTANCE_NAME);
		hmac_sha256.init(key);
		byte[] buff = hmac_sha256.doFinal(message.getBytes());
		return Base64.encodeBase64URLSafeString(buff);
	}

	public static void testJWT() throws InvalidKeyException, NoSuchAlgorithmException {
		String secret = "HBZDUUALlGq0NACTJ9Uxtb9sMz2Aa70r";
		String content = "ab7af613b4fa43289e1c763429298d46";

		String signature = Hmacsha256(secret, content);

		String jwt = "ab7af613b4fa43289e1c763429298d46" + signature;
		System.out.println(jwt);
	}
}