package com.yang.test.java.reflect;

import java.lang.reflect.Field;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		long l = System.currentTimeMillis();
		
		A a = new A();
		a.setA(1);
		a.setB(new Date(l));

		B b = new B();
		b.setA(1);
		b.setB(new Date(l));

		Class<? extends A> clazz = a.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object fieldValue = field.get(a);

			Field compareField = B.class.getDeclaredField(fieldName);
			compareField.setAccessible(true);
			Object compareValue = compareField.get(b);

			if (!fieldValue.equals(compareValue)) {
				System.out.println(fieldValue);
				System.out.println(compareValue);
				System.out.println(fieldName + " not equals");
			} else {
				System.out.println(fieldName + " equals");
			}
		}
	}
}

class A {
	private Integer a;
	private Date b;

	public Date getB() {
		return b;
	}

	public void setB(Date b) {
		this.b = b;
	}

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}
}

class B {
	private Integer a;
	private Date b;

	public Date getB() {
		return b;
	}

	public void setB(Date b) {
		this.b = b;
	}

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}
}