package com.yang.test.java.spring.cloud.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication  
@EnableScheduling  
@EnableAdminServer  
@EnableDiscoveryClient
public class SvcMonitorApplication {  
    public static void main(String[] args) {  
        SpringApplication.run(SvcMonitorApplication.class, args);  
    }  
} 