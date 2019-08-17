package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class Simple {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("192.168.197.81:2181", 12000, new TestWatcher2());



		List<String> l = zk.getChildren("/lyzh/locks/00001", true);
		for (String s : l) {
			System.out.println(s);
		}
		
		

//		byte[] bs = zk.getData("/lyzh/lock/000010000000004",true,new Stat());
//		System.out.println(new String(bs));
	}
}

class TestWatcher2 implements Watcher {

	public void process(WatchedEvent arg0) {
//		System.out.println(arg0.getState());
//		System.out.println(arg0.getType());
	}
}