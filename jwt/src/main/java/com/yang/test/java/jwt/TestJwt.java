package com.yang.test.java.jwt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class TestJwt {

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
		String secret = "eerp";
		String header = "{\"type\":\"JWT\",\"alg\":\"HS256\"}";
		String claim = "{\"iss\":\"cnooc\", \"sub\":\"yrm\", \"username\":\"yrm\", \"admin\":true}";

		String base64Header = Base64.encodeBase64URLSafeString(header.getBytes());
		String base64Claim = Base64.encodeBase64URLSafeString(claim.getBytes());
		String signature = Hmacsha256(secret, base64Header + "." + base64Claim);

		String jwt = base64Header + "." + base64Claim + "." + signature;
		System.out.println(jwt);
	}
}