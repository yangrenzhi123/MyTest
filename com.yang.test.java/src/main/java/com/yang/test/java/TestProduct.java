package com.yang.test.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class TestProduct {

	public static void main(String[] args) throws InterruptedException, IOException {
		while (true) {
			test();
		}
	}

	public static HttpPost getPost() {
		HttpPost p1 = new HttpPost("http://192.168.8.70:3106/api-console/Product/l");
		StringEntity e1 = new StringEntity("{\"pageNum\":\"1\",\"pageSize\":\"10\"}", "utf-8");
		e1.setContentEncoding("UTF-8");
		e1.setContentType("application/json");
		p1.setEntity(e1);

		HttpPost p2 = new HttpPost("http://192.168.8.70:3106/api-console/t/save");
		StringEntity e2 = new StringEntity("{\"id\":\"1\",\"name\":\2\",\"type\":\"3\"}","utf-8");
		e2.setContentEncoding("UTF-8");
		e2.setContentType("application/json");
		p2.setEntity(e2);

		int a = new Random().nextInt(2);
		if (a == 0) {
			return p1;
		} else {
			return p1;
		}
	}

	static void test() throws InterruptedException, IOException {
		int count = 1000;
		final CountDownLatch latch = new CountDownLatch(count);

		final CloseableHttpClient hc = HttpClientUtil.getHttpClient();
		List<Thread> l = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Thread a = new Thread(new Runnable() {
				public void run() {
					try {
						HttpResponse response = hc.execute(getPost());
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