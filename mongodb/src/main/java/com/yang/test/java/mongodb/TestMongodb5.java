package com.yang.test.java.mongodb;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class TestMongodb5 {

	public static void main(String[] args) throws InterruptedException, IOException {
//		RandomAccessFile raf = new RandomAccessFile("C:/1.txt", "rw");
//		FileChannel fileChannel = raf.getChannel();
//		ByteBuffer buf = ByteBuffer.allocate(1024);

		MongoClient mongoClient = new MongoClient("192.168.10.238", 27017);
		final MongoDatabase mgdb = mongoClient.getDatabase("recyclerecord");

		MongoIterable<String> mi = mgdb.listCollectionNames();

		MongoCursor<String> mc = mi.iterator();
		int i = 0;
		while (mc.hasNext()) {
			if(i++ > 100) break;
			
			
			String collectionName = mc.next();

			MongoCollection<Document> mongoCollection = mgdb.getCollection(collectionName);

			ListIndexesIterable<Document> lii = mongoCollection.listIndexes();
			MongoCursor<Document> mcnei = lii.iterator();
			StringBuilder sb = new StringBuilder();
			while (mcnei.hasNext()) {
				Document d = mcnei.next();
				sb.append(d.get("name") + "，");
			}
//			String content = collectionName + "：" + sb.toString() + "\r\n";
//			buf.clear();
//			buf.put(content.getBytes());
//			buf.flip();
//			while (buf.hasRemaining()) {
//				fileChannel.write(buf);
//			}
		}

		mongoClient.close();
//		raf.close();
	}
}