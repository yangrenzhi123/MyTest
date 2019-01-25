import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;


@SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
public class Kafka2 {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("serializer.class", StringEncoder.class.getName());

		
		
		
		
		String topic = "TCP_GREENLIFE2";
		properties.put("metadata.broker.list", "192.168.10.239:9092");

		
		
		
		
		Producer producer = new Producer<String, String>(new ProducerConfig(properties));
		producer.send(new KeyedMessage<String, String>(topic, "110"));
	}
}