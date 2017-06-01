<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String version = "1.0.5";
	String v = "?v=" + System.currentTimeMillis();
%>
<!DOCTYPE html>
<html>
<head>
<title>超星自动化办公系统</title>
<meta HTTP-EQUIV="Pragma" content="no-cache">
<meta HTTP-EQUIV="Cache-Control" content="no-cache,must-revalidate">
<meta HTTP-EQUIV="Expires" content="0">
<meta charset="UTF-8">
<jsp:include page="inc.jsp"></jsp:include>
 <!-- 自己定义的样式和JS扩展 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/oaCss<%=version %>.css<%=v %>" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/util<%=version %>.js<%=v %>" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/app<%=version %>.js<%=v %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/north<%=version %>.js<%=v %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/west<%=version %>.js<%=v %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/center/employee<%=version %>.js<%=v %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/system<%=version %>.js"<%=v %>></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/wagesCalculate<%=version %>.js<%=v %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsController/center/hetong<%=version %>.js<%=v %>"></script>
<script type="text/javascript">
$(document).ready(function () {
	init();
/* 	initWestTree(); */
});
</script>
<!-- 登陆窗口  -->
<c:if test="${sessionInfo == null }">
		<c:redirect url="login.jsp"></c:redirect>
</c:if>
<%-- 	<c:if test="${sessionInfo != null}"> --%>
		<%-- <jsp:include page="layout/components/logindialog.jsp"></jsp:include> --%>
		<script type="text/javascript">
		window.handler = {};
		var handler = window.handler;
		handler = employee;
		session.logined = true;
		session.user.ID = '${sessionInfo.id }';
		session.user.username = '${sessionInfo.username }';
		session.user.email = '${sessionInfo.email}';
		session.user.roleId = '${sessionInfo.roleId}';
		session.user.departmentId = '${sessionInfo.departmentId}';
		session.user.position = '${sessionInfo.position}';
		session.user.sex = '${sessionInfo.sex}';
		session.user.phoneNumber = '${sessionInfo.phoneNumber}';
		session.user.email = '${sessionInfo.email}';
		initClickHandler();
		</script> 
	  	<!-- $(document).ready(function () {
			$('#login_dlg').dialog('close');
		 	if(!session.logined){
				$('#main_body').css('display','none');
				$('#login_dlg').dialog('open');
				$('#login_dlg').dialog({modal: true});
			}else{
				$('#main_body').css('display','');
				$('#login_dlg').dialog('close');
			} 
			}) -->
	 <%-- </c:if> --%>
</head>
<body id = "body" class="easyui-layout">
	<div id="main_body" class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north.jsp'" style="height: 60px;overflow: hidden;" class="logo"></div>
		
		<div data-options="region:'west',title:'功能菜单',href:'${pageContext.request.contextPath}/layout/west.jsp'" style="width: 200px;overflow: hidden;"></div>
		
		<div data-options="region:'center',href:'${pageContext.request.contextPath}/layout/center.jsp'" style="overflow: hidden;"></div>
		
		<%-- <div data-options="region:'east',title:'日历',split:true" style="width: 200px;overflow: hidden;">
			<jsp:include page="layout/east.jsp"></jsp:include>
		</div> --%>
		
		<div data-options="region:'south',href:'${pageContext.request.contextPath}/layout/south.jsp'" style="height: 27px;overflow: hidden;"></div>
	</div>
	
</body>
</html>