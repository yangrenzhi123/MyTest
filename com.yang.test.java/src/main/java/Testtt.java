import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testtt {

	public static void main(String[] args) {
		Date d = new Date(1288834974657L);
		
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(df.format(d));
	}
}