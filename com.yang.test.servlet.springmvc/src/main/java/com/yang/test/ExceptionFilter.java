package com.yang.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.util.NestedServletException;

public class ExceptionFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (NestedServletException e) {
			if (e instanceof TipException) {
				TipException tip = (TipException) e;
				String s;
				if (tip.getCode() != null) {
					s = "{\"code\":\"" + tip.getCode() + "\",\"result\":\"" + e.getMessage() + "\"}";
				} else {
					s = "{\"code\":\"200001\",\"result\":\"" + e.getMessage() + "\"}";
				}
				response.getOutputStream().write(s.getBytes("utf-8"));
				response.setContentType("application/json;charset=UTF-8");
			} else {
				throw e;
			}
		}
	}

	public void destroy() {

	}
}