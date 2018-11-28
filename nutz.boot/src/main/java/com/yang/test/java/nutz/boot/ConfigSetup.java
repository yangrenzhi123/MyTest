package com.yang.test.java.nutz.boot;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class ConfigSetup implements Setup {

	public void init(NutConfig nc) {
		System.out.println(123456);
	}

	public void destroy(NutConfig nc) {
	}
}