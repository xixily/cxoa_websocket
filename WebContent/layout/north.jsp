<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
	<c:if test="${sessionInfo != null}">[<strong>${sessionInfo.username}</strong>]，欢迎您！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！</c:if>
</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'icon-back'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; ">
	<div onclick="changeTheme('default');">default</div>
	<div onclick="changeTheme('gray');">gray</div>
	<div onclick="changeTheme('black');">black</div>
	<div onclick="changeTheme('bootstrap');">bootstrap</div>
	<div onclick="changeTheme('metro');">metro</div>
	<div onclick="changeTheme('material');">material</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; ">
	<div class="menu-sep"></div>
	<div onclick="north.lock(true);">锁定系统</div>
	<div onclick="north.modifyPassword(false);">修改密码</div>
	<div onclick="north.logoutFun(true);">退出系统</div>
</div>
<div id="lock_sys_dialog" class="easyui-dialog" title="解锁" style="width:360px;padding:50px 60px" data-options = "modal:true,closed:true,closable:false">
	<form id="unlock_login_form">
	<div style="margin-bottom:20px">
	<input id="pass" name="password" class="easyui-passwordbox" prompt="密码..." style="width:305px;" data-options="label:'请输入密码解锁:',required:true">
	</div>
	</form>
	<div style="text-align:center">
	<span id="unlock_button" class="easyui-linkbutton" onclick="north.lock(false)" style="width:80px;">解锁</span>
	<span class="easyui-linkbutton" onclick="$('#unlock_login_form').form('clear')" style="width:80px;">重置</span>
	</div>
</div>

<div id="login_dialog" class="easyui-dialog" title="修改密码窗口" style="max-width:420px;padding:30px 40px;" data-options="closed:true,modal:true,validate:true">
	<form id="login_frm" method="post" action="user/modifyPass.action">
	<div style="margin-bottom:20px">
	<input id="email" name="email" class="easyui-textbox" prompt="邮箱..." style="width:280px;" data-options="label:'邮箱:',required:true,readonly:true">
	<br/><span id="_email" style="color:red;"></span>
	</div>
	<div style="margin-bottom:20px">
	<input id="pss" name="password" class="easyui-passwordbox" prompt="密码..." style="width:280px;" data-options="label:'密码:',required:true">
	<br/><span id="relmm" style="color:red;"></span>
	</div>
	<div style="margin-bottom:20px;" name="new_paasword">
	<input id="newpass" name="newpassword" class="easyui-passwordbox" prompt="新密码..." style="width:280px;" data-options="label:'新密码:',required:true">
	</div>
	<div style="margin-bottom:20px;" name="new_paasword">
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
	<span id="mmConfirm" style="color:red;"></span>
	</div>
	<div style="text-align:center;padding:5px 0">
	<a id="log_btn" href="javascript:void(0)" class="easyui-linkbutton" onclick="north.modifyPassword(true);" style="width:80px">确认修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#login_frm').form('clear');$('#login_frm').form('load',{email:session.user.email});" style="width:60px">重置</a>
	</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#unlock_login_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#unlock_button').trigger('click');
		}
		});
	$('#login_frm').keydown(function(e){
		if(e.keyCode==13){
		   $('#log_btn').trigger('click');
		}
		});
})
</script>