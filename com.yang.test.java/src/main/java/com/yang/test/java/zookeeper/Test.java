package com.yang.test.java.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class Test {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 12000, new TestWatcher());
		String registryPath = "/com.xk.msa.registry";
		
		zk.getChildren(registryPath, new TestWatcher());
		Thread.sleep(1000000);
		zk.exists(registryPath, false);
		
		
		if(zk.exists(registryPath, false) == null){
			zk.create(registryPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}else{
			System.out.println("exsit");
		}
		

		zk.create(registryPath+"2", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
	}
}

class TestWatcher implements Watcher {

	public void process(WatchedEvent arg0) {
		System.out.println(1);
	}	
}