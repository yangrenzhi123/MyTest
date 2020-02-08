package com.yang.test.java.spring.cloud.stream;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("rawtypes")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsageDetailSenderApplicationTests {

	@Autowired
	private MessageCollector messageCollector;

	@Autowired
	private Source source;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUsageDetailSender() throws Exception {
		Message message = this.messageCollector.forChannel(this.source.output()).poll(1, TimeUnit.SECONDS);
		String usageDetailJSON = message.getPayload().toString();
		assertTrue(usageDetailJSON.contains("userId"));
		assertTrue(usageDetailJSON.contains("duration"));
		assertTrue(usageDetailJSON.contains("data"));
	}
}