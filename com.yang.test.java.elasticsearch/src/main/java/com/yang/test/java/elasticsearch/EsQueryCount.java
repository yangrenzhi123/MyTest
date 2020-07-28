package com.yang.test.java.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

@SuppressWarnings("deprecation")
public class EsQueryCount {

	public static void main(String[] args) throws IOException {
		RestClientBuilder rcb = RestClient.builder(new HttpHost("192.168.26.199", 9200, "http"));
		
		final RestHighLevelClient client = new RestHighLevelClient(rcb);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(QueryBuilders.rangeQuery("id").lt(20000));

		CountRequest searchRequest = new CountRequest("test");
		//searchRequest.types("h_recycle_record");
		searchRequest.source(sourceBuilder);

		client.countAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<CountResponse>() {
			public void onResponse(CountResponse response) {
				long count = response.getCount();
				System.out.println(count);

				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void onFailure(Exception e) {
				e.printStackTrace();
			}
		});
	}
}