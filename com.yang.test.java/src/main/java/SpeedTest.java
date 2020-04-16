public class SpeedTest {

	public static void main(String[] args) {
		int threadNum;
		if (args.length > 0) {
			String p0 = args[0];
			threadNum = Integer.parseInt(p0);
		}else {
			threadNum = 1;
		}

		try {
			for (int i = 0; i < threadNum; i++) {
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
		long max = 100000000000L;
		System.out.println(max);

		long l = System.currentTimeMillis();
		for (long i = 0; i < max; i++) {
		}

		System.out.println(System.currentTimeMillis() - l);
	}
}