import java.util.Calendar;
import java.util.Date;

public class Testtt {

	public static void main(String[] args) {
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.HOUR_OF_DAY, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		System.out.println(instance.getTime().getTime());
		instance = Calendar.getInstance();
		instance.set(Calendar.HOUR_OF_DAY, 18);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		System.out.println(instance.getTime().getTime());
	}
}