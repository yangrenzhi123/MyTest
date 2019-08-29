package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.ExchangeRecordService;
import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.RecycleRecord;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;
	@Autowired
	ExchangeRecordService exchangeRecordService;

	@GetMapping("/")
	@ResponseBody
	public void hello() throws InterruptedException {
		int i = 0;

		while (true) {
			long a = System.currentTimeMillis();
			List<RecycleRecord> l = userDao.page();
			long aa = System.currentTimeMillis() - a;
			System.out.println("查询耗时：" + aa);
			System.out.println("数据条数：" + l.size());

			if (l == null || l.size() == 0) {
				break;
			}

			exchangeRecordService.exec(l);

			if (i++ < 10) {
				break;
			}
		}
	}
}