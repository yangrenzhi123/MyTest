package com.yang.test.java.springboot.Scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

	
	
	
	//	秒（0~59）
	//	分钟（0~59）
	//	小时（0~23）
	//	天（月）（0~31，但是你需要考虑你月的天数）
	//	月（0~11）
	//	天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
	//	年份（1970－2099）
    @Scheduled(cron = "0/10 * * * * * *")
    public void work() {
        System.out.println(1);
    }
}