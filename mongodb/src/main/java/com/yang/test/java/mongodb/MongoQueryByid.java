package com.yang.test.java.mongodb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoQueryByid {

	public static void main(String[] args) throws ParseException, InterruptedException, IOException {
		// 准备id
		boolean first = true;
		long[] ids = new long[100000];
		int i=0;
		FileReader fr = new FileReader("C:/1.txt");
		BufferedReader bf = new BufferedReader(fr);
		String str;
		while ((str = bf.readLine()) != null) {
			if(first == true) {
				first = false;
				
				byte[] bs = str.getBytes();
				byte[] rs = new byte[bs.length - 3];
				for(int j = 3;j<bs.length;j++) {
					rs[j-3] = bs[j];
				}

				ids[i++] = Long.parseLong(new String(rs));
			}else {
				ids[i++] = Long.parseLong(new String(str));
			}
		}
		bf.close();
		
		// 准备连接
		String ip = "172.17.134.10";
		int port = 27017;
		MongoClient mc = new MongoClient(ip, port);
		MongoDatabase mgdb = mc.getDatabase("test");
		MongoCollection<Document> c1 = mgdb.getCollection("h_recycle_record");
		
		// 执行
		exec(ids, c1);

		// 释放资源
		mc.close();
	}

	public static void exec(long[] ids, MongoCollection<Document> c1) {
		long a = System.currentTimeMillis();
		int j = 0;
		for (int i = 0; i < ids.length; i++) {
			long id = ids[i];
			BasicDBObject queryObject = new BasicDBObject("_id", id);
			FindIterable<Document> fi = c1.find(queryObject);
			Document d = fi.first();
			if (d == null) {
				j++;
				System.out.println(id + "，ids已执行到下标：" + i + "，耗时：" + (System.currentTimeMillis() - a));
			}
		}
		System.out.println("总耗时：" + (System.currentTimeMillis() - a) + "，未找到的数据量：" + j);
	}
}