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

public class Test {

	public static void main(String[] args) throws InterruptedException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String content = "�����л�";
		byte[] cs = content.getBytes();
		

		byte[] key = "��������".getBytes();
		
		DESKeySpec desKey = new DESKeySpec(key);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key));
		
		
		String s = new String(decrypt(cipher.doFinal(cs), "��������"));
		System.out.println(s);
	}
	
	
    private static byte[] decrypt(byte[] src, String key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, SecretKeyFactory.getInstance("DES").generateSecret(desKey), new IvParameterSpec(key.getBytes()));
		// ������ʼ���ܲ���
		return cipher.doFinal(src);
    }
}