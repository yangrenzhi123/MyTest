package com.yang.test.java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.test.java.springboot.dao.UserDao;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class SpringbootMybatisStartup {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisStartup.class, args);
	}
}

@Controller
class HelloController {

	@Autowired
	UserDao userDao;

	@RequestMapping("/")
	@ResponseBody
	public long index() {
		return userDao.selectByPrimaryKey();
	}
}