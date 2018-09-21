package com.yang.test.java.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestMongodb {

	public static void main(String[] args) {
		List<ServerAddress> l = new ArrayList<ServerAddress>();
		l.add(new ServerAddress("192.168.30.120", 27017));
		l.add(new ServerAddress("192.168.30.121", 27017));
		l.add(new ServerAddress("192.168.10.10", 27017));
				
				
				
				
				
				
		
		
		//MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
		MongoClient mongoClient = new MongoClient(l);

		MongoDatabase mgdb = mongoClient.getDatabase("test");
		
		MongoCollection c = mgdb.getCollection("c8");
		for(int i=0;i<100;i++) {
			
			
			c.count();
			System.out.println("MongoDatabase inof is : ");
		}
		
		mongoClient.close();
	}
}