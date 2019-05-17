import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Testtt {

	public static void main(String[] args) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		System.out.println(df.parse("20190101").getTime());
	}
}