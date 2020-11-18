package com.yang.test.java.elasticsearch;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpHost;
import org.bson.Document;
import org.bson.json.Converter;
import org.bson.json.JsonWriterSettings;
import org.bson.json.StrictJsonWriter;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoSlowsqlToEs {

	public static void main(String[] args) throws SQLException, IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar yestoday = Calendar.getInstance();
		yestoday.add(Calendar.DAY_OF_YEAR, -1);
		Calendar today = Calendar.getInstance();
		String strYestoday = df.format(yestoday.getTime());
		String strToday = df.format(today.getTime());
//		String strYestoday = "2020-11-18";
//		String strToday = "2020-11-19";
		
		JsonWriterSettings settings = JsonWriterSettings.builder().int64Converter(new Converter<Long>() {
			public void convert(Long value, StrictJsonWriter writer) {
				writer.writeNumber(value.toString());
			}
		}).build();
		
		RestHighLevelClient client = null;
		try {
			client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.10.92", 9200, "http")));
			boolean existing = client.indices().exists(new GetIndexRequest("slowquery-mongo-"+strYestoday), RequestOptions.DEFAULT);
			if (existing) {
				client.indices().delete(new DeleteIndexRequest("slowquery-mongo-"+strYestoday), RequestOptions.DEFAULT);
			}
			
			List<MongoClient> mongoList = new ArrayList<>();
			mongoList.add(new MongoClient("192.168.10.227", 27017));
			mongoList.add(new MongoClient("192.168.10.26", 27017));
			mongoList.add(new MongoClient("192.168.10.19", 27017));
			
			mongoList.add(new MongoClient("192.168.10.84",27017));
			mongoList.add(new MongoClient("192.168.10.39",27017));
			mongoList.add(new MongoClient("192.168.10.227",27018));
			
			mongoList.add(new MongoClient("192.168.10.17",28017));
			mongoList.add(new MongoClient("192.168.10.26",28017));
			mongoList.add(new MongoClient("192.168.10.39",28019));

			mongoList.add(new MongoClient("192.168.10.84",27018));
			mongoList.add(new MongoClient("192.168.10.84",27028));
			mongoList.add(new MongoClient("192.168.10.222",27028));
			mongoList.add(new MongoClient("192.168.10.222",27038));
			mongoList.add(new MongoClient("192.168.10.224",27018));
			mongoList.add(new MongoClient("192.168.10.224",27038));
			
			
			for(MongoClient mc : mongoList) {
				try {
					t(mc, client, settings, strYestoday, strToday);
				} finally {
					mc.close();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		if(client != null) client.close();
	}
	
	public static void t(MongoClient mc, RestHighLevelClient client, JsonWriterSettings settings, String strYestoday, String strToday) throws ClassNotFoundException, SQLException, IOException, ParseException {
		MongoIterable<String> iterable = mc.listDatabaseNames();
		MongoCursor<String> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			String db = cursor.next();
			if(!db.equals("admin") && !db.equals("config") && !db.equals("local")) {
				List<String> l = getSlowsqlFromMongo(mc, db, strYestoday, strToday, settings, client);
				if(l.size() > 0) {
					BulkRequest bulkRequest = new BulkRequest();
					for(String json : l) {
						IndexRequest request = new IndexRequest("slowquery-mongo-"+strYestoday);
						request.source(json, XContentType.JSON);
						bulkRequest.add(request);
					}
					client.bulk(bulkRequest, RequestOptions.DEFAULT);
				}
			}
		}
	}

	public static List<String> getSlowsqlFromMongo(MongoClient mc, String db, String strYestoday, String strToday, JsonWriterSettings settings, RestHighLevelClient client) throws ParseException, IOException {
		List<String> result = new ArrayList<>();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		MongoDatabase mgdb = mc.getDatabase(db);
		MongoCollection<Document> c = mgdb.getCollection("system.profile");

		BasicDBObject query = new BasicDBObject();
		query.put("ts", new BasicDBObject("$gte", df.parse(strYestoday)).append("$lt", df.parse(strToday)));

		FindIterable<Document> findIterable = c.find(query);
		MongoCursor<Document> cursor = findIterable.cursor();
		while (cursor.hasNext()) {
			Document d = cursor.next();
			Calendar time = Calendar.getInstance();
			time.setTime((Date)d.get("ts"));
			time.add(Calendar.HOUR_OF_DAY, -8);
			
			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"@timestamp\":\"");json.append(df2.format(time.getTime()));json.append("\"");
			json.append(",\"client\":\"");json.append(d.get("client"));json.append("\"");
			json.append(",\"ns\":\"");json.append(d.get("ns"));json.append("\"");
			json.append(",\"json\":\"");json.append(d.toJson(settings).replaceAll("\\\\\"", "'").replaceAll("\"", "\\\\\""));json.append("\"");
			json.append("}");
			result.add(json.toString());
			

			// 调试用的代码
			/*try {
				BulkRequest bulkRequest = new BulkRequest();
				IndexRequest request = new IndexRequest("slowquery-mongo-"+strYestoday);
				request.source(json.toString(), XContentType.JSON);
				bulkRequest.add(request);
				client.bulk(bulkRequest, RequestOptions.DEFAULT);
			}catch(Exception e) {
				System.out.println();
			}*/
		}
		return result;
	}
}