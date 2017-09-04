package com.yang.test.java;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException {
		final Num i = new Num();
		while (true) {
			new Thread(new Runnable() {

				public void run() {

					try {
						HttpHost host = new HttpHost("192.168.5.152", 8080, "http");

						HttpGet method = null;
						synchronized (i) {
							method = new HttpGet("/?v=" + i);
							i.add();
						}

						CloseableHttpClient httpClient = HttpClients.createDefault();

						CloseableHttpResponse response = httpClient.execute(host, method);
						HttpEntity entity = response.getEntity();
						String s = EntityUtils.toString(entity);
						// System.out.println(s);

						// method.abort();
						// EntityUtils.consume(entity);
						// response.close();
						// httpClient.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			Thread.currentThread().sleep(100);
		}
	}
}

class Num {
	public int i = 0;

	public void add() {
		i++;
	}

	public String toString() {
		return i + "";
	}
}