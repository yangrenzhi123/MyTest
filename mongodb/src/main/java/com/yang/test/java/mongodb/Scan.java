package com.yang.test.java.mongodb;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Scan {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("192.168.255.84", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection mongoCollection = mgdb.getCollection("h_recyle_record");

		long last = 0;

		while(true) {
			int i = 0;
			
			long a = System.currentTimeMillis();
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new BasicDBObject("$gt", last));
			
			FindIterable<Document> fi = mongoCollection.find(query).sort(new Document("_id", 1)).limit(10000);
			MongoCursor<Document> cursor = fi.iterator();
			while(cursor.hasNext()) {
				Document document = cursor.next();
				last = document.getLong("_id");
				
				i++;
			}
			System.out.println("last：" + last + "，耗时：" + (System.currentTimeMillis() - a));
			if(i < 10000) break;
		}
		
		mongoClient.close();
	}
}