package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestMongodb6 {

	public static void main(String[] args) throws InterruptedException, IOException {
		MongoClient mongoClient = new MongoClient("192.168.10.229", 27017);
		final MongoDatabase mgdb = mongoClient.getDatabase("test_source");

		MongoCollection<Document> mc = mgdb.getCollection("h_score_record_day_20191227");
		MongoCursor<Document> datas = mc.find().iterator();
		while (datas.hasNext()) {
			System.out.println(datas.next().toJson());
		}

		mongoClient.close();
	}
}