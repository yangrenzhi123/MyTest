package timer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class TwoHours {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				
				Calendar now = Calendar.getInstance();
				int hour = now.get(Calendar.HOUR_OF_DAY);
				int minute = now.get(Calendar.MINUTE);
				if(hour >= 7 && hour <= 22 && (minute == 58 || minute == 59)) {
					if(hour % 2 == 1) {
						TestDingding.test("123");
					}
				}
			}
		}, 0, 30000);
	}
}