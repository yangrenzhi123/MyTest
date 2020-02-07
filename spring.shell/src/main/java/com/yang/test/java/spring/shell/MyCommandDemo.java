package com.yang.test.java.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MyCommandDemo {

	//add --a 1 --b 1
	@ShellMethod("Add two intergers together.")
	public int add(int a, int b) {
		return a + b;
	}
}