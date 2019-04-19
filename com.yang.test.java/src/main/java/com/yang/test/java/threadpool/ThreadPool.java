package com.yang.test.java.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService p = Executors.newCachedThreadPool();
		p.execute(new Runnable() {
			public void run() {
				System.out.println(1);
			}
		});
		p.shutdown();
		

		p = Executors.newFixedThreadPool(10);
		p.execute(new Runnable() {
			public void run() {
				System.out.println(1);
			}
		});
		p.shutdown();
		

		p = Executors.newWorkStealingPool(10);
		p.execute(new Runnable() {
			public void run() {
				System.out.println(1);
			}
		});
		p.shutdown();
	}
}