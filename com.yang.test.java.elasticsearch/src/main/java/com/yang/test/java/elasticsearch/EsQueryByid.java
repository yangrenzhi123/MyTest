package com.yang.test.java.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsQueryByid {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("172.17.134.7", 9200, "http")));

		GetRequest r = new GetRequest("test");
		r.id("11521346");
		GetResponse p = client.get(r, RequestOptions.DEFAULT);
		Map<String, Object> source = p.getSource();

		System.out.println(source);

		client.close();
	}
}