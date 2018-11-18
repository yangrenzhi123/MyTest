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
import java.util.UUID;
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
	static int count = 1100;
	static CountDownLatch counttime;
	static long totalDistance;
	
	
	
	static FileChannel countZhengchang;
	static FileChannel count500;
	static FileChannel count502;
	static FileChannel countTimeout;
	static FileChannel countException;
	
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
						EntityUtils.toString(httpEntity, "utf-8");

						int httpCode = response.getStatusLine().getStatusCode();
						if(httpCode == 500) {
							String newData = "1";
							ByteBuffer buf = ByteBuffer.allocate(1);
							buf.clear();
							buf.put(newData.getBytes());
							buf.flip();
							count500.write(buf);
						}else if(httpCode == 502) {
							String newData = "1";
							ByteBuffer buf = ByteBuffer.allocate(1);
							buf.clear();
							buf.put(newData.getBytes());
							buf.flip();
							count502.write(buf);
						}else {
							String newData = "1";
							ByteBuffer buf = ByteBuffer.allocate(1);
							buf.clear();
							buf.put(newData.getBytes());
							buf.flip();
							countZhengchang.write(buf);
						}
					} catch (HttpHostConnectException e) {
						String newData = "1";
						ByteBuffer buf = ByteBuffer.allocate(1);
						buf.clear();
						buf.put(newData.getBytes());
						buf.flip();
						try {
							countTimeout.write(buf);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (Exception e) {
						String newData = "1";
						ByteBuffer buf = ByteBuffer.allocate(1);
						buf.clear();
						buf.put(newData.getBytes());
						buf.flip();
						try {
							countException.write(buf);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} finally {
						counttime.countDown();
					}
				}
			});
		}
		
		totalTime = System.currentTimeMillis();
		for(int i=0;i<20;i++) {
			test(i);
		}
		
		Thread.sleep(2000);
	}

	static void test(int index) throws InterruptedException, IOException {
		counttime = new CountDownLatch(count);

		String countZhengchangFM = UUID.randomUUID().toString();
		String count500FM = UUID.randomUUID().toString();
		String count502FM = UUID.randomUUID().toString();
		String countTimeoutFM = UUID.randomUUID().toString();
		String countExceptionFM = UUID.randomUUID().toString();
		
		File txt;
		txt = new File("C:/tmp/"+countZhengchangFM);
		txt.createNewFile();
		txt = new File("C:/tmp/"+count500FM);
		txt.createNewFile();
		txt = new File("C:/tmp/"+count502FM);
		txt.createNewFile();
		txt = new File("C:/tmp/"+countTimeoutFM);
		txt.createNewFile();
		txt = new File("C:/tmp/"+countExceptionFM);
		txt.createNewFile();

		countZhengchang = new RandomAccessFile("C:/tmp/"+countZhengchangFM, "rw").getChannel();
		count500 = new RandomAccessFile("C:/tmp/"+count500FM, "rw").getChannel();
		count502 = new RandomAccessFile("C:/tmp/"+count502FM, "rw").getChannel();
		countTimeout = new RandomAccessFile("C:/tmp/"+countTimeoutFM, "rw").getChannel();
		countException = new RandomAccessFile("C:/tmp/"+countExceptionFM, "rw").getChannel();

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
			System.out.println("第一次耗时："+distance+"，平均耗时：不计入平均时，正常数："+new File("C:/tmp/"+countZhengchangFM).length()+"，500："+new File("C:/tmp/"+count500FM).length()+"，502："+new File("C:/tmp/"+count502).length()+"，超时数："+new File("C:/tmp/"+countTimeoutFM).length()+"，异常数："+new File("C:/tmp/"+countExceptionFM).length()+"，连接数："+count+"，总耗时："+(System.currentTimeMillis()-totalTime));
			return ;
		}
		totalDistance = totalDistance + distance;
		long avg = totalDistance / index;
		System.out.println("本次耗时："+distance+"，平均耗时："+avg+",正常数："+new File("C:/tmp/"+countZhengchangFM).length()+"，500："+new File("C:/tmp/"+count500FM).length()+"，502："+new File("C:/tmp/"+count502FM).length()+"，超时数："+new File("C:/tmp/"+countTimeoutFM).length()+"，异常数："+new File("C:/tmp/"+countException).length()+"，连接数："+count+"，总耗时："+(System.currentTimeMillis()-totalTime));
	}
}