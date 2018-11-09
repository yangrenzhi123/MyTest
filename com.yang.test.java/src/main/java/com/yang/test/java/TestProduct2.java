package com.yang.test.java;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TestProduct2 {

	static String logPath = "C:/1.txt";
	static RandomAccessFile f;
	static List<Runnable> rl;
	static int count = 4000;
	static CountDownLatch latch;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		File log = new File(logPath);
		if(log.exists()) {
			log.delete();
		}
		log.createNewFile();
		
		f = new RandomAccessFile("C:/1.txt", "rw");
		


		StringEntity e3 = new StringEntity("{\"dictionarydata\":\"true\"}", "utf-8");
		e3.setContentType("application/json");
		final HttpGet p3 = new HttpGet("http://192.168.10.238:3106/api-file/test");
		p3.setHeader("Cookie", "lyzh-saas=s%3ASC6Rj8eCCp48BQO286ZoahuoSKVHLxMA.cooCvTXOGf3agzeBdqGUhaC0iTq%2F0XZW5jcbkFmhxxI");
		p3.setHeader("Connection", "Keep-Alive");
		
		final List<CloseableHttpClient> hcl = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			hcl.add(HttpClients.createDefault());
		}
		
		rl = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			final int j = i;
			rl.add(new Runnable() {
				public void run() {
					try {
						CloseableHttpClient hc = hcl.get(j);
						HttpResponse response = hc.execute(p3);
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
		}
		
		
		for(int i=0;i<1000;i++) {
			test();
		}
		
		Thread.sleep(2000);
	}

	static void test() throws InterruptedException, IOException {
		latch = new CountDownLatch(count);

		
		
		
		List<Thread> l = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			l.add(new Thread(rl.get(i)));
		}
		long a = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			l.get(i).start();
		}

		latch.await();
		System.out.println(System.currentTimeMillis() - a);
	}
}