<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionInfo != null}">
<script type="text/javascript">
$(document).ready(function () {
	if(session.logined){
		west.initWestTree();
	}else{
		north.logoutFun();
	}
/* 	initWestTree(); */
});
</script>
</c:if>
<div id = "menu" class="easyui-accordion" data-options="fit:true,border:false">
</div>