package com.example.demo;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;

@Configuration
@RefreshScope
public class FeignHeadConfiguration {
	

	@Value("${ser.name}")
	private String words;
	
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
        	ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        	
        	if(attrs != null) {
            	HttpServletRequest request = attrs.getRequest();
            	
            	System.out.println("request hash code:"+request.hashCode());
        	}

        	
            requestTemplate.header("headerId2", UUID.randomUUID().toString());
        };
    }
}