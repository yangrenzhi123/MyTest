package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkChildren {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 12000, new Watcher() {
			public void process(WatchedEvent arg0) {
				System.out.print(arg0.getState() + "：");
				System.out.println(arg0.getType());
			}
		});

		List<String> l = zk.getChildren("/lyzh/locks/00001", false);
		for(String item : l) {
			byte[] bs = zk.getData("/lyzh/locks/00001/" + item, true, new Stat());
			System.out.print(item + "：");
			System.out.println(new String(bs));
		}
	}
}