package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Simple {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("192.168.30.120:2181,192.168.30.121:2181,192.168.10.10:2181", 12000, new TestWatcher2());

		List<String> l = zk.getChildren("/", false);
		for (String s : l) {
			System.out.println(s);
		}
	}
}

class TestWatcher2 implements Watcher {

	public void process(WatchedEvent arg0) {
		System.out.println(arg0.getState());
		System.out.println(arg0.getType());
	}
}