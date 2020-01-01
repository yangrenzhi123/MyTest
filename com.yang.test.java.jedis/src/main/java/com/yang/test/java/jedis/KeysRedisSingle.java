package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class KeysRedisSingle {
	public static void main(String[] args) throws IOException {
		Jedis jedis = new Jedis("192.168.10.20", 7001);

		

		jedis.close();
	}
}