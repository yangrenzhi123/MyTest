import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String s1 = "2019-06-06 14:31:00";
		String s2 = "2019-06-06 14:32:00";
		
		System.out.println(df.parse(s1).getTime());
		System.out.println(df.parse(s2).getTime());
	}
}