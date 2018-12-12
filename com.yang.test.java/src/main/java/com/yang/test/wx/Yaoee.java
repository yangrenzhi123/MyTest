package com.yang.test.wx;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Yaoee {

	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException {
		sendMessage();
	}

	public static void getAccessToken() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcc79d67b43519897&secret=4a9bb164fc23668193b3263ebfbb71ee");
		HttpResponse response = httpClient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}
	


	public static void sendMessage() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=16_bnyP6wy9oVNlOE-rjQj6x9GcxPm685nkujRf10Y5t2UkFHc3lz0Asge8xh-SRVQAL37Rnirys4UR5u0CS1BYVePoLSIsdkfiQyDxA9L54m6ZGq3GBY3Bzk4as7J18ABL29JQaXaj8FB2HwsNNVFbAJAYGQ");
		
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