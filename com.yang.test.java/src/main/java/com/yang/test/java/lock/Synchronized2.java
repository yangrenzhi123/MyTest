package com.yang.test.java.lock;

public class Synchronized2 {
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				new Synchronized2().test();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				new Synchronized2().test();
			}
		}).start();
	}
	
	private synchronized void test(){
		System.out.println(1);
	}
}