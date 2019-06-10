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

public class YLock {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("172.28.51.33:2181", 12000, new TWatcher());

		// 创建节点
//		zk.create("/lyzh", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		zk.create("/lyzh/locks", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		zk.create("/lyzh/locks/00001", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zk.create("/lyzh/locks/00001/", "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		zk.create("/lyzh/locks/00001/", "2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		zk.create("/lyzh/locks/00001/", "3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		zk.create("/lyzh/locks/00001/", "4".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

		System.out.println();

		List<String> l = zk.getChildren("/lyzh/locks/00001", false);
		if (l != null && l.size() > 0) {
			String first = l.get(0);
			byte[] bs = zk.getData("/lyzh/locks/00001/" + first, true, new Stat());

			if ("1".equals(new String(bs))) {

			}
		}
		
		//删除
		
	}
}

class TWatcher implements Watcher {

	public void process(WatchedEvent arg0) {
	}
}