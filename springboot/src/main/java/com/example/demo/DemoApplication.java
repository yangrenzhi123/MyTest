package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

		ZooKeeper zk = new ZooKeeper("192.168.30.120:2181,192.168.30.121:2181,192.168.10.10:2181", 12000, new TestWatcher2());

		List<String> l = zk.getChildren("/", false);
		for (String s : l) {
			System.out.println(s);
		}
		
		
		
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
class HelloController {

	@GetMapping("/")
	public String hello2() {
		return "123456";
	}
}



class TestWatcher2 implements Watcher {

	public void process(WatchedEvent arg0) {
		System.out.println(arg0.getState());
		System.out.println(arg0.getType());
	}
}