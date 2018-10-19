import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class Kafka2 {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("serializer.class", StringEncoder.class.getName());
		
		
		//properties.put("zookeeper.connect", "172.28.51.33:2181");// 声明zk
		//properties.put("metadata.broker.list", "172.28.51.33:9092,172.28.51.33:9093,172.28.51.33:9094");// 声明kafka

		//properties.put("zookeeper.connect", "192.168.30.120:2181,192.168.30.121:2181,192.168.10.10:2181");
		//properties.put("metadata.broker.list", "192.168.30.151:9092,192.168.30.152:9092,192.168.30.153:9092");
		
		

		//properties.put("zookeeper.connect", "192.168.10.248:2181");
		properties.put("metadata.broker.list", "192.168.8.155:9092");
		

		//properties.put("zookeeper.connect", "192.168.30.60:2181,192.168.30.61:2181,192.168.30.62:2181");
		//properties.put("metadata.broker.list", "192.168.30.60:9092,192.168.30.61:9092,192.168.30.62:9092");

		Producer producer = new Producer<Integer, String>(new ProducerConfig(properties));

		producer.send(new KeyedMessage<Integer, String>("t0", "message: 1"));
	}
}