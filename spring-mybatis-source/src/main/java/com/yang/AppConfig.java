package com.yang;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.yang")
@Import(LubanBeanDefinitionRegister.class)
public class AppConfig {

}