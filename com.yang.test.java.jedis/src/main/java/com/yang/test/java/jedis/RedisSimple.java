package com.yang.test.java.jedis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisSimple {
	public static void main(String[] args) throws IOException {
//		Jedis j = new Jedis("172.31.73.185", 6379);

		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.10.20", 7001));
		nodes.add(new HostAndPort("192.168.10.20", 7002));
		nodes.add(new HostAndPort("192.168.10.20", 7003));
		nodes.add(new HostAndPort("192.168.10.22", 7004));
		nodes.add(new HostAndPort("192.168.10.22", 7005));
		nodes.add(new HostAndPort("192.168.10.22", 7006));
		JedisCluster j = new JedisCluster(nodes);

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

//		j.del(key);
		Map<String, String> m = j.hgetAll("h_tenant_group_map:LYZH190107982701");
		if(m.toString().contains("\"zhzt\"=0,")) {
			System.out.println("'" +m.get("\"tenantgroupid\"") + "',");
		}

//		j.set(key, key, "NX", "EX", 2*60);

//		Map<String, String> m = new HashMap<String, String>();
//		m.put("1", "1");
//		j.hmset(key, m);

		j.close();
	}
}