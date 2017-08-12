package com.yang.test.java;

public class WaitAndSleep2 {

	public static void main(String[] args) {
		new Thread(new R1(), "11").start();
		new Thread(new R2(), "22").start();
	}

	private static class R1 implements Runnable {
		public void run() {
			synchronized (Single.get()) {
				while (true) {
					way();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			synchronized (Single.get()) {
				while (true) {
					way();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void way() {
		Single.get().id = Single.get().id + 1;
		System.out.println(Single.get().id);
	}
}