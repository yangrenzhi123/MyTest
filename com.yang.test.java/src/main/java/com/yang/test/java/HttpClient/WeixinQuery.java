package com.yang.test.java.HttpClient;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WeixinQuery {
	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException {
		String appid = "wxcc79d67b43519897";
		String mch_id = "1504805211";
		String nonce_str = UUID.randomUUID().toString().replaceAll("-", "");
		//String out_trade_no = "20181005165754284023";
		String out_trade_no = "20181130203422465001";
		
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/orderquery");
		String stringA="appid="+appid+"&mch_id="+mch_id+"&nonce_str="+nonce_str+"&out_trade_no="+out_trade_no;
		String stringSignTemp=stringA+"&key=fad0032fe0ab47e0be69c5d49b1052fd";
		

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(stringSignTemp.getBytes());
		String sign=new BigInteger(1, md.digest()).toString(16).toUpperCase();

		String data = 
		"<xml>"+
		"   <appid>"+appid+"</appid>"+
		"   <mch_id>"+mch_id+"</mch_id>"+
		"   <nonce_str>"+nonce_str+"</nonce_str>"+
		"   <out_trade_no>"+out_trade_no+"</out_trade_no>"+
		"   <sign>"+sign+"</sign>"+
		"</xml>";
		
		
		StringEntity entity = new StringEntity(data, ContentType.create("application/xml", "UTF-8"));
		post.setEntity(entity);
		
		
		HttpResponse response = httpClient.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
	}
}