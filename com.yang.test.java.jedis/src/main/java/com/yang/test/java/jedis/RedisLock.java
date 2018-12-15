package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisLock {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("172.28.51.33", 6379);

		//如果存在则不做任何动作
//		String a = j.set("testKey", "YangRenZhi", "NX", "PX", 60000);
//		System.out.println("a:" + a);


		//使过期
		j.expire("testKey", 0);
		
		
		
		//如果存在则不做任何动作
		long a = j.setnx("testKey", "YangRenZhi");
		System.out.println("a:" + a);
		
		
		
		System.out.println(j.get("testKey"));
		j.close();
	}
}