package com.yang.test.java.springboot.Scheduled;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

	public static final String one = "https://oapi.dingtalk.com/robot/send?access_token=ad88aa82594ae1d596d07282c54eb08a59134e793ce8e49827c76901e19a0a0f";
	public static final String two = "https://oapi.dingtalk.com/robot/send?access_token=6984fb55d6496efb1927696ac3c33eb7163ef83454a2dc2998b8cf29bf9af192";

	@Scheduled(cron = "0 * * * * *") // 每分钟的第0秒执行
	public void work() throws InterruptedException {
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		if (hour >= 7 && hour <= 22 && minute == 59) {
			if (hour % 2 == 1) {
				TestDingding.test("领时段奖励了，快快快！！！", two);
			}
		}

		if (hour == 21 && minute == 58) {
			StringBuilder sb = new StringBuilder();
			sb.append("1、浙江移动步数签到\r\n");
			sb.append("2、惠头条签到\r\n");
			sb.append("3、阿里巴巴签到赚元宝\r\n");
			sb.append("4、省钱快报签到\r\n");
			TestDingding.test(sb.toString(), two);
			TestDingding.test("DT加油签到", one);
			

			
			int a = now.get(Calendar.DAY_OF_MONTH);
			for(int i=0;i<15;i++) {
				if(a == (i+1)) {
					TestDingding.test("浙江移动APP签到", two);
				}
			}
			
			if(a == 18) {
				TestDingding.test("浙江移动APP 18日幸运签到礼", two);
			}
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

		if (hour >= 7 && hour <= 22 && minute == 59) {
			TestDingding.test("头条开宝箱了，快快快！！！", one);
		}
	}
}