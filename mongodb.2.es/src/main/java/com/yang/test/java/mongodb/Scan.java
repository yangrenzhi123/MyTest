package com.yang.test.java.mongodb;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpHost;
import org.bson.BsonTimestamp;
import org.bson.Document;
import org.bson.json.Converter;
import org.bson.json.JsonWriterSettings;
import org.bson.json.StrictJsonWriter;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Scan {
	
	public static void main(String[] args) throws IOException {
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.255.90", 9200, "http")));
		
		MongoClient mongoClient = new MongoClient("192.168.255.84", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection mongoCollection = mgdb.getCollection("h_recyle_record");

		long last = 200000000;

		while(true) {
			int i = 0;
			
			long a = System.currentTimeMillis();

			BulkRequest bulkRequest = new BulkRequest();
			
			
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new BasicDBObject("$gt", last));
			
			FindIterable<Document> fi = mongoCollection.find(query).sort(new Document("_id", 1)).limit(10000);
			MongoCursor<Document> cursor = fi.iterator();
			while(cursor.hasNext()) {
				
				Document document = cursor.next();
				last = document.getLong("_id");

				IndexRequest request = new IndexRequest("test");
				request.id(last + "");

				JsonWriterSettings settings = JsonWriterSettings.builder().int64Converter(new Converter<Long>() {
					public void convert(Long value, StrictJsonWriter writer) {
						writer.writeNumber("\"" + value.toString() + "\"");
					}
				}).dateTimeConverter(new Converter<Long>() {
					public void convert(Long value, StrictJsonWriter writer) {
						writer.writeNumber("\"" + df.format(new Date(value)) + "\"");
					}
				}).build();

				request.source(document.toJson(settings).replaceAll("-", "").replace("{\"_id\":", "{\"id\":"), XContentType.JSON);
				bulkRequest.add(request);
				
				i++;
			}
			

			esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
			System.out.println("last：" + last + "，耗时：" + (System.currentTimeMillis() - a));
			
			
			if(i < 10000) break;
		}
		
		esClient.close();
		mongoClient.close();
	}
}