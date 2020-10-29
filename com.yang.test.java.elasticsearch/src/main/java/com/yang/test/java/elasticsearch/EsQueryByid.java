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
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));

		GetRequest r = new GetRequest("slowquery-mysql-2020-10-27");
		r.id("0nqNbHUBIMNBma3Py8ON");
		GetResponse p = client.get(r, RequestOptions.DEFAULT);
		Map<String, Object> source = p.getSource();

		System.out.println(source);

		client.close();
	}
}