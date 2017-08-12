package com.yang.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 8979576929887359402L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/works.jsp");
		String name = "tom";
		req.setAttribute("id", name);// ´æÖµ
		rd.forward(req, response);
	}
}