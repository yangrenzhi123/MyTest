
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.WriteAbortedException;
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
		simple();
	}
	
	public static void test1() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		File f2 = new File("D:/1.mp4");
		FileOutputStream fos = new FileOutputStream(f2);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		File f1 = new File("D:/Downloads/1.mp4");
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
	
	public static void simple() throws IOException {
		byte[] b1 = new byte[] {49};
		
		File file = new File("D:/1.txt");
		OutputStream fos = new FileOutputStream(file);
		
		fos.write(b1);
		fos.close();
	}

	public static void test3() throws InterruptedException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		byte[] key = "fdfefe43".getBytes();
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
		byte[] t = cipher.doFinal("helloworlds".getBytes());
		
		
		String s = new String(t);
		System.out.println(s);
	}

	public static void test4() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		String s = new String(decrypt("2esxXE87E+XYIleb/dcNwA==".getBytes(), "fdfefe43"));
		System.out.println(s);
	}
	
	private static byte[] decrypt(byte[] src, String key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key.getBytes()));
		return cipher.doFinal(src);
	}
}