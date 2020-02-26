package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class Simple {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {
		ZooKeeper zk = new ZooKeeper("172.17.202.153:2181,172.17.202.154:2181,172.17.202.152:2181", 12000, new TestWatcher2());

		String scheme = "digest";
		
		zk.addAuthInfo(scheme, "lry:123456".getBytes());
		
		Id id = new Id(scheme, DigestAuthenticationProvider.generateDigest("lry:123456"));
		ACL acl = new ACL(Perms.ALL, id);
		
		List<ACL> acls = new ArrayList<>();
        acls.add(acl);
        
//        Stat rootStat = zk.exists("/", true);
//        zk.setACL("/", acls, rootStat.getVersion());
        
        List<ACL> rootAcls = zk.getACL("/", new Stat());
        for(ACL acc : rootAcls) {
        	System.out.println("/：" + acc.getPerms());
        	System.out.println("/：" + acc.getId().getScheme());
        }
        
		String registryPath = "/test5";
		zk.create(registryPath, registryPath.getBytes(), acls/*ZooDefs.Ids.OPEN_ACL_UNSAFE*/, CreateMode.PERSISTENT);
		//zk.create(registryPath + "/address-", "192.168.5.158:8080".getBytes(), acls, CreateMode.EPHEMERAL_SEQUENTIAL);


		byte[] bs = zk.getData(registryPath, true, new Stat());
		System.out.println(new String(bs));
		
		List<String> l = zk.getChildren(registryPath, true);
		for (String s : l) {
			System.out.print(s + " ");
			bs = zk.getData(registryPath + "/" +s, true, new Stat());
			System.out.println(new String(bs));
		}

//		byte[] bs = zk.getData("/test/address-0000000001", true, new Stat());
//		System.out.println(new String(bs));
	}
}

class TestWatcher2 implements Watcher {

	public void process(WatchedEvent arg0) {
//		System.out.println(arg0.getState());
//		System.out.println(arg0.getType());
	}
}