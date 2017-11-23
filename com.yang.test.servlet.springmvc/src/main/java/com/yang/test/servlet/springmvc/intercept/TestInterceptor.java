package com.yang.test.servlet.springmvc.intercept;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yang.test.servlet.springmvc.Test;

public class TestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		
		HandlerMethod m = (HandlerMethod) handler;
		Class s = handler.getClass();
		Annotation[] ann3 =  m.getMethod().getDeclaredAnnotationsByType(Test.class);
		Annotation[] ann2 =  m.getMethod().getAnnotationsByType(Test.class);
		Annotation[] ann = m.getBeanType().getAnnotations();
		for(Annotation item : ann){
			System.out.println(item);
		}
		return true;
	}
}