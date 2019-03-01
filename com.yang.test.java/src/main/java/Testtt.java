import java.util.Calendar;
import java.util.Date;

public class Testtt {

	public static void main(String[] args) {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.DAY_OF_MONTH, 1);
		instance.set(Calendar.HOUR, 0);
		Date min = instance.getTime();
		System.out.println(min);
	}
}