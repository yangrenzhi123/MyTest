import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		System.out.println(URLDecoder.decode("%E7%94%A8%E6%88%B7%E5%B7%B2%E6%B3%A8%E5%86%8C", "utf8"));
	}
}