package com.yang.test.java.spring.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

	private static final long serialVersionUID = 2878267318695777395L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(req.getSession().getAttribute("Test"));
		req.getSession().setAttribute("Test", "Test.Yang");
		resp.sendRedirect(req.getContextPath() + "/");
	}
}
