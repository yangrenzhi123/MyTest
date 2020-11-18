package com.yang.test.java.mongodb;

import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;

public class ListDb {

	public static void main(String[] args) throws InterruptedException, IOException {
		MongoClient mongoClient = new MongoClient("192.168.10.227", 27017);
		MongoIterable<String> iterable = mongoClient.listDatabaseNames();
		MongoCursor<String> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

		mongoClient.close();
	}
}