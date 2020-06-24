package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class TestZookeeper {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("192.168.8.70:2181", 12000, new TestWatcher());

		List<String> l = zk.getChildren("/", false);

		for (String s : l) {
			System.out.println(s);
			List<String> cl = zk.getChildren("/" + s, false);
			for(String cs : cl) {
				System.out.println("\t" + cs);
			}
		}
	}
}

class TestWatcher implements Watcher {

	public void process(WatchedEvent arg0) {
		System.out.println(arg0.getState());
		System.out.println(arg0.getType());
	}
}