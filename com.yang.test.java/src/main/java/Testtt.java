import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testtt {

	public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(df.format(new Date(1562944244000L)));
	}
}