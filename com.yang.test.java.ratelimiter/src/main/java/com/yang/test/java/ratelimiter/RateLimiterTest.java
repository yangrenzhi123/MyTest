package com.yang.test.java.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

	public static final RateLimiter limiter = RateLimiter.create(1);
	
	public static void main(String[] args) {
		
		System.out.println();
		
		new Thread(new Runnable() {
			public void run() {
				double w = limiter.acquire(8);
				System.out.println(w);
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				double w = limiter.acquire();
				System.out.println(w);
			}
		}).start();
	}
}