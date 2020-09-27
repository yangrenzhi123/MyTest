package com.yang.test.java.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RLockReactive;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

public class RedissonTest {
	public static void main(String[] args) {
		Config config = new Config();
		//config.useSingleServer().setAddress("redis://192.168.30.120:6379");
		config.useClusterServers().setScanInterval(2000)
		.addNodeAddress("redis://192.168.10.228:27001", "redis://192.168.10.228:27002", "redis://192.168.10.228:27003")
		.addNodeAddress("redis://192.168.10.229:27004", "redis://192.168.10.229:27005", "redis://192.168.10.229:27006");

		RedissonClient redisson = Redisson.create(config);
		RLock lock = redisson.getLock("testKey");

//		RedissonReactiveClient redisson = Redisson.createReactive(config);
//		RLockReactive lock = redisson.getLock("testKey");

		// 业务
		// lock.lock(); // 阻塞
		boolean result = lock.tryLock(); // 非阻塞
		if (result) {
			try {
				System.out.println(1);
			} finally {
				lock.unlock();
			}
		} else {
			System.out.println(2);
		}
	}
}