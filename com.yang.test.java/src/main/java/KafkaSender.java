import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class KafkaSender {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		String topic = "test";
//		properties.put("bootstrap.servers", "172.17.97.117:9092,172.17.97.121:9092,172.17.97.126:9092");
//		properties.put("bootstrap.servers", "172.17.97.117:9092");
//		properties.put("bootstrap.servers", "172.17.97.121:9092");
		properties.put("bootstrap.servers", "172.17.97.126:9092");

		Producer producer = new KafkaProducer<String, String>(properties);
		producer.send(new ProducerRecord<String, String>(topic, "7"));
		producer.close();
	}
}