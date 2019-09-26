package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.yang.test.java.springboot.dao1.UserDao1;
import com.yang.test.java.springboot.dao2.UserDao2;

import entity.HEtlScoreRecord;
import entity.HScoreRecord;

@Controller
public class UserController {

	@Resource
	private UserDao1 userDao1;
	@Resource
	private UserDao2 userDao2;
	@Resource
	private MyService myService;

	@Bean
	public Integer init() {
		long start = 0;
		while (true) {
			try {
				long a = System.currentTimeMillis();
				List<HEtlScoreRecord> l = userDao1.page(start);
				System.out.println("查询1耗时：" + (System.currentTimeMillis() - a));

				if (l == null || l.size() == 0) break;

				System.out.println("数据量：" + l.size());

				long b = System.currentTimeMillis();
				List<HScoreRecord> ll = userDao2.sflszhInScorerecordid(l);
				System.out.println("查询2耗时：" + (System.currentTimeMillis() - b));

				long c = System.currentTimeMillis();
				myService.updateBatch(ll);
				System.out.println("更新耗时：" + (System.currentTimeMillis() - c));

				long last = l.get(l.size() - 1).getId();
				System.out.println("本次最大HEtlScoreRecord的ID为：" + last);
				System.out.println("本次总耗时：" + (System.currentTimeMillis() - a));
				System.out.println();
				
				start = last;
			}catch(Exception e) {
				System.out.println("start: "+start+", message: "+e.getMessage());
			}
		}
		return 1;
	}
}