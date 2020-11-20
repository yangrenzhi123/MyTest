package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentFactory;

public class EsUpdate {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));

		UpdateRequest ur = new UpdateRequest("ytest", "-X4b33UBIMNBma3PYcLu");
		ur.doc(XContentFactory.jsonBuilder().startObject().field("description", "two").endObject());

		BulkRequest bulkRequest = new BulkRequest();
		bulkRequest.add(ur);

		client.bulk(bulkRequest, RequestOptions.DEFAULT);
		client.close();
	}
}