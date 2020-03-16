package com.yang.test.java.spring.cloud.zuul.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

	@SuppressWarnings("unused")
	private ZuulProperties properties;

	public CustomRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
		this.properties = properties;
	}

	@Override
	public void refresh() {
		doRefresh();
	}

	@Override
	protected Map<String, ZuulRoute> locateRoutes() {
		LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
		// 从配置文件中加载路由信息
		routesMap.putAll(super.locateRoutes());
		// 从db中加载路由信息
		routesMap.putAll(locateRoutesFromDB());
		return routesMap;
	}

	private Map<String, ZuulRoute> locateRoutesFromDB() {
		Map<String, ZuulRoute> routes = new LinkedHashMap<>();
		return routes;
	}
}
