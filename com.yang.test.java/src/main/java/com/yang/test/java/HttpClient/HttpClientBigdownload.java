package com.yang.test.java.HttpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientBigdownload {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		long a = System.currentTimeMillis();
		for(int i=0;i<1024;i++) {
			HttpGet get = new HttpGet("http://127.0.0.1:8080");
			HttpClient hc = HttpClients.createDefault();
			HttpResponse response = hc.execute(get);
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity);
			System.out.println("結果長度：" + result.length());
		}
		System.out.println("耗時：" + (System.currentTimeMillis() - a));
	}
}