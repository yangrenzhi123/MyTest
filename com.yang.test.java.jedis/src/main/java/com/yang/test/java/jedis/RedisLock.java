package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisLock {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("192.168.197.81", 6379);


		//使过期
		//j.expire("testKey", 0);
		
		
		//如果存在则不做任何动作
		//String a = j.set("testKey", "YangRenZhi", "NX", "PX", 6000);
		//System.out.println("a:" + a);


		
		
		
		//如果存在则不做任何动作
		//long a = j.setnx("testKey", "YangRenZhi");
		//System.out.println("a:" + a);
		
		
		
		//System.out.println(j.get("testKey"));
		j.close();
	}
}