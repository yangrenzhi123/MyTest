import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		t4();
	}

	public static void t4() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update("123456".getBytes());
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