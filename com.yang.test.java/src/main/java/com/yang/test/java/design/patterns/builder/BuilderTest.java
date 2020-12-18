package com.yang.test.java.design.patterns.builder;

public class BuilderTest {

	public static void main(String[] args) {
		Builder builder = new ConcreteBuilder(); // 抽象建造者 & 具体建造者
		Director director = new Director(builder); // 指挥者
		Product product = director.construct(); // 产品
		product.show();
	}
}

class Product {
	private String partA;
	private String partB;
	private String partC;
	public void setPartA(String partA) {
		this.partA = partA;
	}
	public void setPartB(String partB) {
		this.partB = partB;
	}
	public void setPartC(String partC) {
		this.partC = partC;
	}
	public String getPartA() {
		return partA;
	}
	public String getPartB() {
		return partB;
	}
	public String getPartC() {
		return partC;
	}
	public void show() {
		// 显示产品的特性
		System.out.println(partA);
		System.out.println(partB);
		System.out.println(partC);
	}
}

abstract class Builder {
	// 创建产品对象
	protected Product product = new Product();

	public abstract void buildPartA();

	public abstract void buildPartB();

	public abstract void buildPartC();

	// 返回产品对象
	public Product getResult() {
		return product;
	}
}

class ConcreteBuilder extends Builder {
	public void buildPartA() {
		product.setPartA("建造 PartA");
	}

	public void buildPartB() {
		product.setPartB("建造 PartB");
	}

	public void buildPartC() {
		product.setPartC("建造 PartC");
	}
}

class Director {
	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	// 产品构建与组装方法
	public Product construct() {
		builder.buildPartA();
		builder.buildPartB();
		builder.buildPartC();
		return builder.getResult();
	}
}