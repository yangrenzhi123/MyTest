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
		// 生成一个MD5加密计算摘要
		MessageDigest md = MessageDigest.getInstance("MD5");
		// 计算md5函数
		md.update("123".getBytes());
		// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
		// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
		System.out.println(new BigInteger(1, md.digest()).toString(16));
	}

	public static void md5ForFile() throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		File a = new File("C:/1.zip");

		InputStream is = new FileInputStream(a);
		md.update(is.readAllBytes());
		System.out.println(new BigInteger(1, md.digest()).toString(16));

		is.close();
	}

}