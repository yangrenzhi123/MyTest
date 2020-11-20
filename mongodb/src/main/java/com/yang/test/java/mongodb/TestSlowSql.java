package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestSlowSql {

	public static void main(String[] args) throws InterruptedException, IOException {
//		t("192.168.10.84",27017);
//		t("192.168.10.39",27017);
//		t("192.168.10.227",27018);
//		
//		t("192.168.10.17",28017);
//		t("192.168.10.26",28017);
//		t("192.168.10.39",28019);
//		
		t("192.168.10.19",27017);
//		t("192.168.10.26",27017);
//		t("192.168.10.227",27017);
//		
//		t("192.168.10.84",27018);
//		t("192.168.10.84",27028);
//		//t("192.168.10.84",27038);
//		//t("192.168.10.222",27018);
//		t("192.168.10.222",27028);
//		t("192.168.10.222",27038);
//		t("192.168.10.224",27018);
//		//t("192.168.10.224",27028);
//		t("192.168.10.224",27038);
	}
	
	public static void t(String ip, int port) {
		MongoClient mongoClient = new MongoClient(ip, port);

		final MongoDatabase mgdb = mongoClient.getDatabase("test");
		final MongoCollection<Document> c = mgdb.getCollection("system.profile");
		

		BasicDBObject query = new BasicDBObject();
		query.put("ts", new BasicDBObject("$gt", "2020-01-01"));
		
		Document sort = new Document();
		sort.put("ts", 1);
		
		FindIterable<Document> iter = c.find(/*query*/).sort(sort);//.limit(10);
		MongoCursor<Document> mc = iter.cursor();
		while (mc.hasNext()) {
			Document slowsql = mc.next();
			System.out.println(slowsql);
		}

		mongoClient.close();
	}
}