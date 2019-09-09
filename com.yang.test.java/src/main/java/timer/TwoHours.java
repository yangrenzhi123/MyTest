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
				if (hour >= 7 && hour <= 22 && (minute == 58 || minute == 59)) {
					if (hour % 2 == 1) {
						TestDingding.test("领时段奖励了，快快快！！！");
					}
				}

				if (hour == 21 && (minute == 58 || minute == 59)) {
					StringBuilder sb = new StringBuilder();
					sb.append("1、浙江移动步数签到\r\n");
					sb.append("2、浙江移动APP签到\r\n");
					sb.append("3、DT加油签到\r\n");
					sb.append("4、惠头条签到\r\n");
					sb.append("5、阿里巴巴签到赚元宝\r\n");
					sb.append("6、省钱快报签到\r\n");
					sb.append("7、给老公买袜子\r\n");
					TestDingding.test(sb.toString());
				}

				if (hour == 13 && (minute == 56 || minute == 57)) {
					StringBuilder sb = new StringBuilder();
					sb.append("1、支付宝一波操作\r\n");
					TestDingding.test(sb.toString());
				}
			}
		}, 0, 60000);
	}
}