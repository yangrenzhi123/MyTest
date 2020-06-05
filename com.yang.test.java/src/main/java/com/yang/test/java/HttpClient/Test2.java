package com.yang.test.java.HttpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(300000).setConnectTimeout(300000).setSocketTimeout(300000).build();
		
		HttpGet get = new HttpGet("http://172.17.144.93:31143");
		get.setConfig(config);
		
		CloseableHttpClient hc = HttpClients.createDefault();

		long a = System.currentTimeMillis();
		try {
			HttpResponse response = hc.execute(get);
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity);
			System.out.println(result);
		}catch(Exception e) {
			System.out.println(System.currentTimeMillis() - a);
			e.printStackTrace();
		}
	}
}