package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisSet {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("172.28.51.33", 6379);

		j.exists("ltest2");
		
//		Pipeline pipelined = j.pipelined();
//		pipelined.sadd("ltest2", "102");
//		pipelined.sadd("ltest2", "103");
//		pipelined.close();


		
		
		
		System.out.println(j.scard("ltest2"));
		System.out.println(j.spop("ltest2"));
		System.out.println(j.scard("ltest2"));

		j.close();
	}
}