package com.yang.test.java.elasticsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsQueryByidBat {

	public static void main(String[] args) throws IOException {
		// 准备id
		String[] ids = new String[100000];
		int i=0;
		FileReader fr = new FileReader("C:/1.txt");
		BufferedReader bf = new BufferedReader(fr);
		String str;
		while ((str = bf.readLine()) != null) {
			ids[i++] = str;
		}
		bf.close();
		
		// 准备连接
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("172.17.134.7", 9200, "http")));
		GetRequest r = new GetRequest("test");
		
		// 开始执行
		exec(ids, client, r);
		
		//释放资源
		client.close();
	}
	public static void exec(String[] ids, RestHighLevelClient client, GetRequest r) throws IOException {
		long a = System.currentTimeMillis();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			r.id(id);
			GetResponse p = client.get(r, RequestOptions.DEFAULT);
			Map<String, Object> source = p.getSource();
			if(source == null) {
				System.out.println(id + "，ids已执行到下标：" + i);
			}
		}
		System.out.println(System.currentTimeMillis() - a);	
	}
}