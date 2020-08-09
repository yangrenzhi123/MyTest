package com.yang.test.java.mongodb;

import java.io.IOException;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("unchecked")
public class TestCurrentOp {

	public static void main(String[] args) throws InterruptedException, IOException {
//		RandomAccessFile raf = new RandomAccessFile("C:/1.txt", "rw");
//		FileChannel fileChannel = raf.getChannel();
//		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		MongoClient mongoClient = new MongoClient("192.168.10.222", 27017);

		final MongoDatabase mgdb = mongoClient.getDatabase("admin");
		Document doc = new Document();
		doc.append("currentOp", new BasicDBObject("$all", true));//.append("secs_running", new BasicDBObject("$gte", 0));
		Document result = mgdb.runCommand(doc);
		List<Document> infos = (List<Document>) result.get("inprog");
		int i = 0;
		for (Document item : infos) {
			Object o = item.get("secs_running");
			long secs_running = 0;
			if(o != null) {
				secs_running = (long)o;
			}
			
			if (item.get("client_s") != null) {
			//if(secs_running > 7000) {
				System.out.println(item);
				System.out.println("client：\t\t" + item.get("client_s") + "，");
				System.out.println("shard：\t\t" + item.get("shard") + "，");
				System.out.println("host：\t\t" + item.get("host") + "，");
				System.out.println("ns：\t\t" + item.get("ns") + "，");
				System.out.println("op：\t\t" + item.get("op") + "，");
				System.out.println("secs_running：\t" + item.get("secs_running") + "，");
				System.out.println("command：\t" + item.get("command") + "，");
				System.out.println("locks：\t\t" + item.get("locks"));
				System.out.println("----------------------------------------------------------");
				i++;
			 }
		}
		System.out.println("总数量：" + infos.size());
		System.out.println("符合条件数量：" + i);

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