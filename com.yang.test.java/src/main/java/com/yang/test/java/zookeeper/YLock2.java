package com.yang.test.java.zookeeper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class YLock2 {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		String value = "2";
		
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 12000, new Watcher() {
			public void process(WatchedEvent arg0) {
				System.out.print(arg0.getState() + "：");
				System.out.println(arg0.getType());
			}
		});

		zk.create("/lyzh/locks/00001/", value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

		while(true) {
			Thread.sleep(1000);

			List<String> l = zk.getChildren("/lyzh/locks/00001", false);
			if (l != null && l.size() > 0) {
				Collections.sort(l);

				String last = l.get(0);
				byte[] bs = zk.getData("/lyzh/locks/00001/" + last, true, new Stat());

				if (value.equals(new String(bs))) {
					System.out.println("获得锁");
				}else {
					System.out.println("未获得锁");
				}
			}
		}
	}
}