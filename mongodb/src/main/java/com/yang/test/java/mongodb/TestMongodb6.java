package com.yang.test.java.mongodb;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class TestMongodb6 {

	public static void main(String[] args) throws InterruptedException, IOException {
		MongoClient mongoClient = new MongoClient("192.168.10.238", 27017);
		final MongoDatabase mongoDatabase = mongoClient.getDatabase("recyclerecord");
		MongoIterable<String> collectionNames = mongoDatabase.listCollectionNames();

		MongoCursor<String> cursor = collectionNames.iterator();
		while (cursor.hasNext()) {
			String collectionName = cursor.next();
			if(collectionName.equals("h_recycle_record")) {
				continue;
			}
			
			MongoCollection<Document> mc = mongoDatabase.getCollection(collectionName);
			
//			System.out.println(collectionName + "，数据量：" + mc.countDocuments());

			long totalBytes = 0;
			long totalCount = 0;
			MongoCursor<Document> datas = mc.find().iterator();
			while (datas.hasNext()) {
				String json = datas.next().toJson();

				totalBytes = totalBytes + json.getBytes("UTF-8").length;
				totalCount++;
			}
			System.out.println(collectionName + "，totalBytes：" + totalBytes + "，" + totalBytes / 1024 / 1024 + "MB，数据总条数：" + totalCount);
		}
		mongoClient.close();
	}
}