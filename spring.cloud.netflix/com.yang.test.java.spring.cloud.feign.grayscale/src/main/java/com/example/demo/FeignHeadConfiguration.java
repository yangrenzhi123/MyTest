package com.example.demo;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
				HttpServletRequest request = attrs.getRequest();
				String apiVersion = request.getHeader("api-version");
				if (StringUtils.isNotEmpty(apiVersion)) {
					RibbonFilterContextHolder.getCurrentContext().add("api-version", apiVersion);
					requestTemplate.header("api-version", apiVersion);
				}else {
					RibbonFilterContextHolder.getCurrentContext().add("api-version", "0");
					requestTemplate.header("api-version", "0");
				}
			}

			requestTemplate.header("headerId2", UUID.randomUUID().toString());
		};
	}
}