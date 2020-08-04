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
		boolean first = true;
		String[] ids = new String[100000];
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

				ids[i++] = new String(rs);
			}else {
				ids[i++] = str;
			}
		}
		bf.close();
		
		// 准备连接
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("172.17.134.11", 9200, "http")));
		GetRequest r = new GetRequest("test");
		
		// 开始执行
		exec(ids, client, r);
		
		//释放资源
		client.close();
	}
	public static void exec(String[] ids, RestHighLevelClient client, GetRequest r) throws IOException {
		long a = System.currentTimeMillis();
		
		int j = 0;
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			r.id(id);
			GetResponse p = client.get(r, RequestOptions.DEFAULT);
			Map<String, Object> source = p.getSource();
			if(source == null) {
				j++;
				System.out.println(id + "，ids已执行到下标：" + i + "，耗时：" + (System.currentTimeMillis() - a));
			}
		}

		System.out.println("总耗时：" + (System.currentTimeMillis() - a) + "，未找到的数据量：" + j);
	}
}