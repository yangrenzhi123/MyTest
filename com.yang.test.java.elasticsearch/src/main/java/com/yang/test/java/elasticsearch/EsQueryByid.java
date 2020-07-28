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
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.26.199", 9200, "http")));

		GetRequest r = new GetRequest("test4"/*, "h_recycle_record", "1"*/);
		r.id("1");
		GetResponse p = client.get(r, RequestOptions.DEFAULT);
		Map<String, Object> source = p.getSource();

		System.out.println(source);

		client.close();
	}
}