package com.yang.test.java.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	public static void main(String[] args) {
		AtomicInteger count = new AtomicInteger(0);

		System.out.println(count.incrementAndGet());

		System.out.println(count.decrementAndGet());
	}
}