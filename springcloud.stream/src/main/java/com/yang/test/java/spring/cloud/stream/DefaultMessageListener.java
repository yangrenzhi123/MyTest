package com.yang.test.java.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

@Component
public class DefaultMessageListener {

	@StreamListener(Processor.INPUT)
	public void processMyMessage(String message) {
		System.out.println("接收到消息：" + message);
	}
}