package com.example.demo;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

@Configuration
@RefreshScope
public class FeignHeadConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

			if (attrs != null) {
				System.out.println("-------------------------------------------------------------------");
				
				
				HttpServletRequest request = attrs.getRequest();
				String hd_flag = request.getHeader("hd_flag");
				if ("1".equals(hd_flag)) {
					RibbonFilterContextHolder.getCurrentContext().add("lancher", "1");
					requestTemplate.header("hd_flag", "1");
				}/*else {
					RibbonFilterContextHolder.getCurrentContext().add("lancher", "0");
				}*/
			}

			requestTemplate.header("headerId2", UUID.randomUUID().toString());
		};
	}
}