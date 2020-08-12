package com.yang.test.java.mongodb;

import java.io.IOException;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("unchecked")
public class TestCurrentOp {

	public static void main(String[] args) throws InterruptedException, IOException {
		MongoClient mongoClient = new MongoClient("192.168.226.213", 27017);

		final MongoDatabase mgdb = mongoClient.getDatabase("admin");
		Document doc = new Document();
		doc.append("currentOp", false);
		Document result = mgdb.runCommand(doc);
		List<Document> infos = (List<Document>) result.get("inprog");
		int i = 0;
		for (Document item : infos) {
			//Object o = item.get("secs_running");
			//long secs_running = 0;
			//if(o != null) {
			//	secs_running = (long)o;
			//}
			//if(secs_running > 7000) {
			//if (item.get("client_s") != null) {
			if (!"local.oplog.rs".equals(item.get("ns"))) {
				System.out.println(item);
				System.out.println("client：\t\t" + item.get("client") + "，");
				System.out.println("client_s：\t" + item.get("client_s") + "，");
				System.out.println("shard：\t\t" + item.get("shard") + "，");
				System.out.println("host：\t\t" + item.get("host") + "，");
				System.out.println("ns：\t\t" + item.get("ns") + "，");
				System.out.println("op：\t\t" + item.get("op") + "，");
				System.out.println("secs_running：\t" + item.get("secs_running") + "，");
				System.out.println("command：\t" + item.get("command") + "，");
				System.out.println("connectionId：\t\t" + item.get("connectionId"));
				System.out.println("----------------------------------------------------------");
				System.out.println();
				i++;
			 }
		}
		System.out.println("总数量：" + infos.size());
		System.out.println("符合条件数量：" + i);
		mongoClient.close();
	}
}