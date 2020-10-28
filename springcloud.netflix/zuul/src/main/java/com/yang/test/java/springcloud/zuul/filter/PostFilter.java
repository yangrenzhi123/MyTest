package com.yang.test.java.springcloud.zuul.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Enumeration<String> headerNames = request.getHeaderNames();
		long length = 0;
		while (null != headerNames && headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			System.out.println(key + "：" + key.length() + "，" +value.length());
			length = length + key.length() + value.length();
		}
		length = length + request.getContentLengthLong();
		System.out.println("content-length：" + request.getContentLengthLong());
		System.out.println("流量：" + length);
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 1;
	}
}