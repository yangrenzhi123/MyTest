package com.example.demo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
@Service
public class MonitorForKafka {

	@Autowired
	Config config;

	public void execute() throws ClassNotFoundException, SQLException {
		List<String> l = config.getDbList();

		for (String s : l) {
			Properties properties = new Properties();
			properties.put("serializer.class", StringEncoder.class.getName());
			String topic = "test";
			properties.put("metadata.broker.list", s);
			Producer producer = new Producer<String, String>(new ProducerConfig(properties));

			MoniResult result = new MoniResult();
			result.setCheckTime(new Date());
			try {
				producer.send(new KeyedMessage<String, String>(topic, "110"));
				result.setResult(1);
				DemoApplication.result.put(s, result);
			} catch (Exception e) {
				result.setResult(0);
				DemoApplication.result.put(s, result);
			}
		}
	}
}