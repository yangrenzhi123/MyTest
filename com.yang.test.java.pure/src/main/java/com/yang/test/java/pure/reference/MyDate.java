package com.yang.test.java.pure.reference;

import java.util.Date;

public class MyDate extends Date {
	private static final long serialVersionUID = 723073183382105383L;

	public MyDate() {
	}

	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("obj [Date: " + this.getTime() + "] is gc");
	}

	public String toString() {
		return "Date: " + this.getTime();
	}
}