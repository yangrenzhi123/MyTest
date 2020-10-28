package com.yang.test.java.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import entity.RecyleRecord;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestMongodb3 {

	static final MongoClient mongoClient = new MongoClient("192.168.255.84", 27017);
	static final MongoDatabase mgdb = mongoClient.getDatabase("test");
	static final MongoCollection mongoCollection = mgdb.getCollection("h_recyle_record");

	public static long last() {
		long last = 0;
		FindIterable<Document> i = mongoCollection.find().sort(new Document("_id", -1)).limit(1);
		MongoCursor<Document> mcnei = i.iterator();
		while (mcnei.hasNext()) {
			Document d = mcnei.next();
			last = d.getLong("_id");
		}
		return last;
	}
	
	public static void insertOrUpdate(List<RecyleRecord> l) {
		List<Document> documents = new ArrayList<>();
		for (RecyleRecord item : l) {
			Document upIst = new Document();
			upIst.put("_id", item.getRecyclerecordzzid());
			Document data = new Document();
			data.put("_id", item.getRecyclerecordzzid());
			data.put("recyclerecordid", item.getRecyclerecordid());
			data.put("tenantid", item.getTenantid());
			data.put("productid", item.getProductid());
			data.put("orderid", item.getOrderid());
			data.put("regionid", item.getRegionid());
			data.put("communityid", item.getCommunityid());
			data.put("yzqyid", item.getYzqyid());
			data.put("yxxqid", item.getYxxqid());
			data.put("cjlx", item.getCjlx());
			data.put("sbbh", item.getSbbh());
			data.put("equipmentid", item.getEquipmentid());
			data.put("tfbz", item.getTfbz());
			data.put("yhlx", item.getYhlx());
			data.put("tenantgroupid", item.getTenantgroupid());
			data.put("operateuserid", item.getOperateuserid());
			data.put("ljlx", item.getLjlx());
			data.put("hsms", item.getHsms());
			data.put("tdqzl", item.getTdqzl());
			data.put("tdhzl", item.getTdhzl());
			data.put("tdzl", item.getTdzl());
			data.put("yszl", item.getYszl());
			data.put("tddj", item.getTddj());
			data.put("hdjf", item.getHdjf());
			data.put("sdjf", item.getSdjf());
			data.put("tffs", item.getTffs());
			data.put("jlly", item.getJlly());
			data.put("sjly", item.getSjly());
			data.put("dxfssl", item.getDxfssl());
			data.put("tfsj", item.getTfsj());
			data.put("growth_value", item.getGrowth_value());
			data.put("growth_value_singleprice", item.getGrowth_value_singleprice());
			data.put("createuser", item.getCreateuser());
			data.put("createtime", item.getCreatetime());
			data.put("active", item.getActive());
			data.put("qy_projectid", item.getQy_projectid());
			data.put("etl", item.getEtl());
			data.put("gdspid", item.getGdspid());
			data.put("jljf", item.getJljf());
			data.put("sfbl", item.getSfbl());
			documents.add(data);
		}

		mongoCollection.insertMany(documents);
	}
}