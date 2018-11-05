
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

@SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
public class TestProduct {

	public static final int count = 5000;
	public static final Properties properties = new Properties();
	
	public static void main(String[] args) throws InterruptedException {
		properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("zookeeper.connect", "192.168.30.120:2181,192.168.30.121:2181,192.168.10.10:2181");
		properties.put("metadata.broker.list", "192.168.30.151:9092,192.168.30.152:9092,192.168.30.153:9092");
		final List<Producer> pl = new ArrayList<>();
		pl.add(new Producer<Integer, String>(new ProducerConfig(properties)));
		pl.add(new Producer<Integer, String>(new ProducerConfig(properties)));
		pl.add(new Producer<Integer, String>(new ProducerConfig(properties)));
		pl.add(new Producer<Integer, String>(new ProducerConfig(properties)));
		while (true) {
			test(pl);
		}
	}

	static void test(final List<Producer> pl) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(count);
		List<Thread> l = new ArrayList<>();
		
		for (int i = 0; i < count; i++) {
			Thread a = new Thread(new Runnable() {
				public void run() {
					try {
						pl.get(new Random().nextInt(4)).send(new KeyedMessage<Integer, String>("test", "message: 1"));
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}
			});
			l.add(a);
		}
		
		
		long a = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			l.get(i).start();
		}

		latch.await();
		System.out.println(System.currentTimeMillis() - a);
	}
}