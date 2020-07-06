package com.yang.test.java.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.yang.test.java.springboot.TestDingding;
import com.yang.test.java.springboot.dao1.UserDao1;
import com.yang.test.java.springboot.dao2.UserDao2;

import entity.DispenserReplenish;
import entity.ExchangeScoreRecord;
import entity.GarbagebagPull;
import entity.InspectRecord;
import entity.RecycleLoseweight;
import entity.RecyleRecord;
import entity.ReplenishContent;
import entity.ScoreRecord;

@Controller
public class UserController {

	@Resource
	UserDao1 userDao1;
	@Resource
	UserDao2 userDao2;

	@Bean
	public Integer init() {
		new Thread(new Runnable() {
			public void run() {
				long start = 240612513;
				while (true) {
					int i = 0;
					try {
						long a = System.currentTimeMillis();
						List<ScoreRecord> l = userDao1.page(start);
						long aa = System.currentTimeMillis() - a;

						if(l.size() == 0) {
							break;
						}
						
						List<ScoreRecord> ll = new ArrayList<ScoreRecord>();
						for(ScoreRecord item : l) {
							if(item.getEtl() == null) {
								ll.add(item);
							}
						}

						ScoreRecord last = l.get(l.size() - 1);
						
						if(ll.size() == 0) {
							start = last.getScorerecordzzid();
							continue;
						}
						
						long b = System.currentTimeMillis();
						userDao2.updateBatch(ll);
						long bb = System.currentTimeMillis() - b;

						System.out.println("ScoreRecord，查询耗时：" + aa + "，插入耗时：" + bb + "，查询条数：" + l.size() + "，处理条数：" + ll.size() + "，最后一条记录ID：" + last.getScorerecordzzid());
						start = last.getScorerecordzzid();
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
		}, "ScoreRecord，ETL字段NULL修改为0线程");//.start();
		
		new Thread(new Runnable() {
			public void run() {
				long start = 18565587;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<ScoreRecord> l = userDao1.page(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatch(l);
						long bb = System.currentTimeMillis() - b;

						ScoreRecord last = l.get(l.size() - 1);

						System.out.println("ScoreRecord，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getScorerecordzzid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getScorerecordzzid();
						}
					}catch(Exception e) {
						TestDingding.test("ScoreRecord，异常");
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e1) {
						}
					}
				}
			}
		}, "ScoreRecord数据转移线程");//.start();

		new Thread(new Runnable() {
			public void run() {
				long start = 0;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<RecyleRecord> l = userDao1.limitRecyleRecord(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatchRecyleRecord(l);
						long bb = System.currentTimeMillis() - b;

						RecyleRecord last = l.get(l.size() - 1);

						System.out.println("RecyleRecord，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getRecyclerecordzzid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getRecyclerecordzzid();
						}
					}catch(Exception e) {
						TestDingding.test("RecyleRecord，异常");
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e1) {
						}
					}
				}
			}
		}, "RecyleRecord数据转移线程");//.start();

		new Thread(new Runnable() {
			public void run() {
				long start = 108775711;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<RecycleLoseweight> l = userDao1.limitRecycleLoseweight(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatchRecycleLoseweight(l);
						long bb = System.currentTimeMillis() - b;

						RecycleLoseweight last = l.get(l.size() - 1);

						System.out.println("RecycleLoseweight，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getRecycleloseweightzzid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getRecycleloseweightzzid();
						}
					}catch(Exception e) {
						TestDingding.test("RecycleLoseweight，异常");
					}
				}
			}
		}, "RecycleLoseweight数据转移线程");//.start();

		new Thread(new Runnable() {
			public void run() {
				long start = 0;
				while (true) {
					int i = 0;
					try {
						long a = System.currentTimeMillis();
						List<GarbagebagPull> l = userDao1.limitGarbagebagPull(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatchGarbagebagPull(l);
						long bb = System.currentTimeMillis() - b;

						GarbagebagPull last = l.get(l.size() - 1);

						System.out.println("GarbagebagPull，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getGarbagebagpullzzid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getGarbagebagpullzzid();
						}
					}catch(Exception e) {
						e.printStackTrace();
						if(i++ > 5) TestDingding.test("GarbagebagPull，异常");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
						}
					}
				}
			}
		}, "GarbagebagPull数据转移线程");//.start();

		new Thread(new Runnable() {
			public void run() {
				long start = 0;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<InspectRecord> l = userDao1.limitInspectRecord(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatchInspectRecord(l);
						long bb = System.currentTimeMillis() - b;

						InspectRecord last = l.get(l.size() - 1);

						System.out.println("InspectRecord，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getInspectrecordzzid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getInspectrecordzzid();
						}
					}catch(Exception e) {
						TestDingding.test("InspectRecord，异常");
					}
				}
			}
		}, "InspectRecord数据转移线程");//.start();

		new Thread(new Runnable() {
			public void run() {
				long start = 96181570;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<DispenserReplenish> l = userDao1.limitDispenserReplenish(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatchDispenserReplenish(l);
						long bb = System.currentTimeMillis() - b;

						DispenserReplenish last = l.get(l.size() - 1);

						System.out.println("DispenserReplenish，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getDispenserreplenishzzid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getDispenserreplenishzzid();
						}
					}catch(Exception e) {
						TestDingding.test("DispenserReplenish，异常");
					}
				}
			}
		}, "DispenserReplenish数据转移线程");//.start();
		
		new Thread(new Runnable() {
			public void run() {
				long start = 0;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<ReplenishContent> l = userDao1.limitReplenishContent(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatchReplenishContent(l);
						long bb = System.currentTimeMillis() - b;

						ReplenishContent last = l.get(l.size() - 1);

						System.out.println("ReplenishContent，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getReplenishcontentid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getReplenishcontentid();
						}
					}catch(Exception e) {
						TestDingding.test("DispenserReplenish，异常");
					}
				}
			}
		}, "ReplenishContent数据转移线程");//.start();
		
		new Thread(new Runnable() {
			public void run() {
				long start = 0;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<ExchangeScoreRecord> l = userDao1.limitExchangeScoreRecord(start);
						long aa = System.currentTimeMillis() - a;

						long b = System.currentTimeMillis();
						userDao2.insertBatchExchangeScoreRecord(l);
						long bb = System.currentTimeMillis() - b;

						ExchangeScoreRecord last = l.get(l.size() - 1);

						System.out.println("ExchangeScoreRecord，查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getExchangescorerecordzzid());

						if (l.size() < 10000) {
							break;
						} else {
							start = last.getExchangescorerecordzzid();
						}
					}catch(Exception e) {
						TestDingding.test("ExchangeScoreRecord，异常");
					}
				}
			}
		}, "ExchangeScoreRecord数据转移线程").start();
		return 1;
	}
}