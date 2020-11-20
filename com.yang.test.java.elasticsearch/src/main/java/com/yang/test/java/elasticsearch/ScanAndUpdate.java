package com.yang.test.java.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ScanAndUpdate {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.from(0);
		sourceBuilder.size(10000);

		List<String> indices = new ArrayList<>();
//		indices.add("slowquery-mysql-2020-10-24");
//		indices.add("slowquery-mysql-2020-10-25");
//		indices.add("slowquery-mysql-2020-10-26");
//		indices.add("slowquery-mysql-2020-10-27");
//		indices.add("slowquery-mysql-2020-10-28");
//		indices.add("slowquery-mysql-2020-10-29");
//		indices.add("slowquery-mysql-2020-10-30");
//		indices.add("slowquery-mysql-2020-10-31");
//		indices.add("slowquery-mysql-2020-11-01");
//		indices.add("slowquery-mysql-2020-11-02");
//		indices.add("slowquery-mysql-2020-11-03");
//		indices.add("slowquery-mysql-2020-11-04");
//		indices.add("slowquery-mysql-2020-11-05");
//		indices.add("slowquery-mysql-2020-11-06");
//		indices.add("slowquery-mysql-2020-11-07");
//		indices.add("slowquery-mysql-2020-11-08");
//		indices.add("slowquery-mysql-2020-11-09");
//		indices.add("slowquery-mysql-2020-11-10");
//		indices.add("slowquery-mysql-2020-11-11");
//		indices.add("slowquery-mysql-2020-11-12");
//		indices.add("slowquery-mysql-2020-11-13");
//		indices.add("slowquery-mysql-2020-11-14");
//		indices.add("slowquery-mysql-2020-11-15");
//		indices.add("slowquery-mysql-2020-11-16");
//		indices.add("slowquery-mysql-2020-11-17");
//		indices.add("slowquery-mysql-2020-11-18");
		indices.add("slowquery-mysql-2020-11-19");
		
		for(String indexName : indices) {
			SearchRequest searchRequest = new SearchRequest(indexName);
			searchRequest.source(sourceBuilder);
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits = searchResponse.getHits();
			System.out.println(hits.getTotalHits());
			SearchHit[] searchHits = hits.getHits();
			BulkRequest bulkRequest = new BulkRequest();
			for (SearchHit hit : searchHits) {

				if(hit.getSourceAsMap().get("s0").equals(new Integer(1))) {
					UpdateRequest ur = new UpdateRequest(hit.getIndex(), hit.getId());
					if(hit.getSourceAsMap().get("query_time").toString().startsWith("00:00:0")) {
						ur.doc(XContentFactory.jsonBuilder().startObject().field("s00", 1).endObject());
					}else if(hit.getSourceAsMap().get("query_time").toString().startsWith("00:00:1")) {
						ur.doc(XContentFactory.jsonBuilder().startObject().field("s01", 1).endObject());
					}else if(hit.getSourceAsMap().get("query_time").toString().startsWith("00:00:2")) {
						ur.doc(XContentFactory.jsonBuilder().startObject().field("s02", 1).endObject());
					}else if(hit.getSourceAsMap().get("query_time").toString().startsWith("00:00:3")) {
						ur.doc(XContentFactory.jsonBuilder().startObject().field("s03", 1).endObject());
					}else if(hit.getSourceAsMap().get("query_time").toString().startsWith("00:00:4")) {
						ur.doc(XContentFactory.jsonBuilder().startObject().field("s04", 1).endObject());
					}else if(hit.getSourceAsMap().get("query_time").toString().startsWith("00:00:5")) {
						ur.doc(XContentFactory.jsonBuilder().startObject().field("s05", 1).endObject());
					}else {
						ur = null;
					}
					if(ur != null) {
						bulkRequest.add(ur);
					}
				}
			}
			
			client.bulk(bulkRequest, RequestOptions.DEFAULT);
		}
		client.close();
	}
}