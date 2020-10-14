package com.yang.test.java.kafka;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

@SuppressWarnings("resource")
public class KafkaComsumer {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();

		String group = "test";
		String topic = "test";
		props.put("bootstrap.servers", "192.168.8.70:9092");

		props.put("enable.auto.commit", "false");
		//props.put("enable.auto.commit", "true");
		//props.put("auto.commit.interval.ms", "1000");

		
//		max.poll.interval.ms    两次拉取之间的最大间隔时间，默认5分钟
//		session.timeout.ms      group coordinator 检测consumer奔溃的时间，默认10s
//		heartbeat.interval.ms    心跳包间隔时间，一般是session.timeout.ms的1/3，默认3s
		props.put("group.id", group);
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put("max.poll.interval.ms", "300000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		consumer.subscribe(Arrays.asList(topic));
		System.out.println("Subscribed to topic " + topic);

		while (true) {
			try {
				// 拉取
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
				
				if(!records.isEmpty()) {
					// 业务处理
					for (ConsumerRecord<String, String> record : records) {
						System.out.printf("partition = %d, offset = %d, key = %s, value = %s\n", record.partition(), record.offset(), record.key(), record.value());
					}

					// 提交
					System.out.println("开始提交..");
					consumer.commitSync();
					System.out.println("结束");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}