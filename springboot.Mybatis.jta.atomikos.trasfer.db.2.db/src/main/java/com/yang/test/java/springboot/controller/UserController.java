package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.yang.test.java.springboot.dao1.UserDao1;

import entity.ScoreRecord;

@Controller
public class UserController {

	@Resource
	UserDao1 userDao1;
	@Autowired
	ScoreRecordService scoreRecordService;

	@Bean
	public Integer init() {
		new Thread(new Runnable() {
			public void run() {
				long start = 29;
				while (true) {
					long a = System.currentTimeMillis();
					List<ScoreRecord> l = userDao1.page(start);
					long aa = System.currentTimeMillis() - a;

					long b = System.currentTimeMillis();
					scoreRecordService.insertBatch(l);
					long bb = System.currentTimeMillis() - b;

					ScoreRecord last = l.get(l.size() - 1);

					System.out.println("查询耗时：" + aa + "，插入耗时：" + bb);
					System.out.println("数据条数：" + l.size());
					System.out.println("最后一条记录ID：" + last.getScorerecordzzid());

					if (l.size() < 10000) {
						break;
					} else {
						start = last.getScorerecordzzid();
					}
				}
			}
		}, "ScoreRecord数据转移线程").start();
		return 1;
	}
}