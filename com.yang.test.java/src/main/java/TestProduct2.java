
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

@SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
public class TestProduct2 {

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		while (true) {
			if (i++ == 0) {
				test();
			} else {
				new Thread(new Runnable() {
					public void run() {
						try {
							test();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			Thread.sleep(1000);
		}
	}

	static void test() throws InterruptedException {
		int count = 800;
		final CountDownLatch latch = new CountDownLatch(count);

		List<Thread> l = new ArrayList<>();

		Properties properties = new Properties();
		properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("zookeeper.connect", "192.168.30.120:2181,192.168.30.121:2181,192.168.10.10:2181");
		properties.put("metadata.broker.list", "192.168.30.151:9092,192.168.30.152:9092,192.168.30.153:9092");
		final Producer producer = new Producer<Integer, String>(new ProducerConfig(properties));
		for (int i = 0; i < count; i++) {
			Thread a = new Thread(new Runnable() {
				public void run() {
					try {
						producer.send(new KeyedMessage<Integer, String>("test", "message: 1"));
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