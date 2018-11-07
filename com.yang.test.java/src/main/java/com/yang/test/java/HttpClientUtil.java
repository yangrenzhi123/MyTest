package com.yang.test.java;

import javax.net.ssl.SSLContext;

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
			LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			cm.setMaxTotal(2000);
			cm.setDefaultMaxPerRoute(2000);
			hb = HttpClients.custom().setConnectionManager(cm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CloseableHttpClient getHttpClient() {
		return hb.build();
	}
}