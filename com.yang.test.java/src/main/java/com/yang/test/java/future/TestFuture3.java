package com.yang.test.java.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture3 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();

		int i = 1;

		final Future<Integer> futureRate = executor.submit(new Callable<Integer>() {
			public Integer call() {
				if (i == 1) {
					throw new RuntimeException();
				}
				return 1;
			}
		});
		final Future<Integer> futureRate2 = executor.submit(new Callable<Integer>() {
			public Integer call() {
				if (i == 1) {
					throw new RuntimeException();
				}
				return 1;
			}
		});

		try {
			futureRate.get();
			futureRate2.get();
		}finally {
			executor.shutdown();
		}
		System.out.println(1);
	}
}