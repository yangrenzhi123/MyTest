package com.yang.test.java.proxy2;

public class UserServiceBean implements UserService {

	private String name;

	public UserServiceBean() {
	}

	public UserServiceBean(String name) {
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