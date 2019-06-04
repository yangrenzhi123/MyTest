package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisKeys2 {
	public static void main(String[] args) throws IOException {
		Jedis j1 = new Jedis("192.168.30.120", 6379);

		Set<String> s1 = j1.keys("UserService.getUserById*");

		for (String s : s1) {
			System.out.println("Key内容：" + s + "，值内容：" + j1.get(s));
		}

		j1.close();
	}
}