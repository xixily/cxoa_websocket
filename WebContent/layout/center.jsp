<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="tabs" class="easyui-tabs" style="width: 100%; height: 100%">
	<!-- 设置默认页  -->
	<c:if test="${sessionInfo != null}">
	<jsp:include page="../tabs/indexTab.jsp"></jsp:include>
<%-- 	<jsp:include page="../tabs/renshiUserName.jsp"></jsp:include> --%>
	</c:if>
</div>
