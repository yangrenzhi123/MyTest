package com.yang.test.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import com.yang.test.jpa.dao.TaskDao;
import com.yang.test.jpa.domain.Task;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 8979576929887359402L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = req.getSession().getServletContext();
		WebApplicationContext webApplicationContext = (WebApplicationContext) context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/works.jsp");
		
		TaskDao t = (TaskDao)webApplicationContext.getBean("taskDao");
		List<Task> l = t.findTop10();
		
		req.setAttribute("l", l);// ��ֵ
		rd.forward(req, response);
	}
}