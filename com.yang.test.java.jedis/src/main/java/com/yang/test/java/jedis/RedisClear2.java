package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisClear2 {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("192.168.10.90", 7001);

		Set<String> keys = j.keys("h_tenant_group_map:*");

		for (String key : keys) {
			j.del(key);
		}

		j.close();
	}
}