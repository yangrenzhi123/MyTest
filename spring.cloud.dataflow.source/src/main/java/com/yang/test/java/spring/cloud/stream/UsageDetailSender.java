package com.yang.test.java.spring.cloud.stream;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@EnableBinding(Source.class)
public class UsageDetailSender {

	@Autowired
	private Source source;

	private String[] users = { "user1", "user2", "user3", "user4", "user5" };

	@Scheduled(fixedDelay = 1000)
	public void sendEvents() {
		UsageDetail usageDetail = new UsageDetail();

		usageDetail.setUserId(this.users[new Random().nextInt(5)]);
		usageDetail.setDuration(new Random().nextInt(300));
		usageDetail.setData(new Random().nextInt(700));
		this.source.output().send(MessageBuilder.withPayload(usageDetail).build());
	}
}