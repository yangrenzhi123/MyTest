package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisIncr {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("192.168.197.81", 6379);

		
		// 自增
		System.out.println(j.incr("testKey2"));

		
		
		
		System.out.println(j.get("testKey2"));
		j.close();
	}
}