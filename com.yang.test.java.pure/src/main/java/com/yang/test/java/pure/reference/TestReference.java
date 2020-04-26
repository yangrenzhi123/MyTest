package com.yang.test.java.pure.reference;

import java.lang.ref.WeakReference;

public class TestReference {

	public static void main(String[] args) {
//		WeakReference<MyDate> ref = new WeakReference<MyDate>(new MyDate());
//		MyDate myDate = ref.get();
		
		MyDate myDate = new MyDate();

		// strong reference指向的时候，gc无法回收
		System.gc();

		System.out.println(myDate);
		myDate = null;

		// 已无指向，回收成功
		System.gc();
		
		System.out.println();
	}
}