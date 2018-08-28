package com.yang.test.java.jedis;

import redis.clients.jedis.Jedis;

public class App {
	public static void main(String[] args) {
		Jedis j = new Jedis("172.28.51.33");

		j.set("testKey", "YangRenZhi");
		System.out.println(j.get("testKey"));
		
		j.close();
	}
}