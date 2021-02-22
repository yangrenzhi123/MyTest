import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThreadLocalUtils {

	public static final ThreadLocal<DateFormat> yyyyMMddHHmmss = new ThreadLocal<>();
	public static final InheritableThreadLocal<String> test = new InheritableThreadLocal<>();
	public static final ThreadLocal<String> test2 = new ThreadLocal<>();

	public static DateFormat getyyyyMMddHHmmss() {
		DateFormat df = yyyyMMddHHmmss.get();
		if (df == null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			yyyyMMddHHmmss.set(df);
		}
		return df;
	}

	public static void main(String[] args) {
		test2.set("ok");


		new Thread(new Runnable() {
			public void run() {
				new Thread(new Runnable() {
					public void run() {
						System.out.println(test2.get());
					}
				}).start();
			}
		}).start();
	}
}