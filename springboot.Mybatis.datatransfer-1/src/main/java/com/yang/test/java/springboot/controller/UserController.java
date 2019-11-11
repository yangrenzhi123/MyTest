package com.yang.test.java.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.ScoreRecord;

@Controller
public class UserController {

	@Resource
	private UserDao userDao;

	private int sleep;

	@GetMapping("/sleep/{s}")
	@ResponseBody
	public void sleep(@PathVariable("s") Integer sle) throws InterruptedException {
		this.sleep = sle;
	}

	@GetMapping("/start/{s}")
	@ResponseBody
	public void hello(@PathVariable("s") Long start) throws InterruptedException {
		while (true) {
			int i = 0;
			try {
				if (sleep > 0) {
					Thread.sleep(sleep * 1000);
				}

				long a = System.currentTimeMillis();
				List<ScoreRecord> l = userDao.page(start);
				long aa = System.currentTimeMillis() - a;

				long b = System.currentTimeMillis();
				userDao.insertBatch(l);
				long bb = System.currentTimeMillis() - b;

				ScoreRecord last = l.get(l.size() - 1);

				System.out.println("查询耗时：" + aa + "，插入耗时：" + bb);
				System.out.println("数据条数：" + l.size());
				System.out.println("最后一条记录ID：" + last.getScorerecordzzid());

				if (l.size() == 0) {
					break;
				} else {
					start = last.getScorerecordzzid();
				}
			}catch(Exception e) {
				e.printStackTrace();
				if(i++ > 5) TestDingding.test("ScoreRecord，异常");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
				}
			}
		}
	}
	


	@GetMapping("/cleanETL/{start}")
	@ResponseBody
	public void cleanETL(@PathVariable("start") Long start) throws InterruptedException {
		while (true) {
			if (sleep > 0) {
				Thread.sleep(sleep * 1000);
			}

			long a = System.currentTimeMillis();
			List<ScoreRecord> l = userDao.page(start);
			long aa = System.currentTimeMillis() - a;

			long b = System.currentTimeMillis();
			userDao.cleanETL(l);
			long bb = System.currentTimeMillis() - b;

			ScoreRecord last = l.get(l.size() - 1);

			System.out.println("查询耗时：" + aa + "，更新耗时：" + bb);
			System.out.println("数据条数：" + l.size());
			System.out.println("单次耗时：" + (System.currentTimeMillis() - a));
			System.out.println("最后一条记录ID：" + last.getScorerecordzzid());
			System.out.println();

			if (l.size() < 5000) {
				break;
			} else {
				start = last.getScorerecordzzid();
			}
		}
	}
	
	

	


	@GetMapping("/updateHEtlScoreRecord/{start}")
	@ResponseBody
	public void updateHEtlScoreRecord(@PathVariable("start") Long start) throws InterruptedException {
		while (true) {
			if (sleep > 0) {
				Thread.sleep(sleep * 1000);
			}

			long a = System.currentTimeMillis();
			List<ScoreRecord> l = userDao.page(start);
			long aa = System.currentTimeMillis() - a;

			long b = System.currentTimeMillis();
			userDao.cleanETL(l);
			long bb = System.currentTimeMillis() - b;

			ScoreRecord last = l.get(l.size() - 1);

			System.out.println("查询耗时：" + aa + "，更新耗时：" + bb);
			System.out.println("数据条数：" + l.size());
			System.out.println("单次耗时：" + (System.currentTimeMillis() - a));
			System.out.println("最后一条记录ID：" + last.getScorerecordzzid());
			System.out.println();

			if (l.size() < 5000) {
				break;
			} else {
				start = last.getScorerecordzzid();
			}
		}
	}
}