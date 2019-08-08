import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		String a = URLDecoder.decode("%7B%22SystemType%22%3A%22recycleOnLine%22%2C%22citizenId%22%3A%22161158812111111%22%2C%22type%22%3A%222%22%2C%22phone%22%3A%22LYZH151211111101%22%2C%22deviceNo%22%3A%22000000000002%22%2C%22userId%22%3A%22%22%2C%22Id%22%3A%22bda671c4-c4e1-4ad5-8ded-522eebf1ed4f%22%2C%22ljlx%22%3A%2205%22%2C%22weight%22%3A%221000%22%2C%22recycleTime%22%3A%222019-07-31+08%3A43%3A10%22%2C%22score%22%3A%2250%22%2C%22singlePrice%22%3A%22%22%2C%22jflx%22%3A%221%22%2C%22flmc%22%3A%22%E9%87%91%E5%B1%9E%22%7D", "utf8");
		System.out.println(a);
	}
}