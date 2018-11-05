

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class TestProduct {

	public static void main(String[] args) throws InterruptedException {
		int i=0;
		while (true) {
			if(i++ == 0) {
				test();
			}else {
				new Thread(new Runnable() {
					public void run() {
						try {
							test();
						}catch(Exception e) {
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

		HttpPost post = new HttpPost("http://192.168.30.120:3106/api-console/Product/l");

		StringEntity entity = new StringEntity("{\"pageNum\":\"1\",\"pageSize\":\"10\"}", "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);

		CloseableHttpClient hc = HttpClientUtil.getHttpClient();
		List<Thread> l = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Thread a = new Thread(new Runnable() {
				public void run() {
					try {
						HttpResponse response = hc.execute(post);
						HttpEntity httpEntity = response.getEntity();
						String result = EntityUtils.toString(httpEntity, "utf-8");

						int httpCode = response.getStatusLine().getStatusCode();
						if(httpCode == 500) {
							System.out.println(result);
						}
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