package com.yang.test.java.jedis;

import redis.clients.jedis.Jedis;

public class App {
	public static void main(String[] args) {
		Jedis j = new Jedis("192.168.19.147");

		//j.set("testKey", "YangRenZhi");
		System.out.println(j.get("testKey"));
		
		j.close();
	}
}