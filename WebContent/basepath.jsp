<%@ page language="java" pageEncoding="UTF-8"%><%
	String path = request.getContextPath();
	String use_agent = request.getHeader("user-agent");
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	String path_noScheme = request.getServerName()+":"+request.getServerPort()+path+"/"; 
/* 	//if(use_agent.contains("Android") || use_agent.contains("iOS")){
	if(true){
		response.sendRedirect("http://learn.chaoxing.com/apis/user/currentUser?backurl="+basePath+"public/user/applogin.action");
		} */
%>{"basePath_noScheme":"<%=path_noScheme %>","use_agent":"<%=use_agent %>","bashPath":"<%=basePath %>"}
