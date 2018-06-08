import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				latch.countDown();
			}
		}).start();

		latch.await();

		System.out.println(2);
	}
}