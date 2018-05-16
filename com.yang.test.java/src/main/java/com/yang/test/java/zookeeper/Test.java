package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class Test {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 12000, new TestWatcher());
		String registryPath = "/com.xk.msa.registry";

		if(zk.exists(registryPath, false) == null){
			zk.create(registryPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}else{
			System.out.println("exsit");
		}
		

		//创建节点
		zk.create("/com.xk.msa.registry/address-", "192.168.5.152:8080".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		zk.create("/com.xk.msa.registry/address-", "192.168.5.152:8080".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		
		List<String> l = zk.getChildren("/com.xk.msa.registry", false);
		
		byte[] bs = zk.getData("/com.xk.msa.registry/" + l.get(0),true,new Stat());
		System.out.println(new String(bs));
		System.out.println();
		Thread.sleep(1000000000);
	}
}

class TestWatcher implements Watcher {

	public void process(WatchedEvent arg0) {
		System.out.println(arg0.getState());
		System.out.println(arg0.getType());
	}	
}