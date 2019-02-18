package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisTimeout {
	public static void main(String[] args) throws IOException, InterruptedException {
		Jedis j = new Jedis("172.28.51.33", 6379);

		String key = "testKey";
		j.setex(key, 5, "YangRenZhi");

		while (true) {
			String a = j.get(key);

			if (a == null) {
				break;
			}

			System.out.println(a);

			Thread.sleep(1000);
		}
		j.close();
	}
}