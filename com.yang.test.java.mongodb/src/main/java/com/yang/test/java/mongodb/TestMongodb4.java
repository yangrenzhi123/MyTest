package com.yang.test.java.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class TestMongodb4 {

	public static void main(String[] args) throws InterruptedException {
		MongoClient mc1 = new MongoClient("192.168.10.228", 27017);
		MongoClient mc2 = new MongoClient("192.168.10.229", 27017);
		MongoClient mc3 = new MongoClient("192.168.10.225", 27017);
		final MongoDatabase md1 = mc1.getDatabase("test_rhb_5");
		final MongoDatabase md2 = mc2.getDatabase("test_rhb_5");
		final MongoDatabase md3 = mc3.getDatabase("test_rhb_5");

		final CountDownLatch latch = new CountDownLatch(10);

		List<Thread> l = new ArrayList<>();
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));
		l.add(new Thread(new Runnable() {
			public void run() {
				latch.countDown();
			}
		}));

		for (Thread t : l) {
			t.start();
		}

		latch.await();
		mc1.close();
		mc2.close();
		mc3.close();
	}
}