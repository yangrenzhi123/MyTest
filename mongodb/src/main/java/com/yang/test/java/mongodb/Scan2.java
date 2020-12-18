package com.yang.test.java.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Scan2 {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("192.168.10.227", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection mongoCollection = mgdb.getCollection("gwRequestInfo");

		FindIterable<Document> fi = mongoCollection.find();
		MongoCursor<Document> cursor = fi.iterator();
		while (cursor.hasNext()) {
			cursor.next();
		}

		mongoClient.close();
	}
}