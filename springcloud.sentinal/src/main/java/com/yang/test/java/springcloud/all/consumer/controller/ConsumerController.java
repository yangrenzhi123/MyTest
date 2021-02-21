package com.yang.test.java.springcloud.all.consumer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;

@RestController
public class ConsumerController {

	@RequestMapping("/")
	public String index(HttpServletRequest request) throws Exception {
		Entry entry = null;
		try {
			entry = SphU.entry("HelloWorld");
			/* 您的业务逻辑 - 开始 */
			Thread.sleep(1000);
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