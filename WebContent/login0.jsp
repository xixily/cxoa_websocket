<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>超星OA登陆</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function () {
	 if(!(/chrome/.test(window.navigator.userAgent.toLowerCase()))){
		/* $.messager.alert("浏览器兼容提示"," 当前系统不支持该浏览器，请选择chrome浏览器登录！"); */
		alert("当前系统不支持该浏览器，请选择chrome浏览器登录！");
     }else{
    	 $('#dlg').dialog({modal: true});
     }	
})
</script>
</head>
<body>
<div id="dlg" class="easyui-dialog" title="登陆窗口" data-options="iconCls:'icon-save',close:true" style="width:400px;padding:10px;">
    <div style="padding:10px 60px 20px 60px">
        <form id="login_frm" method="post" action="user/login.action">
            <table cellpadding="8">
                <tr>
                    <td>登陆邮箱:</td>
                    <td><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email'"><span style="color:red">${user_login_error }</span></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input name="password" class="easyui-textbox" type="password"><span style="color:red">${password_login_error }</span></td>
                </tr>
            </table>
            <input style="margin-left:40px;"  type="submit" value="登陆">
            <input style="margin-left:80px;" type="button" value="重置" onclick="$('#login_frm').form('clear')">
            <br>
        </form>
    </div>
</div>
</body>
</html>