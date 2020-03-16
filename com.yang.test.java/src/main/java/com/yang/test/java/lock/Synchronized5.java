package com.yang.test.java.lock;

public class Synchronized5 {

	public static final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {

		new Thread(new R1()).start();
		new Thread(new R2()).start();
	}

	private static class R1 implements Runnable {

		public void run() {
			synchronized (lock) {
				throw new RuntimeException("test exception!!!");
			}
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			System.out.println(1);
			synchronized (lock) {
				System.out.println(2);
			}
		}
	}
}