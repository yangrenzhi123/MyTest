package com.yang.test.java.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(300000).setConnectTimeout(300000).setSocketTimeout(300000).build();
		
		ProtocolVersion http2 = new ProtocolVersion("HTTP", 2, 0);
		
		
		final CloseableHttpClient hc = HttpClients.createDefault();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<100;i++) {
						HttpGet get = new HttpGet("http://127.0.0.1:8080");
						get.setConfig(config);
						get.setProtocolVersion(http2);
						HttpResponse response = hc.execute(get);
						HttpEntity httpEntity = response.getEntity();
						String result = EntityUtils.toString(httpEntity);
						System.out.println(result);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<100;i++) {
						HttpGet get = new HttpGet("http://127.0.0.1:8080");
						get.setConfig(config);
						get.setProtocolVersion(http2);
						HttpResponse response = hc.execute(get);
						HttpEntity httpEntity = response.getEntity();
						String result = EntityUtils.toString(httpEntity);
						System.out.println(result);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		hc.close();
	}
}