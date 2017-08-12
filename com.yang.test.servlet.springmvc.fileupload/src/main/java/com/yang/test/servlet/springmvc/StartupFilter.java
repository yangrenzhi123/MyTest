package com.yang.test.servlet.springmvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class StartupFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		String cmd = "explorer \"http://localhost:8080\"";
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}