package timer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TwoHours {

	public static final String one = "https://oapi.dingtalk.com/robot/send?access_token=4a308218f4d9d08762a3faec3bfcd7673030cb6556091a72567129448991ec7f";
	public static final String two = "https://oapi.dingtalk.com/robot/send?access_token=f3fbb81e09ce87f39afc8af9567e315310811b18e10de8a7666e3ed1660b3b94";

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				Calendar now = Calendar.getInstance();
				int hour = now.get(Calendar.HOUR_OF_DAY);
				int minute = now.get(Calendar.MINUTE);
				if (hour >= 7 && hour <= 22 && minute == 58) {
					if (hour % 2 == 1) {
						TestDingding.test("领时段奖励了，快快快！！！", two);
					}
				}

				if (hour == 21 && minute == 58) {
					StringBuilder sb = new StringBuilder();
					sb.append("1、浙江移动步数签到\r\n");
					sb.append("2、浙江移动APP签到\r\n");
					sb.append("3、DT加油签到\r\n");
					sb.append("4、惠头条签到\r\n");
					sb.append("5、阿里巴巴签到赚元宝\r\n");
					sb.append("6、省钱快报签到\r\n");
					sb.append("7、给老公买袜子\r\n");
					TestDingding.test(sb.toString(), two);
				}

				if (hour == 12 && minute == 57) {
					StringBuilder sb = new StringBuilder();
					sb.append("赶紧来一波支付宝一波操作");
					TestDingding.test(sb.toString(), two);
				}

				if ((hour == 9 || hour == 13) && minute == 57) {
					StringBuilder sb = new StringBuilder();
					sb.append("元宝换红包了，快快快！！！");
					TestDingding.test(sb.toString(), two);
				}

				if (hour >= 7 && hour <= 22 && minute == 58) {
					TestDingding.test("头条开宝箱了，快快快！！！", one);
				}
			}
		}, 0, 60000);
	}
}