package com.example.demo;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String s = config.getKafka();

		Properties properties = new Properties();
		properties.put("serializer.class", StringEncoder.class.getName());
		String topic = "test";
		properties.put("metadata.broker.list", s);
		Producer producer = new Producer<String, String>(new ProducerConfig(properties));

		MoniResult result = new MoniResult();
		result.setName("Kafka");
		result.setCheckTime(new Date());
		try {
			producer.send(new KeyedMessage<String, String>(topic, "110"));
			result.setResult(1);
			MonitorStartup.result.put(s, result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			MonitorStartup.result.put(s, result);

			DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			TestDingding.test("检测时间：" + yyyy.format(result.getCheckTime()) + "，结果：" + (result.getResult() == 1 ? "成功" : "<span style='color:red'>失败</span>") + "，" + result.getName() + "，备注：" + result.getName());
		}
		
		producer.close();
	}
}