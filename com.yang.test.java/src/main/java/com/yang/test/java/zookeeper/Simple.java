package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Simple {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {
		ZooKeeper zk = new ZooKeeper("192.168.1.106:2001,192.168.1.106:2002,192.168.1.106:2003", 12000, new TestWatcher2());

//		String scheme = "digest"; //代表采用的某种权限机制
//		zk.addAuthInfo(scheme, "lry:123456".getBytes());
		
//		Id id = new Id(scheme, DigestAuthenticationProvider.generateDigest("lry:123456"));
//		ACL acl = new ACL(Perms.ALL, id);
//		List<ACL> acls = new ArrayList<>();
//        acls.add(acl);
        //Stat rootStat = zk.exists("/", true);
        //zk.setACL("/", acls, rootStat.getVersion());
        
//        List<ACL> rootAcls = zk.getACL("/", new Stat());
//        for(ACL acc : rootAcls) {
//        	System.out.println("/：" + acc.getPerms());
//        	System.out.println("/：" + acc.getId().getScheme());
//        }

		//zk.create("/test2", "/test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//zk.create(registryPath, registryPath.getBytes(), acls/*ZooDefs.Ids.OPEN_ACL_UNSAFE*/, CreateMode.PERSISTENT);
		//zk.create(registryPath + "/address-", "192.168.5.158:8080".getBytes(), acls, CreateMode.EPHEMERAL_SEQUENTIAL);


//		byte[] bs = zk.getData(registryPath, true, new Stat());
//		System.out.println(new String(bs));
		
		ls(zk, "/");

//		byte[] bs = zk.getData("/test/address-0000000001", true, new Stat());
//		System.out.println(new String(bs));
		
		zk.close();
	}

	public static void ls(ZooKeeper zk, String d) throws KeeperException, InterruptedException, UnsupportedEncodingException {
		System.out.println(URLDecoder.decode(d, "utf8"));

		List<String> l = zk.getChildren(d, true);
		if (l != null && l.size() > 0) {
			for (String s : l) {
				ls(zk, (d.length() > 1 ? d + "/" : d) + s);
			}
		}
	}
}

class TestWatcher2 implements Watcher {

	public void process(WatchedEvent arg0) {
//		System.out.println(arg0.getState());
//		System.out.println(arg0.getType());
	}
}