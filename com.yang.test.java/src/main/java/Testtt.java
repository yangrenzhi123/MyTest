import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException, InterruptedException {

		for (int i = 0; i < 10; i++) {
			try {
				throw new RuntimeException("123");
			} finally {
				System.out.println(i);
			}
		}
	}
}