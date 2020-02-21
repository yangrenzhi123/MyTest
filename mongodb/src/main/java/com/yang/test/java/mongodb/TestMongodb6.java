package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class TestMongodb6 {

	public static void main(String[] args) throws InterruptedException, IOException {
		MongoClient mongoClient = new MongoClient("192.168.10.229", 27017);
		final MongoDatabase mgdb = mongoClient.getDatabase("test_source");

		MongoCollection<Document> mc = mgdb.getCollection("alarmDataInfo_20181201");

		long totalBytes = 0;
		long totalCount = 0;

		MongoCursor<Document> datas = mc.find().iterator();
		while (datas.hasNext()) {
			String json = datas.next().toJson();

			//System.out.println(json);

			totalBytes = totalBytes + json.getBytes("GBK").length;
			totalCount++;
		}

		mongoClient.close();

		System.out.println("totalBytes：" + totalBytes + "，" + totalBytes / 1024 / 1024 + "MB，数据总条数：" + totalCount);
	}
}