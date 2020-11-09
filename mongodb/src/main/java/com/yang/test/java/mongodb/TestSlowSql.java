package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestSlowSql {

	public static void main(String[] args) throws InterruptedException, IOException {
		t("192.168.10.84",27017);
		t("192.168.10.39",27017);
		t("192.168.10.227",27018);
		
		t("192.168.10.17",28017);
		t("192.168.10.26",28017);
		t("192.168.10.39",28019);
		
		t("192.168.10.19",27017);
		t("192.168.10.26",27017);
		t("192.168.10.227",27017);
		
		t("192.168.10.84",27018);
		t("192.168.10.84",27028);
		//t("192.168.10.84",27038);
		//t("192.168.10.222",27018);
		t("192.168.10.222",27028);
		t("192.168.10.222",27038);
		t("192.168.10.224",27018);
//		t("192.168.10.224",27028);
		t("192.168.10.224",27038);
	}
	
	public static void t(String ip, int port) {
		MongoClient mongoClient = new MongoClient(ip, port);

		final MongoDatabase mgdb = mongoClient.getDatabase("system");
		final MongoCollection<Document> c = mgdb.getCollection("profile");
		FindIterable<Document> iter = c.find().limit(10);
		MongoCursor<Document> mc = iter.cursor();
		while (mc.hasNext()) {
			Document slowsql = mc.next();
			System.out.println(slowsql);
		}

		mongoClient.close();
	}
}