package com.yang.test.java.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

@SuppressWarnings("deprecation")
public class Elasticsearch2 {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));

//		GetRequest r = new GetRequest("tsaas-zs-logs", "docl", "GEgjwXEBiMDkxNeh8-XU");
//		GetResponse p = client.get(r, RequestOptions.DEFAULT);
//		Map<String, Object> source = p.getSource();
//		System.out.println(source);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//		sourceBuilder.query(QueryBuilders.matchQuery("message", "2020").fuzziness(Fuzziness.AUTO));
//		sourceBuilder.query(QueryBuilders.matchQuery("@timestamp", "2020-05-21T10:19:43.197Z").fuzziness(Fuzziness.AUTO));
//		sourceBuilder.query(QueryBuilders.rangeQuery("@timestamp").gt("2020-11-14T00:00:00.000Z").lt("2020-11-15T00:00:00.000Z"));
		sourceBuilder.from(0);
		sourceBuilder.size(10000);
//		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		SearchRequest searchRequest = new SearchRequest("testy-2020.11.07");
		//searchRequest.types("Doc");
		searchRequest.source(sourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		System.out.println(hits.getTotalHits());
		SearchHit[] searchHits = hits.getHits();
		for (SearchHit hit : searchHits) {
		    System.out.print(hit.getId() + "，");
		    System.out.print(hit.getIndex() + "，");
		    System.out.print(hit.getSourceAsMap().get("@timestamp") + "，");
		    System.out.print(hit.getSourceAsMap().get("s0") + "，");
		    System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
}