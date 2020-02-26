package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisSingleClear {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("192.168.30.120", 6379);

		Set<String> keys = j.keys("*");
		for(String key : keys) {
			System.out.println(j.get(key));
			j.del(key);
		}

		j.close();
	}
}