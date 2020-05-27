package com.yang.test.java.mongodb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestInsert {

	public static void main(String[] args) throws ParseException, InterruptedException {
		String ip = "172.18.71.174";
		int port = 40000;

//		MongoClient mc = new MongoClient(ip, port);

		MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("test", "test", "test".toCharArray());

		MongoClientOptions options = MongoClientOptions.builder()
			.connectTimeout(1000 * 10)// 设置连接超时时间为10s
			.maxWaitTime(1000 * 10).build();// 设置最长等待时间为10s
		MongoClient mc = new MongoClient(new ServerAddress(ip, port), mongoCredential, options);

		MongoDatabase mgdb = mc.getDatabase("test");
		MongoCollection c1 = mgdb.getCollection("c8");

		for(int m=0;m<10;m++) {
			List<Document> l = new ArrayList<>();
			for (int i = 0; i < 6000; i++) {
				Document insert = new Document();
				insert.put("mykey", 0);
				insert.put("value", 100);
				l.add(insert);
			}
			c1.insertMany(l);
			Thread.sleep(1000);
		}

		mc.close();
	}
}