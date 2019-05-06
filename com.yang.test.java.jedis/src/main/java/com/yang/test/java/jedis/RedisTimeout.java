package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisTimeout {
	public static void main(String[] args) throws IOException, InterruptedException {
		Jedis j = new Jedis("192.168.10.230", 7001);

		String key = "testKey";
		j.setex(key, 5, "YangRenZhi");
		
		System.out.println(j.get(key));

		j.close();
	}
}