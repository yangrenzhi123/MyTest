package com.yang.test.servlet.springmvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.util.NestedServletException;

public class TestFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (NestedServletException e) {
			Throwable cause = e.getCause();
			if (cause instanceof TipException) {
				PrintWriter w = response.getWriter();
				w.println("{a: 1}");
			} else {
				throw e;
			}
		}
	}

	public void destroy() {
	}
}