package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class EsInsert {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));

		BulkRequest bulkRequest = new BulkRequest();


		String jsonString = "{\"id\":2,\"description\":\"one\"}";
		
		IndexRequest request = new IndexRequest("ytest");
		request.id("-X4b33UBIMNBma3PYcLu");
		request.source(jsonString, XContentType.JSON);
		bulkRequest.add(request);

		client.bulk(bulkRequest, RequestOptions.DEFAULT);
		client.close();
	}
}