<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%
	//注意此类处理方式只是对某些js类库有用，比如jquery、extjs等。
	response.setCharacterEncoding("utf-8");
	response.setStatus(500);
	PrintWriter writer = response.getWriter();
	writer.println("抱歉，发生系统错误，请稍后再试！");
	writer.flush();
	writer.close();
%>