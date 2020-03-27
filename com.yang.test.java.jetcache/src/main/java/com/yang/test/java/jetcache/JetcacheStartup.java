package com.yang.test.java.jetcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.lyzh.msa.framework.common.dto.console.ReceiveBagStatisticalResponseDTO;
import com.yang.test.java.jetcache.api.UserService;

@SpringBootApplication
@EnableMethodCache(basePackages = "com.yang.test.java.jetcache")
@EnableCreateCacheAnnotation
public class JetcacheStartup {
	public static void main(String[] args) {
		SpringApplication.run(JetcacheStartup.class);
	}
}

@RestController
class HelloController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String hello2() {
		return userService.getUserById("test");
	}

	@GetMapping("/test")
	public void test() {
		ReceiveBagStatisticalResponseDTO a = userService.findRegionDTOOne();
		System.out.println(a);
		System.out.println(a.getReceiveBagFamilyCount() + "，" + a.getReceiveBagCount());
	}
}