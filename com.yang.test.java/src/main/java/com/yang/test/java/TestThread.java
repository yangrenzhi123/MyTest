package com.yang.test.java;

public class TestThread {

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println(1);
			}
		});
		t.start();
		
		// run无多线程效果
		//t.run();
	}
}