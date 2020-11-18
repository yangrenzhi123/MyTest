package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class ListCollectionIndexes {

	public static void main(String[] args) throws InterruptedException, IOException {
		MongoClient mongoClient = new MongoClient("192.168.10.227", 27017);
		final MongoDatabase mgdb = mongoClient.getDatabase("test");

		MongoCollection<Document> mongoCollection = mgdb.getCollection("system.profile");

		ListIndexesIterable<Document> lii = mongoCollection.listIndexes();
		MongoCursor<Document> mcnei = lii.iterator();
		while (mcnei.hasNext()) {
			System.out.println(mcnei.next());
		}

		mongoClient.close();
	}
}