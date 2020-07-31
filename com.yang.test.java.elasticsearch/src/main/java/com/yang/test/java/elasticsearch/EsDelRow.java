package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class EsDelRow {

	public static void main(String[] args) throws IOException {
		RestClientBuilder rcb = RestClient.builder(new HttpHost("172.17.134.7", 9200, "http"));

		final RestHighLevelClient client = new RestHighLevelClient(rcb);

		DeleteRequest deleteRequest = new DeleteRequest("test");
		deleteRequest.id("344843254");

		DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
		if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
			System.out.println("删除失败");
		} else {
			System.out.println("删除成功");
		}

		client.close();
	}
}