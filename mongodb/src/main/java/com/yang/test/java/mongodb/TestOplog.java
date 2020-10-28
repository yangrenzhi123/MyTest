package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestOplog {

	public static void main(String[] args) throws InterruptedException, IOException {
		int i =0;
		int j =0;
		
		MongoClient mongoClient = new MongoClient("192.168.10.26", 28017);

		final MongoDatabase mgdb = mongoClient.getDatabase("local");
		
		MongoCollection<Document> c = mgdb.getCollection("oplog.rs");
		FindIterable<Document> iter = c.find().limit(100000);
		MongoCursor<Document> mc = iter.cursor();
		while(mc.hasNext()) {
			Document log = mc.next();
			Object o = log.get("o");
			Object o2 = log.get("o2");
			
			if((o != null && o.toString().contains("H_TENANT_GROUP_97a5bf3c-d78c-4d72-bbcd-84acfc9d81f0_d2f608d6-21ba-470b-8bb5-12499235f6b9")) 
					|| 
				(o2 != null && o2.toString().contains("H_TENANT_GROUP_97a5bf3c-d78c-4d72-bbcd-84acfc9d81f0_d2f608d6-21ba-470b-8bb5-12499235f6b9"))) {
				System.out.println(log);
				System.out.println(o);
				System.out.println(o2);
				System.out.println("-----------------------------------------------");
				
				i++;
			}
			j++;
		}
		System.out.println("总数量："+j+"，符合条件数量：" + i);

		mongoClient.close();
	}
}