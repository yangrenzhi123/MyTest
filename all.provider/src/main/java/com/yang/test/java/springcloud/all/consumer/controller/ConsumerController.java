package com.yang.test.java.springcloud.all.consumer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yang.test.java.springboot.dao.UserDao;

@RestController
public class ConsumerController {

	public static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

	@Resource
	private UserDao userDao;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) throws Exception {
		threadLocal.set("ok");
		new Thread(new Runnable() {
			public void run() {
				new Thread(new Runnable() {
					public void run() {
						System.out.println(threadLocal.get());
					}
				}).start();
			}
		}).start();
		
		
		Entry entry = null;
		try {
			entry = SphU.entry("HelloWorld");
			/* 您的业务逻辑 - 开始 */
			Thread.sleep(1000);
			userDao.insert();
			/* 您的业务逻辑 - 结束 */
		} catch (BlockException e) {
			/* 流控逻辑处理 - 开始 */
			System.out.println("block!");
			/* 流控逻辑处理 - 结束 */
		} finally {
			if (entry != null) {
				entry.exit();
			}
		}
		return "success";
	}
}