package com.yang.test.nutz;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;

@Modules(scanPackage = true)
public class MainModule {

	@At("init")
	// @Ok代表入口函数成功执行后返回的结果，可以为jsp页面，也可以以json格式返回数据（例如@Fail-->执行失败情况）
	@Ok("jsp:/views/hello.jsp")
	@Fail("json")
	public String init() {
		return "Hello Nutz!";
	}
}