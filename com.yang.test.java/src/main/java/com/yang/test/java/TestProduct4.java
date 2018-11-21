package com.yang.test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
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

public class TestProduct4 {

	static String logPath = "C:/1.txt";
	static RandomAccessFile f;
	static List<Runnable> rl;
	static int count = 8000;
	static CountDownLatch counttime;
	static long totalDistance;

	static int[] countZhengchangArray;
	static int[] count500Array;
	static int[] count502Array;
	static int[] countTimeoutArray;
	static int[] countExceptionArray;
	
	static long totalTime;
	static String ip="192.168.10.240";
	
	public static void main(String[] args) throws InterruptedException, IOException {
		File log = new File(logPath);
		if(log.exists()) {
			log.delete();
		}
		log.createNewFile();
		
		f = new RandomAccessFile("C:/1.txt", "rw");
		
		
		RequestConfig config = RequestConfig.custom().setConnectTimeout(300000).setConnectionRequestTimeout(300000).setSocketTimeout(300000).build();  

		final HttpGet p1 = new HttpGet("http://"+ip+":8080/user/webflow/showUser");
		p1.setHeader("Connection", "Keep-Alive");

		final HttpGet p2 = new HttpGet("http://"+ip+":8081/user/webflow/showUser");
		p2.setHeader("Connection", "Keep-Alive");
		
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
						HttpGet p;
						if(j % 2 == 0) {
							p = p1;
						}else {
							p = p2;
						}
						CloseableHttpClient hc = hcl.get(j);
						HttpResponse response = hc.execute(p);
						HttpEntity httpEntity = response.getEntity();
						EntityUtils.toString(httpEntity, "utf-8");

						int httpCode = response.getStatusLine().getStatusCode();
						if(httpCode == 500) {
							count500Array[j] = 1;
						}else if(httpCode == 502) {
							count502Array[j] = 1;
						}else {
							countZhengchangArray[j] = 1;
						}
					} catch (HttpHostConnectException e) {
						countTimeoutArray[j] = 1;
					} catch (Exception e) {
						countExceptionArray[j] = 1;
					} finally {
						counttime.countDown();
					}
				}
			});
		}
		
		totalTime = System.currentTimeMillis();
		for(int i=0;i<30;i++) {
			test(i);
		}
		
		Thread.sleep(2000);
	}

	static void test(int index) throws InterruptedException, IOException {
		countZhengchangArray = new int[count];
		count500Array = new int[count];
		count502Array = new int[count];
		countTimeoutArray = new int[count];
		countExceptionArray = new int[count];

		counttime = new CountDownLatch(count);

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
		
		int countZhengchang=0;
		for(int i : countZhengchangArray) {
			if(i == 1) {
				countZhengchang = countZhengchang + 1;
			}
		}
		
		int count500=0;
		for(int i : count500Array) {
			if(i == 1) {
				count500 = count500 + 1;
			}
		}
		
		int count502=0;
		for(int i : count502Array) {
			if(i == 1) {
				count502 = count502 + 1;
			}
		}
		
		int countTimeout=0;
		for(int i : countTimeoutArray) {
			if(i == 1) {
				countTimeout = countTimeout + 1;
			}
		}
		
		int countException=0;
		for(int i : countExceptionArray) {
			if(i == 1) {
				countException = countException + 1;
			}
		}

		if(index == 0) {
			System.out.println("��һ�κ�ʱ��"+distance+"��ƽ����ʱ��������ƽ��ʱ����������"+countZhengchang+"��500��"+count500+"��502��"+count502+"����ʱ����"+countTimeout+"���쳣����"+countException+"����������"+count+"���ܺ�ʱ��"+(System.currentTimeMillis()-totalTime));
			return ;
		}
		totalDistance = totalDistance + distance;
		long avg = totalDistance / index;
		System.out.println("���κ�ʱ��"+distance+"��ƽ����ʱ��"+avg+",��������"+countZhengchang+"��500��"+count500+"��502��"+count502+"����ʱ����"+countTimeout+"���쳣����"+countException+"����������"+count+"���ܺ�ʱ��"+(System.currentTimeMillis()-totalTime));
	}
}