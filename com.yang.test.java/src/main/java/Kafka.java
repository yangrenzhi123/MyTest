import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("resource")
public class Kafka {
	public static void main(String[] args) throws Exception {
		String topic = "test";
		String group = "testGroup2";
		Properties props = new Properties();


        //String topic = "my-replicated-topic";
        //props.put("bootstrap.servers", "172.28.51.33:9092,172.28.51.33:9093,172.28.51.33:9094");
		
		props.put("bootstrap.servers", "192.168.30.151:9092,192.168.30.152:9092,192.168.30.153:9092");
		

		//props.put("bootstrap.servers", "192.168.8.70:9092");
		
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

		MongoClient mongoClient = new MongoClient("192.168.10.239", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection c = mgdb.getCollection("c8");

		
		int i=0;
		long a=0;
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Integer.MAX_VALUE);
			List<Document> documents = new ArrayList<Document>();
			for (ConsumerRecord<String, String> record : records) {
				if(i==0) {
					a = System.currentTimeMillis();
				}
				i++;
				//System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
				

				Document doc = Document.parse("{\""+record.key()+"\":\""+record.value()+"\"}");
				documents.add(doc);
			}
			c.insertMany(documents);
			System.out.println("耗时："+(System.currentTimeMillis()-a)+"，总条数："+i);
		}
	}
}