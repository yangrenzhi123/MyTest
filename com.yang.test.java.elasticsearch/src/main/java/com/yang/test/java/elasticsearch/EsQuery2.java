package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

@SuppressWarnings("deprecation")
public class EsQuery2 {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.26.199", 9200, "http")));

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.from(0);
		sourceBuilder.size(100);
		//sourceBuilder.query(QueryBuilders.matchQuery("id", "5"));
		sourceBuilder.sort("id");
		
		SearchRequest searchRequest = new SearchRequest("test");
		searchRequest.types("h_recycle_record");
		searchRequest.source(sourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		SearchHits hits = searchResponse.getHits();
		System.out.println(hits.getTotalHits());
		SearchHit[] searchHits = hits.getHits();
		for (SearchHit hit : searchHits) {
		    System.out.print(hit.getIndex() + "，");
		    System.out.print(hit.getType() + "，");
		    System.out.print(hit.getId() + "，");
		    System.out.println(hit.getSourceAsString());
		}

		client.close();
	}
}