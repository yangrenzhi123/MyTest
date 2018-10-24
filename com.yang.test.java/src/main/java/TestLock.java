import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

	ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {

		new Thread(new Runnable() {

			public void run() {
				new TestLock().t();
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				new TestLock().t();
			}
		}).start();
	}

	public void t() {
		try {
			lock.lock();
			System.out.println(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}