package com.yang.test.java.lock;

public class WaitAndNotify {

	public static final Object lock = new Object();

	public static void main(String[] args) {
		new Thread(new R1(), "R1").start();
//		new Thread(new R2(), "R2").start();
//		new Thread(new R3(), "R3").start();
	}

	private static class R1 implements Runnable {
		public void run() {
			synchronized (lock) {
				try {
					// 调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
					lock.wait();

					// 被唤醒之后，会重新获得对象锁。
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			synchronized (lock) {
				lock.notify();
			}
		}
	}

	private static class R3 implements Runnable {
		public void run() {
			System.out.println();

			synchronized (lock) {
				lock.notify();
			}
		}
	}
}