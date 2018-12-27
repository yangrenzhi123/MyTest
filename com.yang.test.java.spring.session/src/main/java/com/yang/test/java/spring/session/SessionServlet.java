package com.yang.test.java.spring.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionServlet extends HttpServlet {

	private static final long serialVersionUID = 2878267318695777395L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object v = req.getSession().getAttribute("Test");
		if (v == null) {
			System.out.println("setting sessions");
			req.getSession().setAttribute("Test", "Test.Yang");
		}
		System.out.println(v);
	}
}
