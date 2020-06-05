package com.yang.test.java.mongodb;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("unchecked")
public class TestCurrentOp {

	public static void main(String[] args) throws InterruptedException, IOException {
//		RandomAccessFile raf = new RandomAccessFile("C:/1.txt", "rw");
//		FileChannel fileChannel = raf.getChannel();
//		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		MongoClient mongoClient = new MongoClient("192.168.10.237", 27411);

		final MongoDatabase mgdb = mongoClient.getDatabase("admin");
		Document doc = new Document();
		doc.append("currentOp", false);
		Document result = mgdb.runCommand(doc);
		List<Document> infos = (List<Document>) result.get("inprog");
		for (Document item : infos) {
			if (item.get("ns").toString().startsWith("test.")) {
				System.out.println(item.get("client") + "，");
				System.out.println(item.get("shard") + "，");
				System.out.println(item.get("host") + "，");
				System.out.println(item.get("ns") + "，");
				System.out.println(item.get("op") + "，");
				System.out.println(item.get("secs_running") + "，");
				System.out.println(item.get("command") + "，");
				System.out.println(item.get("locks"));
				System.out.println("----------------------------------------------------------");
			 }
		}

//		final MongoDatabase mgdb = mongoClient.getDatabase("test_tenantgroup");
//		MongoCollection<Document> mc = mgdb.getCollection("H_TENANT_GROUP_2503fce6-b923-42ab-9e2a-1d399fae712d_8a1ceed8-4d2f-4be2-b42d-c4589ddd9c87");

//		long a = System.currentTimeMillis();
//		ListIndexesIterable<Document> lii = mc.listIndexes();
//		MongoCursor<Document> mcnei = lii.iterator();
//		while (mcnei.hasNext()) {
//			Document d = mcnei.next();
//			System.out.println(d);
//		}
//		System.out.println("耗时：" + (System.currentTimeMillis() - a));
		
//		long b = System.currentTimeMillis();
//		MongoCursor<Document> datas = mc.find().iterator();
//		while (datas.hasNext()) {
//			String json = datas.next().toJson() + "\r\n";
//			buf.clear();
//			buf.put(json.getBytes());
//			buf.flip();
//			while (buf.hasRemaining()) {
//				fileChannel.write(buf);
//			}
//		}
//		System.out.println("耗时：" + (System.currentTimeMillis() - b));
		
		mongoClient.close();
//		raf.close();
	}
}