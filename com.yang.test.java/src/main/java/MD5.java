import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static void main(String[] args) {

	}

	public static void t4() throws NoSuchAlgorithmException {
		// 鐢熸垚涓�涓狹D5鍔犲瘑璁＄畻鎽樿
		MessageDigest md = MessageDigest.getInstance("MD5");
		// 璁＄畻md5鍑芥暟
		md.update("123".getBytes());
		// digest()鏈�鍚庣‘瀹氳繑鍥瀖d5 hash鍊硷紝杩斿洖鍊间负8涓哄瓧绗︿覆銆傚洜涓簃d5 hash鍊兼槸16浣嶇殑hex鍊硷紝瀹為檯涓婂氨鏄�8浣嶇殑瀛楃
		// BigInteger鍑芥暟鍒欏皢8浣嶇殑瀛楃涓茶浆鎹㈡垚16浣峢ex鍊硷紝鐢ㄥ瓧绗︿覆鏉ヨ〃绀猴紱寰楀埌瀛楃涓插舰寮忕殑hash鍊�
		System.out.println(new BigInteger(1, md.digest()).toString(16));
	}

	public static void md5ForFile() throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		File a = new File("C:/1.zip");

		InputStream is = new FileInputStream(a);
		//md.update(is.readAllBytes());
		System.out.println(new BigInteger(1, md.digest()).toString(16));

		is.close();
	}

}