<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>超星OA登陆</title>
<jsp:include page="inc.jsp"></jsp:include>
<style type="text/css">
body {
font-family:verdana,helvetica,arial,sans-serif;
 padding:20px;
 font-size:12px;
 margin:0;
 font-size:18px;}
 h2 {
 font-size:18px;
 font-weight:bold;
 margin:0;
 margin-bottom:15px;}
</style>
</head>
<body>
<div id="login_dialog" class="easyui-dialog" title="登录窗口" style="display:none;max-width:500px;padding:30px 40px;" data-options="modal:true,validate:true,closed:true">
    <form id="login_frm" method="post" action="user/login.action" autocomplete="true">
        <div style="margin-bottom:20px">
            <span id="login_err" style="color:red;margin-left: 86px;">${password_modify_error }</span><br/>
            <input id="email" class="easyui-textbox" name="email" style="width:280px;" data-options="label:'邮箱:',required:true,validType:'email'">
            <input type="checkbox" onclick = "if($(this).prop('checked')){
                var user = {};
                user.email = $('#email').val();
                if(user.email && user.email!=''){
                	localStorage.user=JSON.stringify(user)
                }
            }else{
                localStorage.user = '';
            }
            ">
            <div style="display: inline-block;color: #7b7272;font-size: 10px;margin-left: -3px;">:记住邮箱</div><br/>
            <span style="color:red;margin-left: 86px;">${user_login_error }</span>
        </div>
        <div style="margin-bottom:20px">
            <input id="pass" name="password" class="easyui-passwordbox" prompt="密码..." style="width:280px;" data-options="label:'密码:',required:true"><br/>
            <span style="color:red;margin-left: 86px;">${password_login_error }</span>
        </div>
        <div style="margin-bottom:20px;display: none;" name="new_paasword">
            <input id="newpass" name="newpassword" class="easyui-passwordbox" prompt="新密码..." style="width:280px;" data-options="label:'新密码:',required:true">
        </div>
        <div style="margin-bottom:20px;display: none;" name="new_paasword">
            <input id="newpass2" name="newpassword2" class="easyui-passwordbox" prompt="确认新密码..." validType="confirmPass['#newpass']" style="width:280px;" data-options="label:'确认新密码:',required:true,
             onChange:function(){
                var newpass = $('#newpass').val();
                var newpass2 = $('#newpass2').val();
                if(!newpass || newpass.length<8 || newpass!==newpass2){
                $('#mmConfirm').html('两次输入的密码不匹配,或者密码长度小于8位！~');
                }else{
                $('#mmConfirm').html(' ');
                }
                }
            "><br/>
            <span id="mmConfirm" style="color:red;margin-left: 86px;"></span>
        </div>
        <%--<div style="display: inline-block;">--%>
            <%--<input type="checkbox" onclick = "if($(this).prop('checked')){localStorage.user}">--%>
            <%--<div style="display: inline-block;color: #7b7272;font-size: 10px;margin-left: -3px;">:记住账号</div>--%>
        <%--</div>--%>
        <div style="margin:0px auto;padding-left: 80px;">
            <input id="login_btn" style="font-size: 12px;color: #4e434a;" type="button" value="登录" onclick="submit()">
            <input style="margin-left:20px;font-size: 12px;color: #4e434a;" type="button" value="重置" onclick="$('#login_frm').form('clear')">
            <input style="margin-left:20px;font-size: 12px;color: #4e434a;" type="button" value="修改密码" onclick="modifyPassword()">
        </div>
    </form>
</div>
<script type="text/javascript">
    <%--$(document).ready(function() {--%>
    <%--$('#login_dialog').dialog('open');--%>
    <%--$('#login_frm').keydown(function(e){--%>
    <%--if(e.keyCode==13){--%>
    <%--$('#login_btn').trigger('click');--%>
    <%--}--%>
    <%--});--%>
    <%--})--%>
    window.onload = function(){
    $('#login_dialog').dialog('open');
    $('#login_frm').keydown(function(e){
    if(e.keyCode==13){
    $('#login_btn').trigger('click');
    }
    });
    if(!(/chrome/.test(window.navigator.userAgent.toLowerCase()))){
    alert("当前系统不支持该浏览器，请选择chrome（谷歌）浏览器登录！");
    }else{
    $('#dlg').dialog({modal: true});
    if(localStorage.user && localStorage.user != ''){
    $('#login_frm').form('load',JSON.parse(localStorage.user));
    }
    }
    }
function submit(){
    var newpass = $("#newpass").val();
    var newpass2 = $("#newpass2").val();
    if($('#login_btn').val()=='提交'){
	    if(!newpass || newpass.length<8 || newpass!==newpass2){
	        $('#mmConfirm').html('两次输入的密码不匹配,或者密码长度小于8位！~');
	        return false;
	    }
    }
    if($('[name="email"]')[0].val()==''||$('[name="password"]')[0].val()=='password'){
    	$('#mmConfirm').html('账号或密码不能为空！~');
    	return false;
    }
    $('#login_frm').submit();
    //邮箱验证
   // /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);

}
function modifyPassword(){
    var newpass =  $('[name="new_paasword"]');
    if($('#login_btn').val() == '提交'){
        $('#login_frm').attr('action','user/login.action');
        $.each(newpass,function(n, obj){
            $(obj).css("display","none");
        })
        $('#login_btn').val('登录');
    }else{
        $('#login_frm').attr('action','user/modifyPassword.action');
        $.each(newpass,function(n, obj){
            $(obj).css("display","");
        })
        $('#login_btn').val('提交');
    }
}
</script>
</body>
</html>