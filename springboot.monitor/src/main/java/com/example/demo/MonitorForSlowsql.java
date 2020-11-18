package com.example.demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorForSlowsql {

	final static Logger logger = LoggerFactory.getLogger(MonitorForSlowsql.class);

	@Autowired
	Config config;
	@Autowired
	GaugeBean gauge;

	public void execute() throws IOException {
		String slowsqlCollection = config.getSlowsqlCollections();
		if (slowsqlCollection != null) {
			String[] ss = slowsqlCollection.split(":");
			String ip = ss[0];
			int port = Integer.parseInt(ss[1]);
			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(ip, port, "http")));
			DateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
			RandomAccessFile raf = new RandomAccessFile("./slowsql-cursor"+yyyyMMdd.format(new Date()), "rw");
			try {
				t(client, raf, config.getSlowsqlCollectionsDingToken());
			} catch (Exception e) {
				logger.error("", e);
			} finally {
				if(client != null) client.close();
				if(raf != null) raf.close();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
		RandomAccessFile raf = new RandomAccessFile("./slowsql-cursor"+yyyyMMdd.format(new Date()), "rw");
		try {
			MonitorForSlowsql mfs = new MonitorForSlowsql();
			mfs.t(client, raf, "");
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if(client != null) client.close();
			if(raf != null) raf.close();
		}
	}
	
	public void t(RestHighLevelClient client, RandomAccessFile raf, String token) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(100);
		FileChannel fc = raf.getChannel();

		String last = null;
		int bytesRead;
		do {
			buf.clear();
			bytesRead = fc.read(buf);
			buf.flip();
			byte[] content = new byte[buf.limit()];
			if(content.length > 0) {
				buf.get(content);
				last = new String(content);
			}
		} while (bytesRead != -1);
		
		Calendar yestoday = Calendar.getInstance();
		yestoday.add(Calendar.DAY_OF_YEAR, -1);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String start = df.format(new Date());
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.from(0);
		sourceBuilder.size(10000);
		sourceBuilder.sort("@timestamp");
		if(last == null) {
			sourceBuilder.query(QueryBuilders.rangeQuery("@timestamp").gte(df.format(yestoday.getTime())+"T16:00:00").lt(start + "T16:00:00"));
		}else {
			sourceBuilder.query(QueryBuilders.rangeQuery("@timestamp").gt(last).lt(start + "T16:00:00"));
		}
		SearchRequest searchRequest = new SearchRequest("slowquery-mysql-"+start);
		searchRequest.source(sourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		System.out.println(hits.getTotalHits());
		SearchHit[] searchHits = hits.getHits();
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		for (SearchHit hit : searchHits) {
		    System.out.print(hit.getId() + "，");
		    System.out.print(hit.getIndex() + "，");
		    System.out.print(hit.getSourceAsMap().get("@timestamp") + "，");
		    System.out.print(hit.getSourceAsMap().get("query_time") + "，");
		    System.out.print(hit.getSourceAsMap().get("user_host") + "，");
		    System.out.println(hit.getSourceAsString());
		    
		    String user_host = hit.getSourceAsMap().get("user_host").toString();
		    if(user_host.contains("192.168.10.")) {
			    String query_time = hit.getSourceAsMap().get("query_time").toString();
			    if(query_time.startsWith("00:01:")) {
			    	a++;
			    }else if(query_time.startsWith("00:02:")) {
			    	b++;
			    }else if(query_time.startsWith("00:03:")) {
			    	c++;
			    }else if(query_time.startsWith("00:04:")) {
			    	d++;
			    }else if(query_time.startsWith("00:05:")) {
			    	e++;
			    }else if(query_time.startsWith("00:00:")) {
			    	// 不计量
			    	//e++;
			    }else {
			    	e++;
			    }
		    }
		    last = hit.getSourceAsMap().get("@timestamp").toString();
		}
		if(last != null) {
			raf.seek(0);
			buf.clear();
			buf.put(last.getBytes());
			buf.flip();
			while (buf.hasRemaining()) {
				fc.write(buf);
			}
		}
		
		MoniResult result = new MoniResult();
		result.setName("Slowsql");
		result.setCheckTime(new Date());
		int aa = a + b + c + d + e;
		if(aa == 0) {
			result.setResult(1);
		}else {
			gauge.handleMessage(aa);
			result.setResult(0);
			DateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			TestDingding.test(config.getSlowsqlCollectionsDingToken(), yyyy.format(result.getCheckTime()) + "，检测到新增1分钟以上慢sql（"+aa+"）条，主从告警");
		}
	}
}