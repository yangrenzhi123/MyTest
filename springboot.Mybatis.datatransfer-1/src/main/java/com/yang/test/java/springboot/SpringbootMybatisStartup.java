package com.yang.test.java.springboot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yang.test.java.springboot.controller.TestDingding;
import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.RecyleRecord;
import com.yang.test.java.springboot.entity.ScoreRecord;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class SpringbootMybatisStartup {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisStartup.class, args);
	}

	public static final DateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
	
	@Resource
	private UserDao userDao;

	@Bean
	public Integer init2() {
		new Thread(new Runnable() {
			public void run() {
				long start = 22582883;
				int i = 0;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<RecyleRecord> l = userDao.limitRecycleRecord(start);
						long aa = System.currentTimeMillis() - a;

						if(l.size() == 0) {
							break;
						}
						

						Calendar d20200101 = Calendar.getInstance();
						d20200101.set(Calendar.YEAR, 2020);
						d20200101.set(Calendar.MONTH, 0);
						d20200101.set(Calendar.DAY_OF_MONTH, 1);
						long l20200101 = d20200101.getTime().getTime();
						Calendar d20190101 = Calendar.getInstance();
						d20190101.set(Calendar.YEAR, 2019);
						d20190101.set(Calendar.MONTH, 0);
						d20190101.set(Calendar.DAY_OF_MONTH, 1);
						long l20190101 = d20190101.getTime().getTime();
						
						Map<String, List<RecyleRecord>> dataGroup = new HashMap<>();
						dataGroup.put("2020", new ArrayList<RecyleRecord>());
						dataGroup.put("2018", new ArrayList<RecyleRecord>());
						for(RecyleRecord item : l) {
							if(item.getTfsj() != null) {
								if(item.getTfsj().getTime() >= l20200101) {
									dataGroup.get("2020").add(item);
								}else if(item.getTfsj().getTime() < l20190101) {
									dataGroup.get("2018").add(item);
								}else {
									String mongth = yyyyMM.format(item.getTfsj());
									List<RecyleRecord> lmonth = dataGroup.get(mongth);
									if(lmonth == null) {
										lmonth = new ArrayList<RecyleRecord>();
										dataGroup.put(mongth, lmonth);
									}
									lmonth.add(item);
								}
							}
						}

						long b = System.currentTimeMillis();
						Iterator<Map.Entry<String, List<RecyleRecord>>> iter = dataGroup.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry<String, List<RecyleRecord>> entry = (Map.Entry<String, List<RecyleRecord>>) iter.next();
							String month = (String) entry.getKey();
							List<RecyleRecord> lmonth = (List<RecyleRecord>) entry.getValue();
							if(lmonth != null && lmonth.size() > 0) {
								userDao.insertBatchRecycleRecord(lmonth, Integer.parseInt(month));
							}
						}
						long bb = System.currentTimeMillis() - b;

						RecyleRecord last = l.get(l.size() - 1);

						System.out.println("查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getRecyclerecordzzid());
						start = last.getRecyclerecordzzid();
					}catch(Exception e) {
						e.printStackTrace();
						if(i++ > 5) TestDingding.test("h_recycle_record，数据复制线程，异常");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
						}
					}
					
					i = 0;
				}
			}
		}, "h_recycle_record，数据复制线程").start();
		return 1;
	}

	//@Bean
	public Integer init() {
		new Thread(new Runnable() {
			public void run() {
				long start = 0;
				int i = 0;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<ScoreRecord> l = userDao.page(start);
						long aa = System.currentTimeMillis() - a;

						if(l.size() == 0) {
							break;
						}
						
						long b = System.currentTimeMillis();
						userDao.insertBatch(l);
						long bb = System.currentTimeMillis() - b;

						ScoreRecord last = l.get(l.size() - 1);

						System.out.println("查询耗时：" + aa + "，插入耗时：" + bb + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getScorerecordzzid());
						start = last.getScorerecordzzid();
					}catch(Exception e) {
						e.printStackTrace();
						if(i++ > 5) TestDingding.test("h_score_record，数据复制线程，异常");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
						}
					}
					
					i = 0;
				}
			}
		}, "h_score_record，数据复制线程").start();
		return 1;
	}
}