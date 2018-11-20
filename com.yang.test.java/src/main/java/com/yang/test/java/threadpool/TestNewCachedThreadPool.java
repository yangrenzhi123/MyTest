package com.yang.test.java.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestNewCachedThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		while(true) {
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(1);
				}
			});
			
			Thread.sleep(500);
		}
	}
}