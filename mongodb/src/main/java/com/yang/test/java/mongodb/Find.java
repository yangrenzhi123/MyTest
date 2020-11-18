package com.yang.test.java.mongodb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Encoder;
import org.bson.codecs.EncoderContext;
import org.bson.json.Converter;
import org.bson.json.JsonWriterSettings;
import org.bson.json.StrictJsonWriter;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Find {

	public static void main(String[] args) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		MongoClient mongoClient = new MongoClient("192.168.10.227", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection<Document> c = mgdb.getCollection("system.profile");

		BasicDBObject query = new BasicDBObject();
		query.put("ts", new BasicDBObject("$gte", df.parse("2020-11-18")).append("$lt", df.parse("2020-11-19")));

		FindIterable<Document> findIterable = c.find(query).limit(10);
		MongoCursor<Document> cursor = findIterable.cursor();
		while (cursor.hasNext()) {
			Document d = cursor.next();
			d.put("@timestamp", ((Date)d.get("ts")).getTime());

			System.out.println(d.get("client"));
			System.out.println(d.get("op"));
			System.out.println(d.get("ns"));
			
			// 修正转换格式
			JsonWriterSettings settings = JsonWriterSettings.builder().int64Converter(new Converter<Long>() {
				public void convert(Long value, StrictJsonWriter writer) {
					writer.writeNumber(value.toString());
				}
			}).build();
			System.out.println(d.toJson(settings));
		}
		mongoClient.close();
	}
}