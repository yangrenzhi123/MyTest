package com.yang.test.java;

public class GenericityTest {

	public static void main(String[] args) {
		Pi<S> p1 = new Pi<S>();
		Pi<F> p2 = new Pi<F>();
		
		test(p1);
		test(p2);
	}
	
	public static void test(Pi<? extends F> s){
		
	}
}

class Pi<T> {

	private T a;

	public T getA() {
		return a;
	}

	public void setA(T a) {
		this.a = a;
	}
}

class F {
}
class S extends F {
}