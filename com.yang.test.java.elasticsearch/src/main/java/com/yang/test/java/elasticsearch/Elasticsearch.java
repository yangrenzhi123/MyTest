package com.yang.test.java.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

@SuppressWarnings("deprecation")
public class Elasticsearch {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.8.70", 9200, "http")));


		BulkRequest bulkRequest = new BulkRequest();
		
		IndexRequest request = new IndexRequest("posts", "doc", "d");
		String jsonString = "{" + "\"user\":\"kimchya\"," + "\"postDate\":\"2013-01-30\"," + "\"postDate2\":\"2013-01-30\"," + "\"message\":\"trying out Elasticsearch\"" + "}";
		request.source(jsonString, XContentType.JSON);
		//client.index(request, RequestOptions.DEFAULT);

		bulkRequest.add(request);
		
		request = new IndexRequest("posts", "doc", "e");
		jsonString = "{" + "\"user\":\"kimchyb\"," + "\"postDate\":\"2013-01-30\"," + "\"message\":\"trying out Elasticsearch\"" + "}";
		request.source(jsonString, XContentType.JSON);
		//client.index(request, RequestOptions.DEFAULT);

		bulkRequest.add(request);
		
		request = new IndexRequest("posts", "doc", "f");
		jsonString = "{" + "\"user\":\"kimchyc\"," + "\"postDate2\":\"2013-01-30\"," + "\"message\":\"trying out Elasticsearch\"" + "}";
		request.source(jsonString, XContentType.JSON);
		//client.index(request, RequestOptions.DEFAULT);

		bulkRequest.add(request);
		
		client.bulk(bulkRequest, RequestOptions.DEFAULT);
		
        GetRequest r = new GetRequest("posts", "doc", "a");
        GetResponse p = client.get(r, RequestOptions.DEFAULT);
        Map<String, Object> source = p.getSource();
        System.out.println(source);
        r = new GetRequest("posts", "doc", "b");
        p = client.get(r, RequestOptions.DEFAULT);
        source = p.getSource();
        System.out.println(source);
        r = new GetRequest("posts", "doc", "c");
        p = client.get(r, RequestOptions.DEFAULT);
        source = p.getSource();
        System.out.println(source);
        r = new GetRequest("posts", "doc", "d");
        p = client.get(r, RequestOptions.DEFAULT);
        source = p.getSource();
        System.out.println(source);
        r = new GetRequest("posts", "doc", "e");
        p = client.get(r, RequestOptions.DEFAULT);
        source = p.getSource();
        System.out.println(source);
        r = new GetRequest("posts", "doc", "f");
        p = client.get(r, RequestOptions.DEFAULT);
        source = p.getSource();
        System.out.println(source);
		
		client.close();
	}
}