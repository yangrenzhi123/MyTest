package com.cql.tudou;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DubboConsumerConsumer implements WebMvcConfigurer {


    @Reference(version = "default")
    private DemoService demoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DubboConsumerConsumer.class, args);


        DemoService demoService = context.getBean(DemoService.class);
        System.out.println((demoService.sayHello("cql_test")));

    }
}
