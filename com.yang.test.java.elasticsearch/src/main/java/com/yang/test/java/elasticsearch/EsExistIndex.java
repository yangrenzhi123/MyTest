package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

public class EsExistIndex {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));

		GetIndexRequest request = new GetIndexRequest("slowquery-mysql-2020-10-29");

		boolean existing = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(existing);

		client.close();
	}
}