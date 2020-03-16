package com.yang.test.java.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自旋锁 <br />
 * 1.等待可中断<br />
 * 2.公平锁<br />
 * 3.锁绑定多个条件<br />
 */
public class TestReentrantLock {

	ReentrantLock lock = new ReentrantLock();

	public static int i = 0;

	public static void main(String[] args) throws InterruptedException {

		List<Thread> l = new ArrayList<Thread>();
		for (int i = 0; i < 10000; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					new TestReentrantLock().t();
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

	public void t() {
		try {
			lock.lock();
			i = i + 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}