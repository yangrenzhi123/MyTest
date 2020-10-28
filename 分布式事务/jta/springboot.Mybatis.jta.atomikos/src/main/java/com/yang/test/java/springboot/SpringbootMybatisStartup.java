package com.yang.test.java.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*GRANT XA_RECOVER_ADMIN ON *.* TO root@'%';
SHOW GRANTS FOR root@'%';*/
@SpringBootApplication
public class SpringbootMybatisStartup {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisStartup.class, args);
	}
}