import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.UUID;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
}