import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		System.out.println(URLDecoder.decode("%7B%22port%22%3A%229106%22%2C%22ytjbbh%22%3A%224.0.8.0%22%2C%22sbbh%22%3A%2200013190328015%22%2C%22ip%22%3A%22115.236.86.198%22%7D", "utf8"));
	}
}