package com.yang.test.java.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class TestMongodb {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		
		System.out.println("MongoDatabase inof is : "+mgdb.getName());
		
		mongoClient.close();
	}
}