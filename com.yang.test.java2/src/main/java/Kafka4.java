import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

@SuppressWarnings("resource")
public class Kafka4 {
	public static void main(String[] args) throws Exception {
		String group = "parser_group";
		Properties props = new Properties();


        //String topic = "my-replicated-topic";
        //props.put("bootstrap.servers", "172.28.51.33:9092,172.28.51.33:9093,172.28.51.33:9094");

//		props.put("zookeeper.connect", "192.168.30.120:2181,192.168.30.121:2181,192.168.10.10:2181");
//		props.put("bootstrap.servers", "192.168.30.120:9092,192.168.8.70:9092");
		


		String topic = "TCP_GREENLIFE2";
		props.put("bootstrap.servers", "192.168.10.239:9092");
		

		//String topic = "test";
		//props.put("bootstrap.servers", "192.168.30.151:9092");
		
		//props.put("bootstrap.servers", "192.168.30.60:9092,192.168.30.61:9092,192.168.30.62:9092");

		props.put("group.id", group);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		consumer.subscribe(Arrays.asList(topic));
		System.out.println("Subscribed to topic " + topic);

		int i=0;
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			System.out.println((i++) + ":" + records.count());
		}
	}
}