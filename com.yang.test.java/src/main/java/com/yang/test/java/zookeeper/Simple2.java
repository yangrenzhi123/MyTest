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
import org.apache.zookeeper.server.auth.IPAuthenticationProvider;

public class Simple2 {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {
		ZooKeeper zk = new ZooKeeper("192.168.8.70:2183", 12000, new TestWatcher3());

		String scheme = "ip"; //代表采用的某种权限机制
		
		Id id = new Id(scheme, "172.17.0.1");
		ACL acl = new ACL(Perms.ALL, id);
		List<ACL> acls = new ArrayList<>();
        acls.add(acl);
        Stat rootStat = zk.exists("/", true);
        zk.setACL("/", acls, rootStat.getVersion());
        
        List<ACL> rootAcls = zk.getACL("/", new Stat());
        for(ACL acc : rootAcls) {
        	System.out.println("/：" + acc.getPerms());
        	System.out.println("/：" + acc.getId().getScheme());
        }

		//zk.create("/test2", "/test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//zk.create(registryPath, registryPath.getBytes(), acls/*ZooDefs.Ids.OPEN_ACL_UNSAFE*/, CreateMode.PERSISTENT);
		//zk.create(registryPath + "/address-", "192.168.5.158:8080".getBytes(), acls, CreateMode.EPHEMERAL_SEQUENTIAL);


//		byte[] bs = zk.getData(registryPath, true, new Stat());
//		System.out.println(new String(bs));
		
		List<String> l = zk.getChildren("/", true);
		for (String s : l) {
			System.out.println(s);
		}

//		byte[] bs = zk.getData("/test/address-0000000001", true, new Stat());
//		System.out.println(new String(bs));
	}
}

class TestWatcher3 implements Watcher {

	public void process(WatchedEvent arg0) {
//		System.out.println(arg0.getState());
//		System.out.println(arg0.getType());
	}
}