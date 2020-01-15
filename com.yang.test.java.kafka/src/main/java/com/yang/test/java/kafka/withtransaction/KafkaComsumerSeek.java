package com.yang.test.java.kafka.withtransaction;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

@SuppressWarnings("resource")
public class KafkaComsumerSeek {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();

		String group = "MysqlDataChangeGroup";
		String topic = "MysqlDataChange_h_recycle_record";
		
		props.put("group.id", group);
		props.put("max.poll.records", 10000);
		props.put("bootstrap.servers", "192.168.10.239:9092");
		
		props.put("auto.offset.reset", "earliest");
		props.put("enable.auto.commit", "false");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		consumer.subscribe(Arrays.asList(topic));
		System.out.println("Subscribed to topic " + topic);

		TopicPartition a = new TopicPartition(topic, 0);
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

			if (records.count() > 0) {

				if (records.count() < 500) {
					long offset = records.iterator().next().offset();
					consumer.seek(a, offset);

					System.out.println("数量不足，已回溯至" + offset);
				} else {
					System.out.println("消费量：" + records.count());
				}
				
				consumer.commitSync();
			}
		}
	}
}