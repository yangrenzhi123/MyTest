package com.yang.test.java.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class TestMongodb3 {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("192.168.10.19", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");

		MongoCollection c = null;
		String col = null;

		col = "gwRequestInfo";
		c = mgdb.getCollection(col);
		System.out.println(col + "��" + c.count());

		mongoClient.close();
	}
}