import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesTest {

	public static void main(String[] args) throws Exception {
		test1();
		test2();
	}
	
	public static void test1() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		File f2 = new File("D:/1.mp4");
		FileOutputStream fos = new FileOutputStream(f2);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		File f1 = new File("D:/Downloads/hjd2048.com-0412ssni178-h264/1.mp4");
		FileInputStream fis = new FileInputStream(f1);
		byte[] b = new byte[2047];
		while(fis.read(b) != -1) {
			byte[] key = "12345678".getBytes();
			DESKeySpec desKey = new DESKeySpec(key);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
			byte[] target = cipher.doFinal(b);
			bos.write(target);
			
			System.out.println(1);
		}
		
		fis.close();
		bos.close();
	}

	public static void test2() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		int i = 0;
		
		File f2 = new File("D:/2.mp4");
		FileOutputStream fos = new FileOutputStream(f2);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		File f1 = new File("D:/1.mp4");
		FileInputStream fis = new FileInputStream(f1);
		byte[] b = new byte[2048];
		while(fis.read(b) != -1) {
			byte[] key = "12345678".getBytes();
			DESKeySpec desKey = new DESKeySpec(key);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
			byte[] target = cipher.doFinal(b);
			bos.write(target);
			
			System.out.println(i++);
		}
		
		fis.close();
		bos.close();
	}
	
	public static void main11(String[] args) throws IOException {
		byte[] b1 = new byte[] {49};
		byte[] b2 = new byte[] {50};
		
		
		File file = new File("E:/1.txt");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(b1);
		bos.write(b2);
		bos.close();
	}

	public static void main11111(String[] args) throws InterruptedException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] cs = new byte[] {49, 50, 51, 52, 53, 54, 55};
		byte[] key = "12345678".getBytes();
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
		byte[] t = cipher.doFinal(cs);

		byte[] a = new byte[] {t[0], t[1], t[2], t[3], t[4], t[5], t[6], t[7]};
		String s = new String(decrypt(a, "12345678"));
		System.out.println(s);
	}

	private static byte[] decrypt(byte[] src, String key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key.getBytes()));
		// 鐪熸寮�濮嬭В瀵嗘搷浣�
		return cipher.doFinal(src);
	}
}