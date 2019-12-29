package com.yang.test.java.future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture3 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();

		final Future<Integer> futureRate = executor.submit(new Callable<Integer>() {
			public Integer call() {
				try {
					if(true) {
						throw new RuntimeException();
					}
				}catch(Exception e) {
					return 0;
				}
				return 1;
			}
		});

		futureRate.get();

		System.out.println(1);
	}
}