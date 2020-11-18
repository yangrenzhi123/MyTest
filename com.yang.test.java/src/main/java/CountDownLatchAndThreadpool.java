import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchAndThreadpool {

	public static void main(String[] args) {
		int size = 50;
		
		final CountDownLatch latch = new CountDownLatch(size);
		ExecutorService p = Executors.newFixedThreadPool(2);

		for (int i = 0; i < size; i++) {
			final int j = i;
			p.execute(new Runnable() {
				public void run() {
					try {
						if(j % 10 == 0) {
							throw new RuntimeException("123");
						}
						System.out.println(j);
					} finally {
						latch.countDown();
					}
				}
			});
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(2);
		
		p.shutdown();
	}
}