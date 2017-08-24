package com.yang.test.java;

public class TestThread {

	public static final String M = "";
	public static final String N = "";

	public static void main(String[] args) {
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				synchronized (M) {
					System.out.println(1);
				}
			}
		});
		t2.start();
		Thread t = new Thread(new Runnable() {
			public void run() {
				synchronized (N) {
					System.out.println(1);
				}
			}
		});
		t.start();

		// run无多线程效果
		// t.run();
		System.out.println(2);
	}
}