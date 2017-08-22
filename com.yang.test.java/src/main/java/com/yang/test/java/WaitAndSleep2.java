package com.yang.test.java;

/**
 * 测试java对象锁的效果
 * 
 */
public class WaitAndSleep2 {

	public static Integer t = new Integer(1);
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new R1()).start();
		Thread.sleep(1000);

		new Thread(new R2()).start();
	}

	private static class R1 implements Runnable {
		public void run() {
			synchronized (WaitAndSleep2.t) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class R2 implements Runnable {
		public void run() {
			synchronized (WaitAndSleep2.t) {
				System.out.println(WaitAndSleep2.t);
			}
		}
	}
}