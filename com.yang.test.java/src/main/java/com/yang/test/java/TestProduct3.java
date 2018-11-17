package com.yang.test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TestProduct3 {

	static String logPath = "C:/1.txt";
	static RandomAccessFile f;
	static List<Runnable> rl;
	static int count = 9000;
	static CountDownLatch counttime;
	static long totalDistance;
	
	static Integer countZhengchang;
	static Integer count500;
	static Integer count502;
	static Integer countTimeout;
	static Integer countException;
	
	static long totalTime;
	static String ip="127.0.0.1";
	
	public static void main(String[] args) throws InterruptedException, IOException {
		File log = new File(logPath);
		if(log.exists()) {
			log.delete();
		}
		log.createNewFile();
		
		f = new RandomAccessFile("C:/1.txt", "rw");
		
		
		RequestConfig config = RequestConfig.custom().setConnectTimeout(300000).setConnectionRequestTimeout(300000).setSocketTimeout(300000).build();  

		final HttpGet p3 = new HttpGet("http://"+ip+":8080/");
		p3.setHeader("Connection", "Keep-Alive");
		final List<CloseableHttpClient> hcl = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			CloseableHttpClient hc = HttpClients.custom().setDefaultRequestConfig(config).build();
			hcl.add(hc);
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


//						FileChannel fileChannel = f.getChannel();
//						ByteBuffer buf = ByteBuffer.allocate(1024);
//						buf.clear();
//						buf.put("已返回\r\n".getBytes());
//						buf.flip();
//						while (buf.hasRemaining()) {
//							fileChannel.write(buf);
//						}
						
						int httpCode = response.getStatusLine().getStatusCode();
						if(httpCode == 500) {
							synchronized (count500) {
								count500 = count500 + 1;
							}
						}else if(httpCode == 502) {
							synchronized (count502) {
								count502 = count502 + 1;
							}
						}else {
							synchronized (countZhengchang) {
								countZhengchang = countZhengchang + 1;
							}
						}
					} catch (HttpHostConnectException e) {
						synchronized (countTimeout) {
							countTimeout = countTimeout + 1;
						}
					} catch (Exception e) {
						synchronized (countException) {
							countException = countException + 1;
						}
					} finally {
						counttime.countDown();
					}
				}
			});
		}
		
		totalTime = System.currentTimeMillis();
		for(int i=0;i<2000;i++) {
			test(i);
		}
		
		Thread.sleep(2000);
	}

	static void test(int index) throws InterruptedException, IOException {
		counttime = new CountDownLatch(count);
		countZhengchang = 0;
		count500 = 0;
		count502 = 0;
		countTimeout = 0;
		countException = 0;

		List<Thread> l = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			l.add(new Thread(rl.get(i)));
		}
		
		
		long a = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			l.get(i).start();
		}
		counttime.await();
		long distance = System.currentTimeMillis() - a;
		
		int count = 0;
		Process p = Runtime.getRuntime().exec("netstat -ano");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			if(line.contains("ESTABLISHED") && line.contains(ip)) {
				count++;
			}
		}
		br.close();
		br = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GBK"));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
		
		if(index == 0) {
			System.out.println("第一次耗时："+distance+"，平均耗时：不计入平均时，正常数："+countZhengchang+"，500："+count500+"，502："+count502+"，超时数："+countTimeout+"，异常数："+countException+"，连接数："+count+"，总耗时："+(System.currentTimeMillis()-totalTime));
			return ;
		}
		totalDistance = totalDistance + distance;
		long avg = totalDistance / index;
		System.out.println("本次耗时："+distance+"，平均耗时："+avg+",正常数："+countZhengchang+"，500："+count500+"，502："+count502+"，超时数："+countTimeout+"，异常数："+countException+"，连接数："+count+"，总耗时："+(System.currentTimeMillis()-totalTime));
	}
}