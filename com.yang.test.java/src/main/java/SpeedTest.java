public class SpeedTest {

	public static void main(String[] args) {
		try {

			for (int i = 0; i < 4; i++) {
				new Thread(new Runnable() {

					public void run() {
						test28();
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test28() {
		long l = System.currentTimeMillis();

		long max = 100000000000L;
		System.out.println(max);
		for (long i = 0; i < max; i++) {
		}

		System.out.println(System.currentTimeMillis() - l);
	}
}