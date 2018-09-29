package com.zyxk.sevice.check.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ExceptionFilter implements Filter {

	private int serviceCode;

	public ExceptionFilter(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
		try {
			chain.doFilter(request, response);
		} catch (TipException e) {
			String s = "{\"code\":\"" + e.getCode() + "\",\"result\":\"" + e.getMessage() + "\"}";
			response.getOutputStream().write(s.getBytes("utf-8"));
		} catch (Exception e) {
			String s = "{\"code\":\"" + serviceCode + "999999\",\"result\":\"" + e.getMessage() + "\"}";
			response.getOutputStream().write(s.getBytes("utf-8"));
		} finally {
			response.setContentType("application/json;charset=UTF-8");
		}
	}

	public void destroy() {

	}
}