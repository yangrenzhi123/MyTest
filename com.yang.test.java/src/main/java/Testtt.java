import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException, InterruptedException {

		String s = "1\n2\n3\n";
		System.out.println(s.indexOf("\n"));
		System.out.println(s.indexOf("\n", 2));
	}
}