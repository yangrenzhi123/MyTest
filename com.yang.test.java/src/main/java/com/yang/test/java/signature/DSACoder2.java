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
		String inputStr = "1234567890";
		byte[] data = inputStr.getBytes();

		// 构建密钥
//		Map<String, Object> keyMap = DSACoder2.initKey();
//		String publicKey = DSACoder2.getPublicKey(keyMap);
//		String privateKey = DSACoder2.getPrivateKey(keyMap);
		

		String publicKey = "308201b73082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a03818400028180551ece221e9c7b3490dbcd8eac05f430a3e0fbf580a14fc426894392fc107bcade47ef0d2e021663d3dc1178a68e74a80c926af9a8da2ed3427653ceebfa6acc3a00d509e6331c3bd2538a0e161d8cbb88517ae4e89b2e708e397daf1bf000f9a729b43aef8d7666a71cabddf1c0697826d55244684bfab400eea2f7dc726681";
		String privateKey = "3082014b0201003082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a04160214782b174ebf61eb4e82a1f36b68a7df5ddde71e2f";

		System.out.println("公钥：" + publicKey);
		System.out.println("私钥：" + privateKey);

		// 产生签名
		String sign = DSACoder2.sign(data, privateKey);
		System.out.println("签名：" + sign);

		// 验证签名
		boolean status = DSACoder2.verify(data, publicKey, sign/*"302c021427dd66a8d16498db72e0601c78ee9bd80b995c340214638ec184d47323d367ad605efb8378667963bf60"*/);
		System.out.println("状态：" + status);
	}
}