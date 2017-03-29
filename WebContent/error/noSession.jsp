<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div>
<span id="sys_review">${msg}</span>
<script type="text/javascript" charset="utf-8">
try{
	parent.$.messager.progress('close');
}catch(e){
	alert("登录超时，程序即将退出~！");
	location.replace('/cxoa/app_login.jsp');
}
</script>
</div>
