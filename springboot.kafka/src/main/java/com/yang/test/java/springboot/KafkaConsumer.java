package com.yang.test.java.springboot;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = { "test0" })
	public void listenAlarmData0(List<String> records) {
		try {
			for (String record : records) {
				System.out.println(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = { "test1" })
	public void listenAlarmData1(List<String> records) {
		try {
			for (String record : records) {
				System.out.println(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = { "test2" })
	public void listenAlarmData2(List<String> records) {
		try {
			for (String record : records) {
				System.out.println(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = { "test3" })
	public void listenAlarmData3(List<String> records) {
		try {
			for (String record : records) {
				System.out.println(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}