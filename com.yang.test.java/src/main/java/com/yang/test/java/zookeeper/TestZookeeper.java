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

public class TestZookeeper {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("172.18.28.142:2181", 12000, new TestWatcher());

		List<String> l = zk.getChildren("/", false);
		
		for(String s : l) {
			if(s.equals("hbase")) {
				List<String> l2 = zk.getChildren("/"+s, false);
				for(String s2 : l2) {
					System.out.println(s2);
				}
			}
		}
		
		byte[] bs = zk.getData("/hbase/master",true,new Stat());
		System.out.println("结果值：" + new String(bs));
	}
}

class TestWatcher implements Watcher {

	public void process(WatchedEvent arg0) {
		System.out.println(arg0.getState());
		System.out.println(arg0.getType());
	}	
}