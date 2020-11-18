package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class TestExec {

	public static void main(String[] args) throws InterruptedException, IOException {
		MongoClient mongoClient = new MongoClient("192.168.10.227", 27017);

		final MongoDatabase mgdb = mongoClient.getDatabase("test");
		Document doc = new Document();
		doc.append("setProfilingLevel", 1);
		Document result = mgdb.runCommand(doc);
		System.out.println(result);
		mongoClient.close();
	}
}