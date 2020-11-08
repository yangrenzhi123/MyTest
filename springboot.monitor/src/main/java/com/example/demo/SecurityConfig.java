//package com.example.demo;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()// 该方法所返回的对象的方法来配置请求级别的安全细节
//				.antMatchers("/login").permitAll()// 登录页面不拦截
//				.antMatchers("/actuator/**").permitAll()// 登录页面不拦截
//				.antMatchers(HttpMethod.POST, "/checkLogin").permitAll().anyRequest().authenticated();// 对于登录路径不进行拦截
//	}
//}