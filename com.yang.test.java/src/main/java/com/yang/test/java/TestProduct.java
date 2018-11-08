package com.yang.test.java;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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

	static String logPath = "C:/1.txt";
	static RandomAccessFile f;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		File log = new File(logPath);
		if(log.exists()) {
			log.delete();
		}
		log.createNewFile();
		
		f = new RandomAccessFile("C:/1.txt", "rw");
		
		
		for(int i=0;i<100;i++) {
			test();
		}
		
		Thread.sleep(2000);
	}

	public static HttpPost getPost() {
		StringEntity e3 = new StringEntity("{\"dictionarydata\":\"true\"}", "utf-8");
		e3.setContentType("application/json");
		HttpPost p3 = new HttpPost("http://192.168.30.120:4108/api-console/dictionary/menuestatues.do");
		p3.setHeader("Cookie", "lyzh-saas=s%3ADixNV7VMywo4fbfOVffR19k8PpN_xWxt.VrTg50Yp1U4uMJo0Pa9%2FHvgd3aZltJ0zTgB6iqp4MHw");
		p3.setEntity(e3);
		
		HttpPost p1 = new HttpPost("http://192.168.30.120:4108/api-console/Product/l");
		StringEntity e1 = new StringEntity("{\"pageNum\":\"1\",\"pageSize\":\"10\"}", "utf-8");
		e1.setContentEncoding("UTF-8");
		e1.setContentType("application/json");
		p1.setEntity(e1);

		HttpPost p2 = new HttpPost("http://192.168.30.120:3106/api-console/t/save");
		StringEntity e2 = new StringEntity("{\"id\":\"1\",\"name\":\2\",\"type\":\"3\"}","utf-8");
		e2.setContentEncoding("UTF-8");
		e2.setContentType("application/json");
		p2.setEntity(e2);

		int a = new Random().nextInt(2);
		if (a == 0) {
			return p3;
		} else {
			return p3;
		}
	}

	static void test() throws InterruptedException, IOException {
		int count = 10;
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


						FileChannel fileChannel = f.getChannel();
						ByteBuffer buf = ByteBuffer.allocate(10240);
						buf.clear();
						buf.put((this.hashCode()+result+"\r\n").getBytes());
						buf.flip();
						while (buf.hasRemaining()) {
							fileChannel.write(buf);
						}
						
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