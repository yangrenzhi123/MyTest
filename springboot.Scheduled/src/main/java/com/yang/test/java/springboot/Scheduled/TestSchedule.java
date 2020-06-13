package com.yang.test.java.springboot.Scheduled;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@EnableScheduling
@SpringBootApplication
public class TestSchedule {
	public static void main(String[] args) {
		SpringApplication.run(TestSchedule.class, args);
	}
}

@Component
class MyTask1 {
	// 秒 分 时 天 月 周，周和月如果同时设置可能会冲突，不设定可以用?表示
	@Scheduled(cron = "0 * * * * *")
	public void work() {
		System.out.println(1);
	}
}


@Component
class MyTask2 {
	 @Scheduled(cron = "0 * * * * *")
	public void work() {
		System.out.println(2);
	}
}

@Component
class MyTask3 {
	 @Scheduled(cron = "0 * * * * *")
	public void work() {
		System.out.println(3);
	}
}

@Configuration
class ScheduleConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(1);
	}
}