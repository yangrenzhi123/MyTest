package com.yang.test.java.threadpool;

public class TestThread {

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println(1);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("已打算");
						break;
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
		
		//t.interrupt();
		System.out.println(11);
	}
}