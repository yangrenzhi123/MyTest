package com.yang.test.java.signature;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class DSACoder2 {

	public static final String ALGORITHM = "DSA";

	/**
	 * 默认密钥字节数
	 * 
	 * <pre>
	 *  
	 * DSA  
	 * Default Keysize 1024   
	 * Keysize must be a multiple of 64, ranging from 512 to 1024 (inclusive).
	 * </pre>
	 */
	private static final int KEY_SIZE = 1024;

	private static final String PUBLIC_KEY = "DSAPublicKey";
	private static final String PRIVATE_KEY = "DSAPrivateKey";

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data       加密数据
	 * @param privateKey 私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);

		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
		signature.initSign(priKey);
		signature.update(data);

		byte[] sign = signature.sign();
		System.out.println("签名长度："+sign.length);
		
		return encryptBASE64(sign);
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data      加密数据
	 * @param publicKey 公钥
	 * @param sign      数字签名
	 * 
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {

		// 解密由base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);

		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
		signature.initVerify(pubKey);
		signature.update(data);

		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
	}

	/**
	 * 生成密钥
	 * 
	 * @param seed 种子
	 * @return 密钥对象
	 * @throws Exception
	 */
	public static Map<String, Object> initKey(String seed) throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
		// 初始化随机产生器
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(seed.getBytes());
		keygen.initialize(KEY_SIZE, secureRandom);

		KeyPair keys = keygen.genKeyPair();

		DSAPublicKey publicKey = (DSAPublicKey) keys.getPublic();
		DSAPrivateKey privateKey = (DSAPrivateKey) keys.getPrivate();

		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);

		return map;
	}

	/**
	 * 默认生成密钥
	 * 
	 * @return 密钥对象
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		return initKey(UUID.randomUUID().toString().replaceAll("-", ""));
	}

	/**
	 * 取得私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);

		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 取得公钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);

		return encryptBASE64(key.getEncoded());
	}

	public static byte[] decryptBASE64(String key) throws DecoderException {
		return Hex.decodeHex(key.toCharArray());
	}

	public static String encryptBASE64(byte[] key) {
		return new String(Hex.encodeHex(key));
	}

	public static void main(String[] args) throws Exception {
		String inputStr = "我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是我是原文我是原文我是";
		byte[] data = inputStr.getBytes();

		// 构建密钥
		Map<String, Object> keyMap = DSACoder2.initKey();
		String publicKey = DSACoder2.getPublicKey(keyMap);
		String privateKey = DSACoder2.getPrivateKey(keyMap);

		System.out.println("公钥：" + publicKey);
		System.out.println("私钥：" + privateKey);

		// 产生签名
		String sign = DSACoder2.sign(data, privateKey);
		System.out.println("签名：" + sign);

		// 验证签名
		boolean status = DSACoder2.verify(data, publicKey, sign);
		System.out.println("状态：" + status);
	}
}