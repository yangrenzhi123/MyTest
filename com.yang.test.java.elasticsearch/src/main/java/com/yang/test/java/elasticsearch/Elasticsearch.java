package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class Elasticsearch {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.8.70", 9200, "http")));
		client.close();
	}
}