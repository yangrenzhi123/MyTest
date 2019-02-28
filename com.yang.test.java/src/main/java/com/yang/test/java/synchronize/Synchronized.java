package com.yang.test.java.synchronize;

/**
 * 测试java对象锁的效果
 * 
 */
public class Synchronized {

	public static void main(String[] args) throws InterruptedException {
		
		
		new Thread(new R1()).start();
		new Thread(new R2()).start();
	}

	private static class R1 implements Runnable {
		
		public void run() {
			String s = 1+""+1+""+1;
			synchronized (s) {
				System.out.println(1);
			}
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			String s = 1+""+1+""+1;
			synchronized (s) {
				System.out.println(2);
			}
		}
	}
}