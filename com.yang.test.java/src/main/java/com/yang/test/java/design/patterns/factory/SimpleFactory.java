package com.yang.test.java.design.patterns.factory;

public class SimpleFactory {

	public static void main(String[] args) {
		Product p = SimpleFactory.makeProduct(0);
		p.show();
	}

	// 抽象产品
	public interface Product {
		void show();
	}

	// 具体产品：ProductA
	static class ConcreteProduct1 implements Product {
		public void show() {
			System.out.println("具体产品1显示...");
		}
	}

	// 具体产品：ProductB
	static class ConcreteProduct2 implements Product {
		public void show() {
			System.out.println("具体产品2显示...");
		}
	}

	final class Const {
		static final int PRODUCT_A = 0;
		static final int PRODUCT_B = 1;
		static final int PRODUCT_C = 2;
	}

	public static Product makeProduct(int kind) {
		switch (kind) {
		case Const.PRODUCT_A:
			return new ConcreteProduct1();
		case Const.PRODUCT_B:
			return new ConcreteProduct2();
		}
		return null;
	}
}