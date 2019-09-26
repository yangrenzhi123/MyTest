package com.yang.test.java.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class TestMongodb2 {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("192.168.10.239", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");

		MongoCollection c = null;
		String col = null;

		col = "deviceOriginalData";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "alarmDataInfo";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "equipHeartBeatData";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "flEquipFaceMongo";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "gwRequestInfo";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "lyznErrorLog";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "projectScoreRecordDTO";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "recovery_analysis";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "staticPage";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		col = "tenantUserMongo";
		c = mgdb.getCollection(col);
		System.out.println(col + "£º" + c.count());

		mongoClient.close();
	}
}