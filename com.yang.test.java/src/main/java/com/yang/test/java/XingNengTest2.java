package com.yang.test.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class XingNengTest2 {

	static List<Runnable> rl;
	static int count = 400;
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
		RequestConfig config = RequestConfig.custom().setConnectTimeout(300000).setConnectionRequestTimeout(300000).setSocketTimeout(300000).build();  

		final HttpGet p = new HttpGet("http://127.0.0.1:3107/dao/fl/equipdispenser/test");
		p.setHeader("Connection", "Keep-Alive");

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
		//for(int i=0;i<30;i++) {
			test(0);
		//}
		
		
		for(CloseableHttpClient hc : hcl) {
			hc.close();
		}
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
			System.out.println("第一次耗时："+distance+"，平均耗时：不计入平均时，正常数："+countZhengchang+"，500："+count500+"，502："+count502+"，超时数："+countTimeout+"，异常数："+countException+"，连接数："+count+"，总耗时："+(System.currentTimeMillis()-totalTime));
			return ;
		}
		totalDistance = totalDistance + distance;
		long avg = totalDistance / index;
		System.out.println("本次耗时："+distance+"，平均耗时："+avg+",正常数："+countZhengchang+"，500："+count500+"，502："+count502+"，超时数："+countTimeout+"，异常数："+countException+"，连接数："+count+"，总耗时："+(System.currentTimeMillis()-totalTime));
	}
}