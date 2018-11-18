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
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

@SuppressWarnings({"deprecation", "unused"})
public class Test {

	static CloseableHttpClient httpClient = null;
	
	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException {
		getJsapi();
	}
	public static void getAccessToken() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcc79d67b43519897&secret=4a9bb164fc23668193b3263ebfbb71ee");
		HttpResponse response = httpClient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}
	public static void getJsapi() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=15_U0S_OagCjRU8deeoULQv0E3fba1cF-v-aMw5CtY7_Sn5Pzk8_JhBqNo1K9kjXxhhMOWSbihk70oUKG8HhcsaTgjwr0DZ5-xx9Jie2IXdHdpQsVyHIfCDWifLbiV1B3Tg7HxcSVZVAzYQWQyDPYGgAJAXGO&type=jsapi");
		HttpResponse response = httpClient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}

	public static void testPooling() throws ClientProtocolException, IOException, NoSuchAlgorithmException {
		LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(2);
        cm.setDefaultMaxPerRoute(2);

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		HttpGet get = new HttpGet("http://jinnianshilongnian.iteye.com/blog/2089792");
		httpClient.execute(get);
		System.out.println(1);
		

		httpClient = HttpClients.custom().setConnectionManager(cm).build();
		get = new HttpGet("http://172.28.51.33:8081/login?from=%2F");
		httpClient.execute(get);
		System.out.println(2);
	}
	
	/** 一请求一连接 */
	public static void testSimple() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet("http://192.168.6.231:8089/hbplatform/");

		httpClient = HttpClients.createDefault();
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		httpClient.execute(get);
		
		System.out.println();
	}

	public static void https() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://localhost:8443");
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
		
		

		CloseableHttpClient hc = HttpClients.custom().setDefaultRequestConfig(config).build();
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