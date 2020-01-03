package com.yang.test.java.ratelimiter;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static final Semaphore semphore = new Semaphore(1);
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			public void run() {
				try {
					semphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semphore.release();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					semphore.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semphore.release();
			}
		}).start();
	}
}