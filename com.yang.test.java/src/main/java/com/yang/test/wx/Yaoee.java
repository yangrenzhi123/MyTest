package com.yang.test.wx;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Yaoee {

	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException {
		messageTemplateSend();
	}

	/** access_token被踢掉之后，5分钟失效 */
	public static void getAccessToken() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa6ed663216b05946&secret=e4567c9f72a22d381d1bb8e52e74dc8c");
		HttpResponse response = httpClient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}

	public static void batchget_material() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=16_2Ef0e_lXwRktUV0akjMxA4UuRHiDrehwLa5BOkUXhr7yu3pJPZ7ahOCZLHVpS3F22Prrvg7b7TtakR1tv4tp1jic0hYU3wMGC2hCzyifKzjH-CLuvwBeVieejaKDDaTTy3zaHv3ARnUd-_e4DHDjAFAQUQ");
		
		String data = 
		"{"+
		"    \"type\":\"news\","+
		"    \"offset\":\"0\","+
		"    \"count\":\"20\""+
		"}";
		System.out.println(data);
		StringEntity entity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);

		HttpResponse response = httpClient.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}

	/**
	 * 要求：用户在24小时之内与公众号有过交互
	 * 
	 */
	public static void messageSend() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=21_geIKKQOTAPzzU4eljSK79QtLs98M69lS48zhhqUjfqBkhlW3Np0OxODfTWz01NxPGI8wP2uheYcnJ2rL00dQfDPF4slPd3svv1oBSBA8F7P9BklgdqM9-LD6ihSk25fk9zhzJPpfgj3EqB80EFWfAIAWXO");
		
		String data = 
		"{"+
		"    \"touser\":\"oEynf0jA5bCSa4QraReOd2tZ_zjk\","+
		"    \"msgtype\":\"text\","+
		"    \"text\":{\"content\":\"测试客服消息，内容：111111\"}"+
		"}";
		System.out.println(data);
		StringEntity entity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);

		HttpResponse response = httpClient.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}
	


	/**
	 * 要求：用户在24小时之内与公众号有过交互
	 * 
	 */
	public static void messageSend2() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=16_2Ef0e_lXwRktUV0akjMxA4UuRHiDrehwLa5BOkUXhr7yu3pJPZ7ahOCZLHVpS3F22Prrvg7b7TtakR1tv4tp1jic0hYU3wMGC2hCzyifKzjH-CLuvwBeVieejaKDDaTTy3zaHv3ARnUd-_e4DHDjAFAQUQ");
		
		String data = 
		"{"+
		"    \"touser\":\"oEynf0jA5bCSa4QraReOd2tZ_zjk\","+
		"    \"msgtype\":\"mpnews\","+
		"    \"mpnews\":{\"media_id\":\"aU_co04b70_nfVaTgT0OvqAf1YyviehGUNBoTRwENwc\"}"+
		"}";
		System.out.println(data);
		StringEntity entity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);

		HttpResponse response = httpClient.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}

	public static void messageTemplateSend() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=21_geIKKQOTAPzzU4eljSK79QtLs98M69lS48zhhqUjfqBkhlW3Np0OxODfTWz01NxPGI8wP2uheYcnJ2rL00dQfDPF4slPd3svv1oBSBA8F7P9BklgdqM9-LD6ihSk25fk9zhzJPpfgj3EqB80EFWfAIAWXO");
		
		String data = 
		      "{"+
	          "    \"touser\":\"oEynf0jA5bCSa4QraReOd2tZ_zjk\","+
	          "    \"template_id\":\"9Xg56Khtu22dKTZn93Oxb4XGQsqK-VXsu2v4wCaqSQs\","+
	          "    \"url\":\"http://weixin.qq.com/download\","+
	          "    \"miniprogram\":{"+
	          "      \"appid\":\"\","+
	          "      \"pagepath\":\"\""+
	          "    },"+
	          "    \"data\":{"+
	          "            \"first\": {"+
	          "                \"value\":\"test1\","+
	          "                \"color\":\"#173177\""+
	          "            },"+
	          "            \"keyword1\":{"+
	          "                \"value\":\"test2\","+
	          "                \"color\":\"#173177\""+
	          "            },"+
	          "            \"keyword2\": {"+
	          "                \"value\":\"test3\","+
	          "                \"color\":\"#173177\""+
	          "            },"+
	          "            \"keyword3\": {"+
	          "                \"value\":\"test4\","+
	          "                \"color\":\"#173177\""+
	          "            },"+
	          "            \"remark\":{"+
	          "                \"value\":\"test5\","+
	          "                \"color\":\"#173177\""+
	          "            }"+
	          "    }"+
	          "}";
		System.out.println(data);
		StringEntity entity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);

		HttpResponse response = httpClient.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}
}