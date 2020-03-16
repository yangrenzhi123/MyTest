package com.yang.test.java.spring.cloud.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {
	@Autowired
	private ZuulProperties properties;
	@Autowired
	ServerProperties server;

	@Bean
	public RouteLocator locator() {
		CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServlet().getPath(), this.properties);
		return routeLocator;
	}
}