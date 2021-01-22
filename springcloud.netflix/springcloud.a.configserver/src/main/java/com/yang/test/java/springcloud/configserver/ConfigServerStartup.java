package com.yang.test.java.springcloud.configserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import de.codecentric.boot.admin.client.config.SpringBootAdminClientAutoConfiguration;

@SpringBootApplication // (exclude = { SpringBootAdminClientAutoConfiguration.class })
@EnableConfigServer
public class ConfigServerStartup {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerStartup.class, args);
	}
}

@RestController
class TestController {

	@Autowired
	SampleBean sampleBean;

	// 请求一次，指标累加一次
	@RequestMapping("/")
	public String index() throws Exception {
		sampleBean.handleMessage();
		return "success";
	}
}