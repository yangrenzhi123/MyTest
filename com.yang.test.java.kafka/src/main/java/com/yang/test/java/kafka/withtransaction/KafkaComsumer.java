package com.yang.test.java.kafka.withtransaction;
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

		String group = "group4test";
		String topic = "test2";
		props.put("bootstrap.servers", "192.168.10.239:9092");
		
		props.put("group.id", group);

		props.put("enable.auto.commit", "false");
//		props.put("enable.auto.commit", "true");
//		props.put("auto.commit.interval.ms", "60000");
		
//		props.put("isolation.level", "read_committed"); //读提交，否则事务无效
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		consumer.subscribe(Arrays.asList(topic));
		System.out.println("Subscribed to topic " + topic);

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("partition = %d, offset = %d, key = %s, value = %s\n", record.partition(), record.offset(), record.key(), record.value());
			}
		}
	}
}