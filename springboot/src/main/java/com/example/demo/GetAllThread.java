package com.example.demo;

public class GetAllThread {

	public static Thread[] findAllThread() {
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

		while (currentGroup.getParent() != null) {
			// 返回此线程组的父线程组
			currentGroup = currentGroup.getParent();
		}
		// 此线程组中活动线程的估计数
		int noThreads = currentGroup.activeCount();

		Thread[] lstThreads = new Thread[noThreads];
		// 把对此线程组中的所有活动子组的引用复制到指定数组中。
		currentGroup.enumerate(lstThreads);

		for (Thread thread : lstThreads) {
			System.out.println("线程数量：" + noThreads + " 线程id：" + thread.getId() + " 线程名称：" + thread.getName() + " 线程状态：" + thread.getState());
		}
		return lstThreads;
	}

	public static int countByName(String threadName) {
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

		while (currentGroup.getParent() != null) {
			// 返回此线程组的父线程组
			currentGroup = currentGroup.getParent();
		}
		// 此线程组中活动线程的估计数
		int noThreads = currentGroup.activeCount();

		Thread[] lstThreads = new Thread[noThreads];
		// 把对此线程组中的所有活动子组的引用复制到指定数组中。
		currentGroup.enumerate(lstThreads);

		int count = 0;
		for (Thread thread : lstThreads) {
			if (threadName.equals(thread.getName()))
				count++;
		}
		return count;
	}

	public static int countByStartwith(String start) {
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

		while (currentGroup.getParent() != null) {
			// 返回此线程组的父线程组
			currentGroup = currentGroup.getParent();
		}
		// 此线程组中活动线程的估计数
		int noThreads = currentGroup.activeCount();

		Thread[] lstThreads = new Thread[noThreads];
		// 把对此线程组中的所有活动子组的引用复制到指定数组中。
		currentGroup.enumerate(lstThreads);

		int count = 0;
		for (Thread thread : lstThreads) {
			if (thread.getName().startsWith(start))
				count++;
		}
		return count;
	}

	public static void listName() {
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

		while (currentGroup.getParent() != null) {
			// 返回此线程组的父线程组
			currentGroup = currentGroup.getParent();
		}
		// 此线程组中活动线程的估计数
		int noThreads = currentGroup.activeCount();

		Thread[] lstThreads = new Thread[noThreads];
		// 把对此线程组中的所有活动子组的引用复制到指定数组中。
		currentGroup.enumerate(lstThreads);

		for (Thread thread : lstThreads) {
			System.out.println(thread.getName());
		}
	}
}