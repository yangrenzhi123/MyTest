package com.yang.test.java;

public class Single {

	public int id = 0;
	
	private static Single s = new Single();
	
	private Single(){};
	
	public static Single get(){
		return s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Single getS() {
		return s;
	}
}