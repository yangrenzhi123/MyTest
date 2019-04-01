import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Testtt {

	public static void main(String[] args) {
		
		
		
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar start = Calendar.getInstance();
		start.set(Calendar.MONTH, 2);
		start.set(Calendar.DAY_OF_MONTH, 29);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);

		Calendar end = Calendar.getInstance();
		end.set(Calendar.MONTH, 2);
		end.set(Calendar.DAY_OF_MONTH, 30);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);

		System.out.println(start.getTime().getTime());
		System.out.println(end.getTime().getTime());
		System.out.println(df.format(start.getTime()));
		System.out.println(df.format(end.getTime()));
	}
}