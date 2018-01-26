package com.yang.test.java.HttpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

@SuppressWarnings("deprecation")
public class Test {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		testProxy();
	}
	
	public static void main4(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=5_6R3s3uNlQ2mvgxifHbd6MFBBmSpNfyUNL7nH3ofLDVp8jZJ1aRjppX6SA0AXBE884a6ig3em23_urErocxjDKSB77JVSrymcRriAWtPaF1d0FkpsZol2xmkc3jADJqPz6R-Vf7FOC3AB2wS3AXAbAAALIL");
		HttpResponse response = httpClient.execute(get);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity, "utf-8");
		result.replaceAll("\\", "");
		System.out.println(result);
	}

	public static void testProxy() throws ClientProtocolException, IOException {
	    HttpHost proxy = new HttpHost("127.0.0.1", 8888); 
	    RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		HttpGet get = new HttpGet("http://www.baidu.com");
		get.setConfig(config);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse response = httpClient.execute(get);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity, "utf-8");
		System.out.println(result);
	}

	public static void main3(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=nNABUQ1OGTIq2uP90U1iVAnfDqHBM3mRrsCym4vt75STnitKcZpv4aCY46e7Yzv1HtkC0ASwxUZhP5nOSzUTkPro3iw7FZstOifVj1_dnnQPLUMyGTK4Jui9y8C-YXkUJFJeAEAUQU");
		String data = 
		"{"+
		"  \"touser\": \"ojD0F1DnQOIklqRxT-i-NCzyiqZA\","+
		"  \"template_id\": \"7B0NnHfBnisHtgtxfuvVl7iLCFV88UaXalWECcdF6dg\","+
		"  \"url\": \"http://www.baidu.com\","+
		"  \"data\": {\"keyword1\":{\"value\":123,\"color\":\"#0000FF\"}}"+
		"}";
		StringEntity entity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);
		
		
		HttpResponse response = httpClient.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}

	public static void main2(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet method = new HttpGet("/hug_interview/getPhysicalExamReport.token?organCode=47174063-8&pageNum=1&pageSize=1&startDate=2017-11-01&endDate=2017-11-28");

		HttpHost host = new HttpHost("test.joinhealth.cn", 80, "http");
		HttpResponse response = httpClient.execute(host, method);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);

		ObjectMapper mapper = new ObjectMapper();

		Data data = mapper.readValue(result, Data.class);
		Data2 data2 = mapper.readValue(data.getModel(), Data2.class);
		System.out.println(data2);
	}

	private static SSLConnectionSocketFactory createSSLConnSocketFactory() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
			public void verify(String host, SSLSocket ssl) throws IOException {
			}

			public void verify(String host, X509Certificate cert) throws SSLException {
			}
			public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
			}
		});
		return sslsf;
	}
}