package com.yang.test.java.pure.reference;

public class TestReference2 {

	public static void main(String[] args) {
		MyDate a = new MyDate();
		System.out.println(a);

		new Thread(new Runnable() {
			public void run() {
				System.gc();
			}
		}).start();
	}
}