package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

public class EsCreateIndex {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.8.70", 9200, "http")));

		createIndex(client);

		client.close();
	}

	// 创建索引
	public static void createIndex(RestHighLevelClient client) throws IOException {
		CreateIndexRequest request = new CreateIndexRequest("ytest");
		buildIndexMapping(request);
		client.indices().create(request, RequestOptions.DEFAULT);
	}

	// 设置index的mapping
	public static void buildIndexMapping(CreateIndexRequest request) {
		request.mapping("{\"properties\": {\"sql_text\": {\"type\": \"keyword\"},\"s0\": {\"type\": \"long\"},\"s1\": {\"type\": \"long\"},\"s2\": {\"type\": \"long\"},\"s3\": {\"type\": \"long\"},\"s4\": {\"type\": \"long\"},\"s5\": {\"type\": \"long\"}}}", XContentType.JSON);
	}
}