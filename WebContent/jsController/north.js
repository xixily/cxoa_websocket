var north = {
	logoutFun : function() {
		//			jQuery.getJSON(url,[data],[success(data,status,xhr)]) 
		//			一个ajax请求(http get 请求)，url请求路径，data请求参数,success()回调
		session = {};
		$.getJSON('user/logout.action', function(result) {
//			console.log('======>>> o%', result);
			if (result.success) {
				console.log('登出成功！');
				location.replace('/cxoa');
			} else {
				console.log('登出成功！');
				location.replace('/cxoa');
			}
		});
	},
	userInfoFun : function() {
		//生成一个div节点
		$('<div/>').dialog({
			href : 'user/userInfo.action',
			width : 490,
			height : 285,
			modal : true,
			title : '用户信息',
			buttons : [ {
				text : '修改密码',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#user_userInfo_form').form('submit', {
						url : 'userController/modifyCurrentUserPwd.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
			}
		});
	},
	lock : function(flage){
		if(flage){
			$('#unlock_login_form').form('clear');
			$.getJSON('user/logout.action',null,function(result){
				if(result.success){
					$('#lock_sys_dialog').dialog('open',{modal:true});
				}

//				$.messager.alert("加锁提示：",result.msg);
			})
		}else{
			var formResult = getDataOfForm($('#unlock_login_form'));
			if(formResult.succeed){
				var data = formResult.data;
				data.email = session.user.email;
				if(data.password && data.email){
					$.getJSON('user/login.action',data,function(result){
						if (result.success) {
							$('#lock_sys_dialog').dialog('close');
						}
						$.messager.alert("解锁提示：",result.msg);
					})
				}
			}
		}
	},
modifyPassword : function(flag){
	if(!flag){
		$('#login_frm').form('clear');
		$('#login_frm').form('load',{email:session.user.email});
		$('#login_dialog').dialog('open');
	}else{
		$('#login_frm').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				result = eval("("+result+")");
				if(result.success){
					$.messager.alert('更新提示：',result.msg);
					location.replace('/cxoa');
				}
				$.messager.alert('更新提示：',result.msg);
			}
		});
	}
	}
}