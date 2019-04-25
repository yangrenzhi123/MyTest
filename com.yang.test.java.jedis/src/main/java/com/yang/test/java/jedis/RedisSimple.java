package com.yang.test.java.jedis;

import java.io.IOException;

import redis.clients.jedis.Jedis;

public class RedisSimple {
	public static void main(String[] args) throws IOException {
		Jedis j = new Jedis("192.168.10.230", 7001);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.20", 7001));
//		nodes.add(new HostAndPort("192.168.10.20", 7002));
//		nodes.add(new HostAndPort("192.168.10.20", 7003));
//		nodes.add(new HostAndPort("192.168.10.22", 7004));
//		nodes.add(new HostAndPort("192.168.10.22", 7005));
//		nodes.add(new HostAndPort("192.168.10.22", 7006));
//		JedisCluster j = new JedisCluster(nodes);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.30.62", 7001));
//		nodes.add(new HostAndPort("192.168.30.62", 7002));
//		nodes.add(new HostAndPort("192.168.30.62", 7003));
//		nodes.add(new HostAndPort("192.168.30.62", 7004));
//		nodes.add(new HostAndPort("192.168.30.62", 7005));
//		nodes.add(new HostAndPort("192.168.30.62", 7006));
//		JedisCluster j = new JedisCluster(nodes);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.228", 7001));
//		nodes.add(new HostAndPort("192.168.10.228", 7002));
//		nodes.add(new HostAndPort("192.168.10.228", 7003));
//		nodes.add(new HostAndPort("192.168.10.229", 7004));
//		nodes.add(new HostAndPort("192.168.10.229", 7005));
//		nodes.add(new HostAndPort("192.168.10.229", 7006));
//		JedisCluster j = new JedisCluster(nodes);

//		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("192.168.10.240", 7001));
//		nodes.add(new HostAndPort("192.168.10.240", 7002));
//		nodes.add(new HostAndPort("192.168.10.240", 7003));
//		nodes.add(new HostAndPort("192.168.10.240", 7004));
//		nodes.add(new HostAndPort("192.168.10.240", 7005));
//		nodes.add(new HostAndPort("192.168.10.240", 7006));
//		JedisCluster j = new JedisCluster(nodes);

		String key = "h_tenant_groupLYZH1904253423";
//		j.select(3);
//		System.out.println(j.get(key));
//		j.del(key);

//		Map<String, String> m = new HashMap<String, String>();
//		m.put("1", "1");
//		j.hmset(key, m);

		System.out.println(j.hgetAll(key));

		j.close();
	}
}