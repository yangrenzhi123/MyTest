package com.yang.test.java.HttpClient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class Lyzh {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		String cookie = "lyzh-saas=s%3AJExoaP4TjrTqfmFczc8xevKXE0Nxv6KF.IVvTA1Gzn2tQoqfR2scsBjySjqp1mAKuAaiXimOudSE";
		
		HttpPost post = null;
		StringEntity entity = null;
		long a;
		
		
		
		//----------------------------------------------hfyhxm 123456--------------------------------------------------------------------
		post = new HttpPost("http://hfyhxm.lyzhyun.com:8000/api-fl/community/l.do");
		post.setHeader("Cookie", cookie);
		entity = new StringEntity("{\"pageNum\":1,\"devicetypeid\":null,\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"cjmc\":null,\"ddmc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}", ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);
		System.out.println("Starting...");
		a = System.currentTimeMillis();
		HttpClients.createDefault().execute(post);
		System.out.println("基础信息-场景信息-小区信息，查询层级(中国)，耗时：" + (System.currentTimeMillis() - a));
		
		
		post = new HttpPost("http://hfyhxm.lyzhyun.com:8000/api-fl/employer/l.do");
		post.setHeader("Cookie", cookie);
		entity = new StringEntity("{\"pageNum\":1,\"devicetypeid\":null,\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"cjmc\":null,\"ddmc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}", ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);
		System.out.println("Starting...");
		a = System.currentTimeMillis();
		HttpClients.createDefault().execute(post);
		System.out.println("基础信息-场景信息-单位信息，查询层级(中国)，耗时：" + (System.currentTimeMillis() - a));
		
		
		post = new HttpPost("http://hfyhxm.lyzhyun.com:8000/api-fl/school/l.do");
		post.setHeader("Cookie", cookie);
		entity = new StringEntity("{\"pageNum\":1,\"devicetypeid\":null,\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"cjmc\":null,\"ddmc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}", ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);
		System.out.println("Starting...");
		a = System.currentTimeMillis();
		HttpClients.createDefault().execute(post);
		System.out.println("基础信息-场景信息-学校信息，查询层级(中国)，耗时：" + (System.currentTimeMillis() - a));
		
		
		post = new HttpPost("http://hfyhxm.lyzhyun.com:8000/api-fl/tenantgroup/l.do");
		post.setHeader("Cookie", cookie);
		entity = new StringEntity("{\"pageNum\":1,\"undefined\":\"1\",\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"zhnc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}", ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);
		System.out.println("Starting...");
		a = System.currentTimeMillis();
		HttpClients.createDefault().execute(post);
		System.out.println("基础信息-用户信息-业主，查询层级(中国)，耗时：" + (System.currentTimeMillis() - a));
		
		
		post = new HttpPost("http://hfyhxm.lyzhyun.com:8000/api-fl/bumen/l.do");
		post.setHeader("Cookie", cookie);
		entity = new StringEntity("{\"pageNum\":1,\"undefined\":\"3\",\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"zhnc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}", ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);
		System.out.println("Starting...");
		a = System.currentTimeMillis();
		HttpClients.createDefault().execute(post);
		System.out.println("基础信息-用户信息-部门负责人，查询层级(中国)，耗时：" + (System.currentTimeMillis() - a));
		
		c("http://hfyhxm.lyzhyun.com:8000/api-fl/banji/l.do", cookie, 
		"{\"pageNum\":1,\"undefined\":\"2\",\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"zhnc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}",
		"基础信息-用户信息-班级负责人，查询层级(中国)");
		
		c("http://hfyhxm.lyzhyun.com:8000/api-fl/greenuser/l.do", cookie, 
		"{\"pageNum\":1,\"undefined\":\"1\",\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"sjhm\":null,\"zhnc\":null,\"cyewm\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}",
		"基础信息-用户信息-绿色用户，查询层级(中国)");
		
		c("http://hfyhxm.lyzhyun.com:8000/api-fl/equipdispenser/l.do", cookie, 
		"{\"pageNum\":1,\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"ffjlx\":null,\"sbbh\":null,\"ddmc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}",
		"基础信息-设备管理-智能垃圾袋发放机，查询层级(中国)");
		
		c("http://hfyhxm.lyzhyun.com:8000/api-fl/equipbin/l.do", cookie, 
		"{\"pageNum\":1,\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"flxlx\":null,\"sbbh\":null,\"ddmc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}",
		"基础信息-设备管理-智能垃圾分类箱，查询层级(中国)");
		
		c("http://hfyhxm.lyzhyun.com:8000/api-fl/equipment/l.do", cookie, 
		"{\"pageNum\":1,\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"sbbh\":null,\"ddmc\":null,\"type\":1,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}",
		"基础信息-设备管理-终端设备，查询层级(中国)");
		
		c("http://hfyhxm.lyzhyun.com:8000/api-fl/equipelectbalance/l.do", cookie, 
		"{\"pageNum\":1,\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"dzcyt\":null,\"sbbh\":null,\"ddmc\":null,\"qycs\":\"5\",\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}",
		"基础信息-设备管理-智能电子秤，查询层级(中国)");
		
		c("http://hfyhxm.lyzhyun.com:8000/api-fl/equipfixedvideo/l.do", cookie, 
		"{\"pageNum\":1,\"qycs\":\"5\",\"regionid\":\"babb5342-6ade-4158-b070-baf1d18dae1c\",\"qylx\":\"1\",\"communityid\":null,\"bdzt\":null,\"orderid\":null,\"sbmc\":null,\"productid\":\"58fd4d48-cbe5-430c-8cca-8b5b39469bd5\",\"pageSize\":10,\"orderSjly\":0}",
		"基础信息-设备管理-固定视频，查询层级(中国)");
	}

	public static void c(String url, String cookie, String json, String title) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		post.setHeader("Cookie", cookie);
		StringEntity entity = new StringEntity(json, ContentType.create("application/json", "UTF-8"));
		post.setEntity(entity);
		System.out.println("Starting...");
		long a = System.currentTimeMillis();
		HttpClients.createDefault().execute(post);
		System.out.println(title + "，耗时：" + (System.currentTimeMillis() - a));
	}
}