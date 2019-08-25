package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisClear2 {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("172.31.73.185", 6379);

		Set<String> keys = j.keys("*");

		for (String key : keys) {
			if(key.endsWith("orderCode")) {
				continue;
			}
			
			j.del(key);
		}

		j.close();
	}
}