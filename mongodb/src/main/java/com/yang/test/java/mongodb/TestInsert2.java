package com.yang.test.java.mongodb;

import java.text.ParseException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestInsert2 {

	public static void main(String[] args) throws ParseException {
		MongoClient mongoClient = new MongoClient("192.168.8.70", 27017);

		MongoDatabase mgdb = mongoClient.getDatabase("test");
		MongoCollection c1 = mgdb.getCollection("t");
		MongoCollection c2 = mgdb.getCollection("t");

		doInsert(mongoClient, c1, c2);
	}

	public static void doInsert(MongoClient mongoClient, MongoCollection c1, MongoCollection c2) {
		Document insert = new Document();
		insert.put("name", "kobe");
		c1.insertOne(insert);

		insert = new Document();
		insert.put("name", "kobe");
		c2.insertOne(insert);
	}
}