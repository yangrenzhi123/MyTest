package com.yang.test.java.springboot;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

@Service
public class RedisService {

	Jedis j = new Jedis("192.168.30.120", 6379);

	public static final ThreadLocal<RenewalThread> renewals = new ThreadLocal<>();

	public String getDistributedLock(final String key) {
		SetParams sp = SetParams.setParams();
		sp.nx();
		sp.ex(60);
		String result = j.set(key, "test", sp);
		if ("OK".equals(result)) {
			System.out.println("key：" + key + "，获得锁");
			final RenewalThread t = new RenewalThread();
			StringBuilder sb = new StringBuilder("redis_lock_renewal_");
			sb.append(key);
			sb.append("_");
			sb.append(System.currentTimeMillis());
			t.setName(sb.toString());
			t.setDaemon(true);
			t.setFlag(1);
			t.setJedis(j);
			t.setKey(key);
			renewals.set(t);
			t.start();
		}
		return result;
	}

	public void releaseDistributedLock(String key) {
		RenewalThread t = renewals.get();
		if (t != null) {
			t.setFlag(0);
			j.del(key);
		}
	}
}