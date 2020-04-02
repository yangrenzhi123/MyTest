package com.yang.test.java.mongodb;

import java.io.IOException;

import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

@SuppressWarnings("deprecation")
public class TestExec2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		long totalSize = 0;
		String dbName = "recyclerecord";
		
		
		MongoClient mongoClient = new MongoClient("192.168.10.238", 27017);
		DB db = mongoClient.getDB(dbName);

		final MongoDatabase mgdb = mongoClient.getDatabase(dbName);

		MongoIterable<String> mi = mgdb.listCollectionNames();

		MongoCursor<String> mc = mi.iterator();
		while (mc.hasNext()) {
			String collectionName = mc.next();
			
			CommandResult resultSet = db.getCollection(collectionName).getStats();
			Object size = resultSet.get("size");
			Object count = resultSet.get("count");
			Object avgObjSize = resultSet.get("avgObjSize");
			if(size instanceof Double) {
				long v = ((Double)size).longValue();
				totalSize = totalSize + v;
				System.out.println(collectionName + "，" + v + "，数据量：" + count + "行，单条数据容量：" + avgObjSize + "字节");
			}else {
				totalSize = totalSize + (int)size;
				System.out.println(collectionName + "，" + (int)size + "，数据量：" + count + "行，单条数据容量：" + avgObjSize + "字节");
			}
		}

		mongoClient.close();

		System.out.println("总内存量：" + (totalSize/1024/1024/1024) + "GB");
	}
}