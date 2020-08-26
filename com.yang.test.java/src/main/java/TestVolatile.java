
public class TestVolatile {

	public static int i = 0;

	public static void main(String[] args) {

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(true) {
					if(i == 1) {
						System.out.println(i);
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				i = 1;
			}
		}).start();
	}
}