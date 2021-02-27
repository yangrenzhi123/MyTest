import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.ttl.TransmittableThreadLocal;

public class ThreadLocalUtils {

	public static final ThreadLocal<DateFormat> yyyyMMddHHmmss = new ThreadLocal<>();
	public static final InheritableThreadLocal<String> test = new InheritableThreadLocal<>();
	public static final ThreadLocal<String> test2 = new ThreadLocal<>();
	public static final ThreadLocal<String> test3 = new TransmittableThreadLocal<>();
	
	public static final ExecutorService p = Executors.newFixedThreadPool(10);

	public static DateFormat getyyyyMMddHHmmss() {
		DateFormat df = yyyyMMddHHmmss.get();
		if (df == null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			yyyyMMddHHmmss.set(df);
		}
		return df;
	}

	public static void main(String[] args) {
		test3.set("ok");


		// 线程初始的时候，会从父线程copy线程变量的地址引用。
		new Thread(new Runnable() {
			public void run() {
				System.out.println(test3.get());
			}
		}).start();
		
		// 数据发生修改，子线程里面的数据不会修改。
		test3.set("ok-2");
		System.out.println();

		for (int i = 0; i < 10; i++) {
			p.execute(new Runnable() {
				public void run() {
					System.out.println(test3.get());
				}
			});
		}
		
		p.shutdown();
	}
}