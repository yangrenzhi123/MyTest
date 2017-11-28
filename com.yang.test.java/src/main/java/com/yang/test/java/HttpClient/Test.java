package com.yang.test.java.HttpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class Test {

	public static void main(String[] args) throws ClientProtocolException, IOException {
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
}