package com.yang.test.java;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.codehaus.jackson.map.ObjectMapper;

public class HttpClientUtil {

	public static final String host = "192.168.30.120:3106";
	public static final ObjectMapper mapper = new ObjectMapper();
	
	private static HttpClientBuilder hb = null;

	static {
		try {

	        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60 * 1000).setSocketTimeout(60 * 1000).setConnectionRequestTimeout(60 * 1000).build();
			
			
			LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			cm.setMaxTotal(500);
			cm.setDefaultMaxPerRoute(200);
			hb = HttpClients.custom().setConnectionManager(cm);
			hb.setDefaultRequestConfig(requestConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CloseableHttpClient getHttpClient() {
		return hb.build();
	}
}