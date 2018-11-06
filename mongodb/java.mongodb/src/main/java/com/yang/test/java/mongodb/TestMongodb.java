package com.yang.test.java.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({"rawtypes", "unchecked"})
public class TestMongodb {

	public static void main(String[] args) {
		//List<ServerAddress> l = new ArrayList<ServerAddress>();
		
//		 l.add(new ServerAddress("192.168.30.120", 27017));
//		 l.add(new ServerAddress("192.168.30.121", 27017));
//		 l.add(new ServerAddress("192.168.10.10", 27017));
		 

		//l.add(new ServerAddress("192.168.30.60", 27017));
		//l.add(new ServerAddress("192.168.30.61", 27017));

		MongoClient mongoClient = new MongoClient("192.168.10.239", 27017);
		// MongoClient mongoClient = new MongoClient(l);

		MongoDatabase mgdb = mongoClient.getDatabase("test");

		Document doc = Document.parse("{\"myKey\":\"123\"}");
		MongoCollection c = mgdb.getCollection("c8");
		c.insertOne(doc);
		
		
		FindIterable<Document> iter = c.find();
		iter.forEach(new Block<Document>() {
			public void apply(Document _doc) {
				System.out.println(_doc.toJson());
			}
		});

		mongoClient.close();
	}
}