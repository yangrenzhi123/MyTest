package com.yang.test.java.kafka.withtransaction;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class KafkaSender {

	public static void main(String[] args) {
		Properties properties = new Properties();
//		properties.put("client.id", "ProducerTranscationnalExample");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//		properties.put("transactional.id", "test-transactional");
//		properties.put("acks", "all");
//		properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

		String topic = "ggggg";
		properties.put("bootstrap.servers", "192.168.10.239:9092");

		Producer producer = new KafkaProducer<String, String>(properties);
//		producer.initTransactions();
//		producer.beginTransaction();
		producer.send(new ProducerRecord<String, String>(topic, "test0", "0000"));
		producer.send(new ProducerRecord<String, String>(topic, "test0", "1111"));
		producer.send(new ProducerRecord<String, String>(topic, "test0", "2222"));
		producer.send(new ProducerRecord<String, String>(topic, "test0", "3333"));
		producer.send(new ProducerRecord<String, String>(topic, "test0", "4444"));
//		producer.abortTransaction();
//		producer.commitTransaction();
		producer.close();
	}
}