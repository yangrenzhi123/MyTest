
public class TestThread {

	public static void main(String[] args) {

		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println(1);
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						System.out.println("InterruptedException");
					}
				}
			}
		});
		t.start();

		System.out.println(t);t.interrupt();
	}
}
