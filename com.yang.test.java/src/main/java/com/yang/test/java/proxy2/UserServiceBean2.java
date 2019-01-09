package com.yang.test.java.proxy2;

public class UserServiceBean2 implements UserService {

	private String name;

	public UserServiceBean2() {
	}

	public UserServiceBean2(String name) {
		this.name = name;
	}

	public void say() {
		System.out.println("恭喜你来到此地...");
		this.hello();
	}

	public void hello() {
		System.out.println("欢迎您！");
	}

	public String getName() {
		return name;
	}

}