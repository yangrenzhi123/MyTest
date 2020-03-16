package com.yang.test.java.lock;
import java.util.ArrayList;
import java.util.List;

public class TestLock {

	public static int i = 0;

	public static void main(String[] args) throws InterruptedException {

		List<Thread> l = new ArrayList<Thread>();
		for (int i = 0; i < 10000; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					new TestLock().t();
				}
			});
			l.add(t);
		}

		for (Thread t : l) {
			t.start();
		}
		
		Thread.sleep(10000L);
		System.out.println(i);
	}

	public synchronized void t() {
		i = i + 1;
	}
}