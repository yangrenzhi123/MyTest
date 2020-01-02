package com.yang.test.java.jedis.clear.zs;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisScan {

	public static void main(String[] args) throws IOException {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();

		nodes.add(new HostAndPort("192.168.10.20", 7001));
		nodes.add(new HostAndPort("192.168.10.20", 7002));
		nodes.add(new HostAndPort("192.168.10.20", 7003));
		nodes.add(new HostAndPort("192.168.10.22", 7004));
		nodes.add(new HostAndPort("192.168.10.22", 7005));
		nodes.add(new HostAndPort("192.168.10.22", 7006));

		JedisCluster jedis = new JedisCluster(nodes);

		jedis.set("{yrz_test}:tttttest", "1");
		jedis.set("{yrz_test}:tttttesr", "2");

		String cursor = ScanParams.SCAN_POINTER_START;
		String key = "{yrz_test}:*";
		ScanParams scanParams = new ScanParams();
		scanParams.match(key);
		scanParams.count(1000);
		while (true) {
			ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
			cursor = scanResult.getCursor();
			List<String> list = scanResult.getResult();
			long t1 = System.currentTimeMillis();
			for (int m = 0; m < list.size(); m++) {
				String trueKey = list.get(m);
				jedis.del(trueKey);
			}
			long t2 = System.currentTimeMillis();
			System.out.println("删除" + list.size() + "条数据，耗时: " + (t2 - t1) + "毫秒，cursor:" + cursor);
			if ("0".equals(cursor)) {
				break;
			}
		}

		jedis.close();
	}
}