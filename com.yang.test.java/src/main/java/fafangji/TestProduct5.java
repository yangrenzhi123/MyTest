package fafangji;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class TestProduct5 {

	static String ip = "127.0.0.1:3114";//"192.168.10.239";
	static int count = 500;
	
	static List<Runnable> rl;
	static CountDownLatch counttime;
	static long totalDistance;

	static int[] countZhengchangArray;
	static int[] count500Array;
	static int[] count502Array;
	static int[] countTimeoutArray;
	static int[] countExceptionArray;
	
	static long totalTime;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(300000).setConnectionRequestTimeout(300000).setSocketTimeout(300000).build();  



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
						
						String orderNo = UUID.randomUUID().toString().replaceAll("-", "");

						String mySign = "1545793115" + "LYZH2015" + orderNo;
						String sign = MD5Util.MD5Encode(mySign, "UTF-8").toUpperCase();
						
						final HttpGet p = new HttpGet("http://"+ip+"/NewVendingMachine/check?mchinesn=10051001080004&ordersn="+orderNo+"&timestamp=1545793115&code=LYZH511000000401&sign="+sign);
						p.setHeader("Connection", "Keep-Alive");
						final HttpGet p1 = new HttpGet("http://"+ip+"/NewVendingMachine/checkresult?mchinesn=10051001080004&ordersn="+orderNo+"&timestamp=1545793115&goodsCode=ZJLY01021801016100&sign="+sign+"&mark=00000");
						p1.setHeader("Connection", "Keep-Alive");
						
						
						HttpResponse response = hc.execute(p);
						HttpEntity httpEntity = response.getEntity();
						String content = EntityUtils.toString(httpEntity, "utf-8");
						if(!content.startsWith("{\"code\":\"0\"")) {
							System.out.println(content);
						}
						

						response = hc.execute(p1);
						httpEntity = response.getEntity();
						content = EntityUtils.toString(httpEntity, "utf-8");
						if(!content.startsWith("{\"code\":\"0\"")) {
							System.out.println(content);
						}
						

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
		for(int i=0;i<50;i++) {
			test(i);
		}
		
		
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