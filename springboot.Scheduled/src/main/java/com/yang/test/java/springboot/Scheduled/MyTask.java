package com.yang.test.java.springboot.Scheduled;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

	public static final String one = "https://oapi.dingtalk.com/robot/send?access_token=ad88aa82594ae1d596d07282c54eb08a59134e793ce8e49827c76901e19a0a0f";
	public static final String two = "https://oapi.dingtalk.com/robot/send?access_token=6984fb55d6496efb1927696ac3c33eb7163ef83454a2dc2998b8cf29bf9af192";

	@Scheduled(cron = "0 * * * * *")
	public void work() throws InterruptedException {
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int weekDay = now.get(Calendar.DAY_OF_WEEK);

		if (weekDay == 6 && hour == 10 && minute == 59) {
			TestDingding.test("中石油，周末欢乐享", one);
		}

		if (hour == 12 && minute == 59) {
			TestDingding.test("赶紧来一波支付宝一波操作", one);
			TestDingding.test("DT加油签到", one);
		}
	}
}