<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String use_agent = request.getHeader("user-agent");
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	//if(use_agent.contains("Android") || use_agent.contains("iOS")){
	if(true){
		response.sendRedirect("http://learn.chaoxing.com/apis/user/currentUser?backurl="+basePath+"public/user/applogin.action");
		}
%>
<html>
<body>
<%=basePath+"desktop.do" %>
<%="设备信息："+use_agent%>
</body>
</html>