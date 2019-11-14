package com.yang.test.java.springboot;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yang.test.java.springboot.controller.TestDingding;
import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.ScoreRecord;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class SpringbootMybatisStartup {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisStartup.class, args);
	}

	@Resource
	private UserDao userDao;

	@Bean
	public Integer init() {
		new Thread(new Runnable() {
			public void run() {
				long start = 120000000;
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