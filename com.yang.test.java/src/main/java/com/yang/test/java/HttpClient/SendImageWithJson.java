package com.yang.test.java.HttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class SendImageWithJson {

	public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException {
		File f = new File("C:/1.png");
		FileInputStream fis = new FileInputStream(f);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		fis.close();
		
		Dto dto = new Dto();
		dto.setData(b);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(dto);
		System.out.println("json:"+json);
		
		String m1 = getMd5(json);
		
		
		CloseableHttpClient hc = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8080/");
		StringEntity entity = new StringEntity(json, ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);

		HttpResponse response = hc.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println("result:"+result);
		

		String m2 = getMd5(result);
		
		System.out.println(m1.equals(m2));
	}
	
	private static String getMd5(String data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data.getBytes());
		String sign = new BigInteger(1, md.digest()).toString(16).toUpperCase();
		return sign;
	}
}

class Dto {
	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}