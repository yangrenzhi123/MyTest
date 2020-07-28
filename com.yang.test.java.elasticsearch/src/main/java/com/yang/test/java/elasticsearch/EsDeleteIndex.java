package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsDeleteIndex {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.26.199", 9200, "http")));

		DeleteIndexRequest request = new DeleteIndexRequest("test");

		AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(response.toString());

		client.close();
	}
}