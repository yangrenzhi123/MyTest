package com.yang.test.java.nutz.boot;

import org.nutz.boot.NbApp;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;

@IocBean
@SetupBy(ConfigSetup.class)
public class MainLauncher {

	@Ok("raw")
	@At("/")
	public long now() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) throws Exception {
		new NbApp(MainLauncher.class).run();
	}
}