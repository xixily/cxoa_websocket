var employee = {
	employee : {
		quickQuery : function(flage){
			if(flage){
				var params = {};
				params.type = flage;
				$('#employee_datas').datagrid({
					url : 'employee/quickQuery.action',
					queryParams : params
				})
			}
//			$.post(url,null,function(result){
//				result = eval("(" + result + ")");
//			});
		/*	if(flage == 111|| flage==112 ||flage==113||flage==211||flage==212){
				params.type = 'wages';
				if(flage==111){
					params.salary = 0;
				}
			}*/
		},
		exportExcel : function(type) {
			console.log('exportExcel:' + type);
			var exportParam = {};
			var form = $('#renshiquery_form');
			var data = getDataOfForm(form);
			downloadForm.createForm();
			var url = "file/exportEmployeeExcel.action";
			var mydate = new Date();
			var year = mydate.getFullYear(); // 获取完整的年份(4位,1970-????)
			var month = mydate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
			var monthb = month - 1;
			var montha = month + 1;
			var date = '' + year + (month < 10 ? ('0' + month) : month);
			var dateBefore ='' + year + (monthb < 10 ? ('0' + monthb) : monthb);
			var dateAfter ='' + year + (montha < 10 ? ('0' + montha) : montha);
			exportParam.configurable = 'reportForm';
			if (!type || type == 0) {
				delete exportParam.configurable;
				if (data.succeed) {
					exportParam = data.data;
				}
			} else if (type == 111 || type == 112 || type == 113 || type == 121
					|| type == 122 || type == 123) {
				exportParam.configurable = 'ruzhiReport';
				if (type == 111) {
					exportParam.configurable_value = dateBefore;
					exportParam.levelc = "非实习生";
				} else if (type == 112) {
					exportParam.configurable_value = date;
					exportParam.levelc = "非实习生";
				} else if (type == 113) {
					exportParam.configurable_value = dateAfter;
					exportParam.levelc = "非实习生";
				} else if (type == 121) {
					exportParam.configurable_value = dateBefore;
					exportParam.levelc = "实习生";
				} else if (type == 122) {
					exportParam.configurable_value = date;
					exportParam.levelc = "实习生";
				} else {
					exportParam.configurable_value = dateAfter;
					exportParam.levelc = "实习生";
				}
			} else if (type == 211 || type == 212 || type == 213 || type == 221
					|| type == 222 || type == 223) {
				exportParam.configurable = 'zhuanzhengReport';
				if (type == 211) {
					exportParam.configurable_value = dateBefore;
					exportParam.levelc = "非实习生";
				} else if (type == 212) {
					exportParam.configurable_value = date;
					exportParam.levelc = "非实习生";
				} else if (type == 213) {
					exportParam.configurable_value = dateAfter;
					exportParam.levelc = "非实习生";
				} else if (type == 221) {
					exportParam.configurable_value = dateBefore;
					exportParam.levelc = "实习生";
				} else if (type == 222) {
					exportParam.configurable_value = date;
					exportParam.levelc = "实习生";
				} else {
					exportParam.configurable_value = dateAfter;
					exportParam.levelc = "实习生";
				}
			} else if (type == 311 || type == 312 || type == 313 || type == 321
					|| type == 322 || type == 323) {
				exportParam.configurable = 'lizhiReport';
				if (type == 311) {
					exportParam.configurable_value = dateBefore;
					exportParam.levelc = "非实习生";
				} else if (type == 312) {
					exportParam.configurable_value = date;
					exportParam.levelc = "非实习生";
				} else if (type == 313) {
					exportParam.configurable_value = dateAfter;
					exportParam.levelc = "非实习生";
				} else if (type == 321) {
					exportParam.configurable_value = dateBefore;
					exportParam.levelc = "实习生";
				} else if (type == 322) {
					exportParam.configurable_value = date;
					exportParam.levelc = "实习生";
				} else {
					exportParam.configurable_value = dateAfter;
					exportParam.levelc = "实习生";
				}
			} else if (type == 411 || type == 412 || type == 413 || type == 421
					|| type == 422 || type == 423) {
				exportParam.configurable = 'bumentiaozhengReport';
				if (type == 411) {
					exportParam.configurable_value = dateBefore;
				} else if (type == 412) {
					exportParam.configurable_value = date;
				} else if (type == 413) {
					exportParam.configurable_value = dateAfter;
				} else if (type == 421) {
					exportParam.configurable_value = dateBefore;
					exportParam.levelc = "实习生";
				} else if (type == 422) {
					exportParam.configurable_value = date;
					exportParam.levelc = "实习生";
				} else {
					exportParam.configurable_value = dateAfter;
					exportParam.levelc = "实习生";
				}
			}
			$("#export_query").form('submit', {
				url : url,
				queryParams : exportParam,
				onSubmit : function() {
					console.log("正在导出,请稍后");
				},
				onLoadSuccess : function() {
					downloadForm.destoryForm();
				}
			});
		},
		exportYDExcel: function(type){
			var url = "file/exportYiDong.action";
			var exportParam = {};
			exportParam.type = type ? type : "211";
			downloadForm.createForm();
			$("#export_query").form('submit', {
				url : url,
				queryParams : exportParam,
				onSubmit : function() {
					console.log("正在导出,请稍后");
				},
				onLoadSuccess : function() {
					downloadForm.destoryForm();
				}
			});
		},
		initEmployee : function(data, user) {
//		console.log("employee.initEmployee!");
			$('#employee_datas')
					.datagrid(
					{
						title : '职工信息列表',
						iconCls : 'icon-edit',// 图标
						height : 'auto',
//							fitColumns : true,
						singleSelect : true,
						url : 'employee/renshiUser.action',
						singleSelect : true,// 是否单选
						pagination : true,// 分页控件
						pageSize : 15,
						striped : true,
						pageList : [ 10, 15, 20, 30, 50, 100, 200],
						rownumbers : true,// 行号
						rowStyler : function(index, row) {
							if (row.leaveTime && row.leaveTime != '') {
								return 'background-color:#E88282;color:#fff;font-weight:bold;';
							}
							if(/实习生/.test(row.level)){
								return 'background-color:yellow;';
							}
//								if(index%2 == 0)
//								{
//									return 'background-color:rgb(245,245,245);';
//								}
						},
//							columns : [ [
//							              {
//								field : 'id',
//								title : '员工编号',
//								sortable : true,
//								width : 60
//							},
//							{
//								field : 'username',
//								title : '姓名',
//								sortable : true,
//								width : 60
//							}, {
//								field : 'firstLevel',
//								title : '公司',
//								sortable : true,
//								width : 40
//							}, {
//								field : 'secondLevel',
//								title : '部门',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'thirdLevel',
//								title : '岗位',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'fourthLevel',
//								title : '小组',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'position',
//								title : '职位',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'sex',
//								title : '性别',
//								sortable : true,
//								width : 40
//							}, {
//								field : 'identityCard',
//								title : '身份证号',
//								sortable : true,
//								width : 125
//							}, {
//								field : 'borthDay',
//								title : '出生年月',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'nation',
//								title : '民族',
//								sortable : true,
//								width : 40
//							}, {
//								field : 'householdType',
//								title : '户口性质',
//								sortable : true,
//								width : 60
//							}, {
//								field : 'insurance',
//								title : '保险',
//								sortable : true,
//								width : 60
//							}, {
//								field : 'insuranceCompany',
//								title : '保险公司',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'company',
//								title : '公司名称',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'degree',
//								title : '学历',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'workPlace',
//								title : '工作地点',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'earlyEntryDate',
//								title : '早期入职时间',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'hiredate',
//								title : '入职时间',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'zhuanzhengTime',
//								title : '转正时间',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'leaveTime',
//								title : '离职时间',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'phoneNumber',
//								title : '联系方式',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'graduatedSchool',
//								title : '毕业学校',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'major',
//								title : '专业',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'homeAddress',
//								title : '家庭住址',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'homeNumber',
//								title : '家庭电话',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'pastLeaveTime',
//								title : '过去离职时间',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'maritalStatus',
//								title : '婚姻状况',
//								sortable : true,
//								width : 60
//							}, {
//								field : 'cellCore',
//								title : '细胞核',
//								sortable : true,
//								width : 60
//							}, {
//								field : 'cellCoreEmail',
//								title : '细胞核邮箱',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'guidance',
//								title : '指导',
//								sortable : true,
//								width : 60
//							}, {
//								field : 'guidanceEmail',
//								title : '指导邮箱',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'managementSystem',
//								title : '关系',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'signedTime',
//								title : '签定时间',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'terminationTime',
//								title : '终止时间',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'registeredAddress',
//								title : '户口地址',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'Postcode',
//								title : '邮编',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'socialSecurityHospital',
//								title : '社保医院',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'dueSocialSecurity',
//								title : '投保时间',
//								sortable : true,
//								width : 100
//							},{
//								field : 'level',
//								title : '级别',
//								sortable : true,
//								width : 80
//							}, {
//								field : 'email',
//								title : '邮箱',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'ruzhiReport',
//								title : '入职报表',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'lizhiReport',
//								title : '离职报表',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'zhuanzhengReport',
//								title : '转正报表',
//								sortable : true,
//								width : 100
//							}, {
//								field : 'bumentiaozhengReport',
//								title : '部门调整报表',
//								sortable : true,
//								width : 100
//							} ] ],
						toolbar : '#renshi_toolbar',
						onDblClickCell : function(index, field, value) {
							employee.view();
						}
					});
		},
	},

	queryEmployee : function(data, src) {
		$('#employee_datas').datagrid({
			url : 'employee/renshiUser.action',
			queryParams : data
		})
	},
	view : function(flage,obj) {
		var rights = session.user.roleId;
		var userInfo;
		if(flage == 1)
			{
			userInfo = obj;
			}
		else
			{
			userInfo = $('#employee_datas').datagrid('getSelected');
			}
		session.employee = {};
		if(!userInfo||!userInfo.id||userInfo.id==0){
			$.messager.alert('操作提示：','请先选择员工,您没有选择任何员工！~');
			return false;
		}
		session.employee.cellCoreEmail = userInfo.cellCoreEmail?userInfo.cellCoreEmail:'';
		var url = "user/getUserName.action";
		$('#updateUsesrname_form').form('clear');
		$.getJSON(url, userInfo, function(result) {
			if (result.success) {
//				if(result.obj.ifSecret == "on"){
//					$('#btn_wagesInfo').linkbutton({disabled:true});
//				}else{
//					$('#btn_wagesInfo').linkbutton({disabled:false});
//				}
				$('#userName_info').dialog({
					title : '查看职员信息'
				});
				result.obj.sercret = result.obj.ifSecret;
				$('#updateUsesrname_form').form('load', result.obj);
				$('#btn_employeeMailto').attr('href', 'mailto:' + result.obj.email + "?cc=" + session.employee.cellCoreEmail);
				disabledForm('updateUsesrname_form', true);
				$('#userName_info').window('open').window(
						'resize',
						{
							top : $(document).scrollTop()
									+ ($(window).height() - 480) * 0.5
						});
				$('#btn_employeeSave').linkbutton("disable");
				$('#btn_employeeRest').linkbutton("disable");
				$('#btn_employeeEdit').linkbutton("enable");
			} else {
				$.messager.alert(result.msg);
			}
		})
	},
	editEmployee : function(flage) {
		$('#userName_info').dialog({
			title : '编辑职员信息'
		});
		var url = "user/getUserName.action";
		var form_url = "user/updateUserName.action"
		$('#updateUsesrname_form').form({
			url : form_url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert("更新提示",result.msg);
				} else {
					$.messager.alert("更新提示",result.msg);
				}
			}
		});
		if(flage && flage == 1){
			disabledForm('updateUsesrname_form', false);
			$('#btn_employeeSave').linkbutton("enable");
			$('#btn_employeeRest').linkbutton("enable");
			$('#btn_employeeEdit').linkbutton("disable");
		}else{
			var userInfo = $('#employee_datas').datagrid('getSelected');
			if(!userInfo||!userInfo.id||userInfo.id==0){
				$.messager.alert('操作提示：','请先选择员工,您没有选择任何员工！~');
				return;
			}
			$('#updateUsesrname_form').form('clear');
			session.employee.cellCoreEmail = userInfo.cellCoreEmail?userInfo.cellCoreEmail:'';
			$.getJSON(url, userInfo, function(result) {
				if (result.success) {
					result.obj.sercret = result.obj.ifSecret;
					$('#updateUsesrname_form').form('load', result.obj);
					$('#btn_employeeMailto').attr('href', 'mailto:' + result.obj.email + "?cc=" + session.employee.cellCoreEmail);
					$('#userName_info').window('open').window(
							'resize',
							{
								top : $(document).scrollTop()
								+ ($(window).height() - 480) * 0.5
							});
					disabledForm('updateUsesrname_form', false);
					$('#btn_employeeSave').linkbutton("enable");
					$('#btn_employeeRest').linkbutton("enable");
					$('#btn_employeeEdit').linkbutton("disable");
				} else {
					$.messagr.alert('提示：',result.msg);
				}
			})
		}
	},
	openAddEEmployee : function() {
		$('#updateUsesrname_form').form('clear');
		$('#userName_info').window('open').window('resize', {
			top : $(document).scrollTop() + ($(window).height() - 480) * 0.5
		});
		disabledForm('updateUsesrname_form', false);
		$('#btn_employeeSave').linkbutton("enable");
		$('#btn_employeeRest').linkbutton("enable");
		$('#btn_employeeEdit').linkbutton("disable");
	},
	addEmployee : function() {
		$('#userName_info').dialog({
			title : '增加职员'
		});
		$('#updateUsesrname_form').form('clear');
		$('#userName_info').window('open').window('resize', {
			top : $(document).scrollTop() + ($(window).height() - 480) * 0.5
		});
		disabledForm('updateUsesrname_form', false);
		$('#btn_employeeSave').linkbutton("enable");
		$('#btn_employeeRest').linkbutton("enable");
		$('#btn_employeeEdit').linkbutton("disable");
		$('#updateUsesrname_form').form('load', {
			id : -1
		});

		var url = "user/addUserName.action";
		$('#updateUsesrname_form').form({
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				$('#employee_datas').datagrid('reload');
//				if (result.success) {
////					form_hidden_ID
//					data = {};
//					data.id = result.obj;
//					$('#updateUsesrname_form').form('load', data);
//					$.messager.alert("添加提示", result.msg);
//				} else {
//					$.messager.alert("添加提示", result.msg);
//				}
			}
		});
		$('#text_nation').textbox('setValue', '汉');
		$('#combox_sex').combobox('setValue', '男');
//		$("#textbox_id").textbox(
//				{
//					onChange : function(newValue, oldValue) {
////						if (oldValue && oldValue.length > 0) {
////							return;
////						}
//						if(newValue.length==18){
//							borthday = newValue.substr(6, 4) + '.'
//									+ newValue.substr(10, 2) + '.'
//									+ newValue.substr(12, 2);
//							$('#textbox_borth').textbox('setValue', borthday);
//						}
//					}
//				})
//		$("#textbox_addrss").textbox({
//			onChange : function(newValue, oldValue) {
//				if (oldValue && oldValue.length > 0) {
//					return;
//				}
//				$('#textbox_hukou').textbox('setValue', newValue);
//			}
//		})
//		$('#date_hiredate').datebox({
//			onChange:function(newValue,oldValue){
//				var date = new Date(newValue);
//				date.setMonth(date.getMonth()+3);
//				var newDate = date.getFullYear()+ "." + (date.getMonth()<10 ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1)) + "." + date.getDate();
//				$('#zhuangZheng_datebox').datebox('setValue',newDate);
//				$('#signed_date').datebox('setValue',newDate);
//				}
//		})
//		$('#signed_date').datebox({
//			onChange:function(newValue,oldValue){
//				var date = new Date(newValue);
////				date.setMonth(date.getMonth()+3);
//				date.setYear(date.getFullYear()+3);
//				var newDate = date.getFullYear()+ "." + (date.getMonth()<10 ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1)) + "." + date.getDate();
//				$('#end_datebox').datebox('setValue',newDate);
//			}
//		})
	},
	deleteEmployee : function(confirmId) {
		var userInfo = $('#employee_datas').datagrid('getSelected');
		var url = "user/deleteUserName.action";
		$.post(url, userInfo, function(result) {
//			result = eval("("+ result + ")");
			confirmDialog.destoryDialog(confirmId);
			$('#employee_datas').datagrid('reload');
//			if (result.success) {
//				$.messager.alert(result.msg,result.msg);
//			} else {
				$.messager.alert(result.msg,result.msg);
//			}
		})
	},
//	exportExcel : function(type) {
//		console.log('exportExcel:' + type);
//		var exportParam = {};
//		var form = $('#renshiquery_form');
//		var data = getDataOfForm(form);
//		downloadForm.createForm();
//		var url = "file/exportEmployeeExcel.action";
//		var mydate = new Date();
//		var year = mydate.getFullYear(); // 获取完整的年份(4位,1970-????)
//		var month = mydate.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
//		var monthb = month - 1;
//		var montha = month + 1;
//		var date = '' + year + (month < 10 ? ('0' + month) : month);
//		var dateBefore ='' + year + (monthb < 10 ? ('0' + monthb) : monthb);
//		var dateAfter ='' + year + (montha < 10 ? ('0' + montha) : montha);
//		exportParam.configurable = 'reportForm';
//		if (!type || type == 0) {
//			delete exportParam.configurable;
//			if (data.succeed) {
//				exportParam = data.data;
//			}
//		} else if (type == 111 || type == 112 || type == 113 || type == 121
//				|| type == 122 || type == 123) {
//			exportParam.configurable = 'ruzhiReport';
//			if (type == 111) {
//				exportParam.configurable_value = dateBefore;
//				exportParam.levelc = "非实习生";
//			} else if (type == 112) {
//				exportParam.configurable_value = date;
//				exportParam.levelc = "非实习生";
//			} else if (type == 113) {
//				exportParam.configurable_value = dateAfter;
//				exportParam.levelc = "非实习生";
//			} else if (type == 121) {
//				exportParam.configurable_value = dateBefore;
//				exportParam.levelc = "实习生";
//			} else if (type == 122) {
//				exportParam.configurable_value = date;
//				exportParam.levelc = "实习生";
//			} else {
//				exportParam.configurable_value = dateAfter;
//				exportParam.levelc = "实习生";
//			}
//		} else if (type == 211 || type == 212 || type == 213 || type == 221
//				|| type == 222 || type == 223) {
//			exportParam.configurable = 'zhuanzhengReport';
//			if (type == 211) {
//				exportParam.configurable_value = dateBefore;
//				exportParam.levelc = "非实习生";
//			} else if (type == 212) {
//				exportParam.configurable_value = date;
//				exportParam.levelc = "非实习生";
//			} else if (type == 213) {
//				exportParam.configurable_value = dateAfter;
//				exportParam.levelc = "非实习生";
//			} else if (type == 221) {
//				exportParam.configurable_value = dateBefore;
//				exportParam.levelc = "实习生";
//			} else if (type == 222) {
//				exportParam.configurable_value = date;
//				exportParam.levelc = "实习生";
//			} else {
//				exportParam.configurable_value = dateAfter;
//				exportParam.levelc = "实习生";
//			}
//		} else if (type == 311 || type == 312 || type == 313 || type == 321
//				|| type == 322 || type == 323) {
//			exportParam.configurable = 'lizhiReport';
//			if (type == 311) {
//				exportParam.configurable_value = dateBefore;
//				exportParam.levelc = "非实习生";
//			} else if (type == 312) {
//				exportParam.configurable_value = date;
//				exportParam.levelc = "非实习生";
//			} else if (type == 313) {
//				exportParam.configurable_value = dateAfter;
//				exportParam.levelc = "非实习生";
//			} else if (type == 321) {
//				exportParam.configurable_value = dateBefore;
//				exportParam.levelc = "实习生";
//			} else if (type == 322) {
//				exportParam.configurable_value = date;
//				exportParam.levelc = "实习生";
//			} else {
//				exportParam.configurable_value = dateAfter;
//				exportParam.levelc = "实习生";
//			}
//		} else if (type == 411 || type == 412 || type == 413 || type == 421
//				|| type == 422 || type == 423) {
//			exportParam.configurable = 'bumentiaozhengReport';
//			if (type == 411) {
//				exportParam.configurable_value = dateBefore;
//			} else if (type == 412) {
//				exportParam.configurable_value = date;
//			} else if (type == 413) {
//				exportParam.configurable_value = dateAfter;
//			} else if (type == 421) {
//				exportParam.configurable_value = dateBefore;
//				exportParam.levelc = "实习生";
//			} else if (type == 422) {
//				exportParam.configurable_value = date;
//				exportParam.levelc = "实习生";
//			} else {
//				exportParam.configurable_value = dateAfter;
//				exportParam.levelc = "实习生";
//			}
//		}
//		$("#export_query").form('submit', {
//			url : url,
//			queryParams : exportParam,
//			onSubmit : function() {
//				console.log("正在导出,请稍后");
//			},
//			onLoadSuccess : function() {
//				downloadForm.destoryForm();
//			}
//		});
//	},
	helpEmployee : function() {
	},
	reloadQueryForm : function(dom) {
		alert(dom);
	},
	wages : {
		getCountId: function(txStruct, position){
			txStruct = txStruct.trim_();
			if(txStruct=="办公室" ){
				return 4;
			}else if (txStruct == "销售"){
				return 3;
			}else if (txStruct == "技术服务" || txStruct == "研发"){
				return 2;
			}
			return 0;
		},
		refresh_wagesData : function(mesg){
			$('#datagrid_wages').datagrid({
				url:'employee/getWagesList.action',
				queryParams:{page:1,rows:5,id:session.formData.id},
				onLoadSuccess : function(){
					employee.wages.updateHtml();
					$.messager.alert('提示：',mesg);
				}
			})
			employee.wages.form_onchange(false);
			disabledForm('updatewages_form', true);
			disabledButton('updatewages_form', true);
			employee.wages.canotEdit('updatewages_form');
		},
		openWages : function(src) {
			editIndex = undefined;
			disabledForm('updatewages_form', true);
			$('#wages_footer>select').val(0);
			session.formData = getDataOfForm($('#updateUsesrname_form')).data;
			var dptId = Number(session.formData.departmentId);
			$('#wages_username').html(session.formData.username);
			$('#wages_identity').html(session.formData.identityCard);
			$('#wages_hidden_taxS').val(session.ostructure[dptId].taxStructure);
			$('#wages_hidden_eid').val(session.formData.id);
			$('#wages_hidden_countId').val(employee.wages.getCountId(session.ostructure[dptId].taxStructure));
			var queryParam = {};
			queryParam.id = session.formData.id;
			if(queryParam.id<0){
				$.messager.alert('提示：','职员id错误，请重新打开职员信息窗口！~');
				return false;
			}
//			employee.wages.toggleEdit(true,queryParam);
//			session.wadgeData = {};
			//begin - - - - - - -
			$('#datagrid_wages').datagrid(
					{
						url : "employee/getWagesList.action",
						queryParams : queryParam,
						height : 'auto',
						fitColumns : true,
						singleSelect : true,// 是否单选
						rownumbers : true,// 行号
						pagination : true,// 分页控件
						pageSize : 5,
						pageList : [ 5, 15, 20, 30, 50, 100 ],
						columns : [ [ {
							field : 'id',
							title : '工资编号',
							width : 80
						}, {
							field : 'salary',
							title : '工资总额',
							width : 100
						}, {
							field : 'identityCard',
							title : '身份证号码',
							width : 120,
							editor : 'textbox'
						}, {
							field : 'company',
							title : '公司名称',
							width : 100,
							editor:{
		                            type:'combobox',
		                            options:{
		                                valueField:'cmopany',
		                                textField:'cmopany',
		                                method:'get',
		                                url:'employee/getCompany.action',
		                                required:true
		                            }
		                        }
						}, {
							field : 'accountBank',
							title : '开户行',
							width : 100,
							editor : {
								type:'combobox',
								options:{
									valueField: 'value',
									textField: 'text',
									data: [{
										text: '交行',
										value: '交行'
									},{
										text: '招行',
										value: '招行'
									},{
										text: '建行',
										value: '建行'
									},{
										text: '光大',
										value: '光大'
									},{
										text: '工行',
										value: '工行'
									},{
										text: '南京银行',
										value: '南京银行'
									},{
										text: '现金无卡号',
										value: '现金无卡号'
									},{
										text: '现金离职 办理中',
										value: '现金离职 办理中'
									},{
										text: '现金入职资料不全',
										value: '现金入职资料不全'
									},{
										text: '现金 自取',
										value: '现金 自取'
									}]
								}
							}

						}, {
							field : 'account',
							title : '职工帐号',
							width : 180,
							editor : 'textbox'
						}, {
							field : 'householdType',
							title : '户口性质',
							width : 100,
							editor:{
	                            type:'combobox',
	                            options:{
	                                valueField:'householdType',
	                                textField:'householdType',
	                                method:'get',
	                                url:'employee/getHouseholdType.action',
	                                required:true
	                            }
	                        },
//							editor : 'textbox',
						},{
							field : 'rubaoTime',
							title : '入保时间',
							width : 100,
							editor :{
								type : 'textbox'
							}
						},{
							field : 'taxStructure',
							title : '报税架构',
							width : 100,
							editor :{
								type : 'combobox',
								options: {
									url: 'employee/getTxs.action',
									valueField:'name',
									textField:'name'
								}
							}
						},{
							field : 'countId',
							title : '统计架构',
							width : 100,
							editor :{
								type : 'combobox',
								options: {
									url: 'employee/getCountst.action',
									valueField:'id',
									textField:'name'
								}
							}
						} ] ],
						//TODO 结束编辑，form表单显示 工资信息
						onClickRow : employee.wages.viewWages,
						//TODO 双击进入编辑状态，并关闭之前状态
						onDblClickRow : employee.wages.onDblClickRow,
						//TODO 结束编辑,向后台直接请求
						onEndEdit : employee.wages.onEndEdit,
						onLoadSuccess : function(){
							employee.wages.updateHtml();
//							$('#userName_info').dialog('close');
							$('#updatewages_form').form('clear');
							$('#updatewages_form').form({
								url : 'employee/updateWages.action'
							});
							employee.wages.form_onchange(false);
							disabledForm('updatewages_form', true);
							disabledButton('updatewages_form', true);
							$('#dialog_wagesInfo').dialog("open");
						},
						toolbar : [
								{
									text : '新增',
									iconCls : 'icon-add',
									handler : function(){
										if(session.formData.sercret == 'on'){
											if(session.user.roleId=='0'||session.user.roleId=='100'){
												employee.wages.append();
											}else{
												$.messager.alert('提示','该员工工资保密，你没有增加权限~');
											}
										}else{
											employee.wages.append();
										}
									}

								} ,
								'-',
								{
									text : '编辑',
									iconCls : 'icon-edit',
									handler : function(){
										if(session.formData.sercret == 'on'){
											if(session.user.roleId=='0'||session.user.roleId=='100'){
												employee.wages.editWages();
											}else{
												$.messager.alert('提示','该员工工资保密，你没有编辑权限~');
											}
										}else{
											employee.wages.editWages();
										}
									}
								},
								'-',
								{
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										if(session.formData.sercret == 'on'){
											if(session.user.roleId=='0'||session.user.roleId=='100'){
												confirmDialog.createDialog("您确定要删除吗？",employee.wages.deleWages);
											}else{
												$.messager.alert('提示','该员工工资保密，你没有删除权限~');
											}
										}else{
											confirmDialog.createDialog("您确定要删除吗？",employee.wages.deleWages);
										}
									}
								} ],
						footer : "#wages_footer",
					})
			//end - - - - - - -
		},
        toggleEdit: function(flag){
			editIndex = undefined;
			disabledForm('updatewages_form', true);
			session.formData = getDataOfForm($('#updateUsesrname_form')).data;
			$('#wages_username').html(session.formData.username);
			$('#wages_identity').html(session.formData.identityCard);
			$('#wages_company').html(session.formData.company);
			var queryParam = {};
			queryParam.id = session.formData.id;
			if(queryParam.id<0){
				$.messager.alert('提示：','职员id错误，请重新打开职员信息窗口！~');
				return false;
			}
			$('#datagrid_wages').datagrid(
					{
						url : "employee/getWagesList.action",
						queryParams : queryParam,
						height : 'auto',
						fitColumns : true,
						singleSelect : true,// 是否单选
						rownumbers : true,// 行号
						pagination : true,// 分页控件
						pageSize : 5,
						pageList : [ 5, 15, 20, 30, 50, 100 ],
						columns : [ [ {
							field : 'id',
							title : '工资编号',
							width : 80
						}, {
							field : 'radix',
							title : '基数',
							width : 100,
							editor : {
								type : 'numberbox',
								options : {
									min : 0,
									precision : 2
								}
							}
						}, {
							field : 'company',
							title : '公司名称',
							width : 100,
							editor:{
								type:'combobox',
								options:{
									valueField:'cmopany',
									textField:'cmopany',
									method:'get',
									url:'employee/getCompany.action',
									required:true
								}
							}
						}, {
							field : 'householdType',
							title : '户口性质',
							width : 100,
							editor:{
								type:'combobox',
								options:{
									valueField:'householdType',
									textField:'householdType',
									method:'get',
									url:'employee/getHouseholdType.action',
									required:true
								}
							},
						},{
							field : 'rubaoTime',
							title : '入保时间',
							width : 100,
							editor :{
								type : 'textbox'
							}
						} ] ],
						//TODO 结束编辑，form表单显示 工资信息
						onClickRow : employee.wages.viewWages,
						//TODO 双击进入编辑状态，并关闭之前状态
						onDblClickRow : employee.wages.onDblClickRow,
						//TODO 结束编辑,向后台直接请求
						onEndEdit : employee.wages.onEndEdit_,
						onLoadSuccess : function(){
							employee.wages.updateHtml();
//							$('#userName_info').dialog('close');
							$('#updatewages_form').form('clear');
							$('#updatewages_form').form({
								url : 'employee/updateWages.action'
							});
							employee.wages.form_onchange(false);
							disabledForm('updatewages_form', true);
							disabledButton('updatewages_form', true);
							$('#dialog_wagesInfo').dialog("open");
						},
						footer : "#wages_footer",
					})
        },
		viewWages : function(index,row){//显示
			session.append = false;
			employee.wages.form_onchange(false);
			$('#wages_add').css('display', 'none');
			$('#wages_save').css('display', '');
			$('#wages_edit').css('display', '');
			disabledForm('updatewages_form', true);
			disabledButton('updatewages_form', true);
			employee.wages.canotEdit('updatewages_form');
//			var userInfo = $('#datagrid_wages').datagrid('getSelected');
			$('#updatewages_form').form('load', row);
			employee.wages.endEditing();
		},
		onDblClickRow : function(index,row){
			session.append = false;
			employee.wages.form_onchange(false);
			$('#wages_add').css('display', 'none');
			$('#wages_save').css('display', '');
			$('#wages_edit').css('display', '');
			disabledForm('updatewages_form', true);
			disabledButton('updatewages_form', true);
			if (editIndex != index) {
				if (employee.wages.endEditing()) {
					$('#datagrid_wages').datagrid('selectRow', index).datagrid(
							'beginEdit', index);
					editIndex = index;
				} else {
					setTimeout(function() {
						$('#datagrid_wages').datagrid('selectRow', editIndex);
					}, 0);
				}
			}
		},
		editWages : function(index, field, value) {
			employee.wages.form_onchange(true);
			$('#wages_add').css('display', 'none');
			$('#wages_save').css('display', '');
			$('#wages_edit').css('display', '');
			disabledForm('updatewages_form', false);
			disabledButton('updatewages_form', false);
			employee.wages.canotEdit('updatewages_form');
			var userInfo = $('#datagrid_wages').datagrid('getSelected');
			$('#updatewages_form').form('load', userInfo);
			$('#wages_id').textbox('enable');
			$('#wages_id').textbox('readonly');
		},
		endEditing : function() {
			if (editIndex == undefined) {
				return true
			}
			if ($('#datagrid_wages').datagrid('validateRow', editIndex)) {
				$('#datagrid_wages').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		},
		onEndEdit_: function(index, row,changes){
			employee.wages.onEndEdit(index, row,changes,true)
		},
		onEndEdit : function(index, row,changes,flag) {
			var url = flag ? "employee/updateGridShebaoWages.action" : "employee/updateGridWages.action" ;
			if (session.append) {
				return false
			};
			changes.id = row.id;
			var data = row;
			if(!data.company||data.company==''||!data.householdType||data.householdType==''){
				$.messager.alert('tips', '您没有输入公司名称或户口性质，请重新填写！', 'info');
				return false;
			}
			if(!isEmpty(changes)){
//			if(changes.radix || changes.company || changes.accountBank || changes.account){
				$.post(url ,row, function(result){
					if(result.success){
						employee.wages.refresh_wagesData(result.msg);
					}else{
						$.messager.alert('更新提示',result.msg);
					}
				})
			}
		},
		append : function() {
			session.append = true;
			$('#wages_add').css('display', '');
			$('#wages_save').css('display', 'none');
//			$('#wages_edit').css('display', 'none');
			if (employee.wages.endEditing()) {
				$('#updatewages_form').form('clear');
				var txs = $("#wages_hidden_taxS").val();
				var countid = $('#wages_hidden_countId').val();
				$('#updatewages_form').form('load', $.extend({},session.formData,{taxStructure:txs,countId:countid}));
				$('#updatewages_form input[class="easyui-numberbox numberbox-f textbox-f"]').each(function() {
							$(this).numberbox({value : 0});
						});
				disabledForm('updatewages_form', false);
				disabledButton('updatewages_form', false);
				employee.wages.canotEdit('updatewages_form');
				employee.wages.form_onchange(true);
				$('#wages_id').textbox('enable');
				$('#wages_id').textbox({
					readonly : true,
					value : ''
				});
				$('#wages_hidden_employeeid').val(session.formData.id);
			}
		},
		deleWages : function(confirmId) {
			var selected = $('#datagrid_wages').datagrid('getSelected');
			if (selected && selected.id != '0') {
				if(session.formData.sercret=='on'){// session.formData.ifSecret
					if(session.user.roleId=='0'||session.user.roleId=='100'){
						$.post("employee/deleteWages.action", selected,function(result) {
//							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if (result.success) {
								employee.wages.refresh_wagesData(result.msg);
								}else{
										$.messager.alert('消息', result.msg, 'info');
									}
								})
					}else{
						$.messager.alert('提示','你没有删除权限~');
					}
				}else{
					$.post("employee/deleteWages.action", selected,function(result) {
//						var result =  eval("(" + result + ")");
						confirmDialog.destoryDialog(confirmId);
						if (result.success) {
							employee.wages.refresh_wagesData(result.msg);
							
						}else{
							$.messager.alert('消息', result.msg, 'info');
						}
						})
				}
			}
		},
		updateWages : function(dom) {
			$('#wages_hidden_employeeid').val(session.formData.id);
			$('#updatewages_form').form({
				url : 'employee/updateWages.action'
			});
			$('#wages_id').trigger('onclick');
			submitForm(dom, function(result) {
				if (result.success) {
//					employee.wages.openWages();
//					$.messager.alert(result.msg);
//					$('#datagrid_wages').datagrid({url:'employee/getWagesList.action',queryParams:{page:1,rows:5,id:session.formData.id}})
//					employee.wages.updateHtml();
//					disabledForm('updatewages_form', true);
//					disabledButton('updatewages_form', true);
					employee.wages.refresh_wagesData(result.msg);
//					employee.wages.form_onchange(false);
				}else{
					employee.wages.refresh_wagesData(result.msg);
//					$.messager.alert('消息', result.msg, 'info');
				}
			});
			disabledForm('updatewages_form', true);
			disabledButton('updatewages_form', true);
//			disabledForm('updatewages_form', true);
		},
		addWages : function(dom) {
			$('#wages_hidden_employeeid').val(session.formData.id);
			$('#updatewages_form').form({
				url : 'employee/addWages.action'
			});
			submitForm(dom, function(result) {
				if (result.success) {
					employee.wages.refresh_wagesData(result.msg);
				}else{
					employee.wages.refresh_wagesData(result.msg);
				}
			});
			disabledForm('updatewages_form', true);
			disabledButton('updatewages_form', true);
		},
		form_onchange : function(ischange){
			if(ischange){
				$("#wages_radix").textbox(
						{
							onChange : function(newValue, oldValue) {
								var data = {};
								data.radix = newValue;
								data.company = $('#wages_company input[name="company"]').val();
								data.householdType = $('#wages_hoseholdType input[name="householdType"]').val();
								if(!data.company || data.company == '' || data.newValue <= 0||!data.householdType){
									$.messager.alert('提示：','基础不合法或者您未选择社保公司和户口性质！');
									return;
								}
								wagesCalculate.calculateShebao(data, function(result) {
									if (result) {
										$('#updatewages_form').form('load',data);
									}
								})
							}
						})
						$("#total_salary").numberbox(
								{
									onChange : function(newValue, oldValue) {
										var data = {};
										data.basicWage = 0;
										data.performanceRelatedPay = 0;
										data.postSalary = 0;
										if(newValue>=2000){
											data.basicWage = 1400;
											data.performanceRelatedPay = newValue * 0.3;
											data.postSalary = newValue - data.basicWage - data.performanceRelatedPay;
										}else if(newValue >= 1400){
											data.basicWage = 1400;
											data.performanceRelatedPay = newValue - data.basicWage;
										}else if(newValue > 0){
											data.basicWage = newValue;					
										}
										$('#updatewages_form').form('load',
												data);
									}
								})
			}else{
				$("#wages_radix").textbox({onChange:function(){}});
				$("#total_salary").numberbox({onChange:function(){}});
			}
		},
		updateHtml : function(){
			var rowsData = $('#datagrid_wages').datagrid('getData');
			var salary = 0;
			$.each(rowsData.rows,function(n, obj) {
//				if (n == 0) {
//					$('#wages_identity').html(
//							obj.identityCard);
//					$('#wages_company').html(
//							obj.company);
//					$('#wages_username').html(
//							obj.username);
//				}
				salary += Number(obj.salary);
			})
	$('#wages_totalSalary').html(salary);
		},
		canotEdit : function(formId){
			if(!formId) return false;
			//禁用jquery easyui中的linkbutton组件
			$("#" + formId + " input[target~='_blank']").each(function () {
				$(this).textbox({disabled:true})
			});
		}
	},
	shebao : {
		onDblClickRow : function(index,row) {
			if(!shebaoEdit){
				session.editRow = $.extend({},row);
			}
			if (shebaoEdit != index) {
				if (employee.shebao.endEditing()) {
					$('#datagrid_shebao').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					shebaoEdit = index;
				} else {
					setTimeout(function() {$('#datagrid_shebao').datagrid('selectRow',shebaoEdit);
							}, 0);
				}
			}
			
		},
		endEditing : function() {
			if (shebaoEdit == undefined) {
				return true
			}
			if ($('#datagrid_shebao').datagrid('validateRow', shebaoEdit)) {
				$('#datagrid_shebao').datagrid('endEdit', shebaoEdit);
				shebaoEdit = undefined;
				return true;
			} else {
				return false;
			}
		},
		onEndEdit : function(index,row,changes) {
			var message = "您确定要更新吗？";
			var url = "employee/updateShebao.action";
			if(shebaoAdd){
				message = "您确定要增加吗？";
				url = "employee/addShebao.action";
			}
			confirmDialog.createDialog(
					message,
					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
//							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								if(shebaoAdd){
									employee.shebao.removeit(1);
									shebaoAdd = undefined;
									return;
								}
								$('#datagrid_shebao').datagrid('getData').rows[index] = session.editRow;
								$('#datagrid_shebao').datagrid('refreshRow',index);
							}else{
								if(shebaoAdd){
									$('#datagrid_shebao').datagrid('reload');
									shebaoAdd = undefined;
									return;
								}
//								else{
//									$('#datagrid_shebao').datagrid('getData').rows[index] = session.editRow;
//									$('#datagrid_shebao').datagrid('refreshRow',index);
//								}
							}
							$.messager.alert('消息', result.msg, 'info');
						});
					},function(){
						if(shebaoAdd){
							employee.shebao.removeit(1);
							shebaoAdd = undefined;
							return;
						}
						$('#datagrid_shebao').datagrid('getData').rows[index] = session.editRow;
						var selectedData = $('#datagrid_shebao').datagrid('getSelected');
						session.editRow = $.extend({},selectedData);
						$('#datagrid_shebao').datagrid('refreshRow',index);
					});
		},
		append : function() {
			if (employee.shebao.endEditing()) {
				shebaoAdd = 1;
				var selectedData = $('#datagrid_shebao').datagrid('getSelected');
				if(!selectedData){
					selectedData = {};
					selectedData.company = "世纪超星";
					selectedData.shebaoType = "养老保险";
					selectedData.typeCode = 1;
					selectedData.radixMin = 0;
					selectedData.radixMax = 0;
					selectedData.cRadio = 0;
					selectedData.radio = 0;
					selectedData.cFixedValue = 0;
					selectedData.fixedValue = 0;
				}
				$('#datagrid_shebao').datagrid('appendRow',selectedData);
				shebaoEdit = $('#datagrid_shebao').datagrid('getRows').length - 1;
				$('#datagrid_shebao').datagrid('selectRow', shebaoEdit)
						.datagrid('beginEdit', shebaoEdit);
			}
		},
		removeit : function(confirmId) {
			if(confirmId){
				shebaoEdit = $('#datagrid_shebao').datagrid('getRows').length - 1;
			}
			if (shebaoEdit == undefined) {
				return
			}
			$('#datagrid_shebao').datagrid('cancelEdit', shebaoEdit).datagrid(
					'deleteRow', shebaoEdit);
			shebaoEdit = undefined;
		},
		remove : function() {
			var selectedData = $('#datagrid_shebao').datagrid('getSelected');
			var message = "您确定要删除<strong>【" + selectedData.company + "-" + selectedData.shebaoType + "】</strong>吗？";
			confirmDialog.createDialog(
					message,
					function(confirmId){
						$.post('employee/deleteShebao.action', selectedData, function(result){
							confirmDialog.destoryDialog(confirmId);
//							var result =  eval("(" + result + ")");
							$.messager.alert("删除结果", result.msg);
							$('#datagrid_shebao').datagrid('reload');
						})
					});
		},
		accept : function() {
			if (employee.shebao.endEditing()) {
				$('#datagrid_shebao').datagrid('acceptChanges');
			}
		},
		reject : function() {
			$('#datagrid_shebao').datagrid('rejectChanges');
			shebaoEdit = undefined;
		},
		getChanges : function() {
			var rows = $('#datagrid_shebao').datagrid('getChanges');
			alert(rows.length + ' rows are changed!');
		},
		queryShebao : function(data, src) {
			$('#datagrid_shebao').datagrid({
				queryParams : data
			})
		}
	},
shebaoSummary : {
//	initShebaoSummary : function(){
//		$('#datagrid_shebaoSummary').datagrid({
//			loadFilter : function(data){
//				$.each(data.rows,function(n, obj){
//					if(obj.locked == 0){
//						console.log(obj.locked);
//						obj.locked = '未锁定';
//					}else if(obj.locked == 1){
//						console.log(obj.locked);
//						obj.locked = '已锁定';
//					}
//				})
//				return data;
//			}
//		})
//	},
	queryShebaoSummary : function(data, src) {
		$('#datagrid_shebaoSummary').datagrid({
			queryParams : data
		})
	},
	queryShebaoCompany : function(data, src) {
		data.company = session.shebaoCompany.company;
		$('#datagrid_shebaoCompany').datagrid({
			queryParams : data
		})
	},
	locked : function(flag){
		var shebaoSummaryInfo = $('#datagrid_shebaoSummary').datagrid('getSelected');
		var url = flag ? 'employee/luckedShebaoSummary.action':'employee/unluckeShebaoSummary.action';
		if(shebaoSummaryInfo.locked&&shebaoSummaryInfo.locked=='未锁定'&&!flag){
			return false;
		}
		if(shebaoSummaryInfo.locked&&shebaoSummaryInfo.locked=='已锁定'&&flag){
			return false;
		}
		if(shebaoSummaryInfo&&shebaoSummaryInfo.company&&shebaoSummaryInfo.company!=''){
			shebaoSummaryInfo.locked = (shebaoSummaryInfo.locked||shebaoSummaryInfo.locked=='未锁定')?0:1;
			$.post(url,shebaoSummaryInfo,function(result){
//				var result =  eval("(" + result + ")");
				if(result.success){
					$('#datagrid_shebaoSummary').datagrid('reload');
				}
				$.messager.alert('提示',result.msg);
			})
		}
	},
	view : function(index, row){
		
		$('#shebaoCompany_detail').dialog({
			title : '查看公司五险详情'
		});
		$('#shebaoCompany_detail').window('open').window(
				'resize',
				{
					top : $(document).scrollTop()
							+ ($(window).height() - 480) * 0.5
				});
		
		var company = row.company;
		session.shebaoCompany = {};
		session.shebaoCompany.company = company;
		$('#shebaoCompany_company').html(company);
		employee.shebaoSummary.updateDom(row);
		session.viewCompanyRow = row;
		session.viewCompanyIndex = index;
		var queryParam = {};
		queryParam.company = company;
		var url = "employee/getShebaoCompany.action";
		$('#datagrid_shebaoCompany').datagrid(
				{
					height : 'auto',
					fitColumns : true,
					url : url,
					queryParams : queryParam,
					singleSelect : true,// 是否单选
					rownumbers : true,// 行号
					pagination : true,// 分页控件
					pageSize : 10,
					striped:true,
					loadFilter : function(data){
						if (typeof(data.d)=='number'){
							return data.d.toFixed(2);
						} else {
							return data;
						}
					},
					pageList : [ 10, 15, 20, 30, 50, 100 ],
					columns : [ [ {
						field : 'ck',
						checkbox : true,
						width : 30
					}, {
						field : 'username',
						title : '职员姓名',
						sortable : true,
						width : 80
					}, {
						field : 'identityCard',
						title : '身份证号',
						sortable : true,
						width : 100
					}, {
						field : 'rubaoTime',
						title : '入保时间',
						sortable : true,
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'householdType',
						title : '户口性质',
						sortable : true,
						width : 100,
//						editor : {
//							type : 'combobox',
//							options:{
//                                valueField:'householdType',
//                                textField:'householdType',
//                                method:'get',
//                                url:'employee/getHouseholdType.action',
//                                required:true
//                            }
//						}
					}, {
						field : 'radix',
						title : '基数',
						sortable : true,
						width : 100,
//						editor : {
//							type : 'numberbox',
//							options : {
//								precision : 2
//							}
//						}
					}, {
						field : 'subEndowmentIinsurance',
						title : '代扣养老保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'subMedicare',
						title : '代扣医疗保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'subUnemployedInsurance',
						title : '代扣失业保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'subHouseIinsurance',
						title : '代扣住房保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'cEndowmentIinsurance',
						title : '公司养老保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}, {
						field : 'cMedicare',
						title : '公司医疗保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cUnemployedInsurance',
						title : '公司失业保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cHouseIinsurance',
						title : '公司住房保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cInjuryInsurance',
						title : '公司工伤保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					} , {
						field : 'cBirthIinsurance',
						title : '公司生育保险',
						width : 100,
						editor : {
							type : 'numberbox',
							options : {
								precision : 2
							}
						}
					}] ],
					toolbar : "#shebaoCompany_toolbar",
					footer : "#shebaoComany_footerbar",
					onDblClickRow : employee.shebaoSummary.onDblClickRow,
					onClickCell : employee.shebaoSummary.endEditing,
					onEndEdit: employee.shebaoSummary.onEndEdit}
					)
//					$('#datagrid_shebaoCompany').datagrid('enableFilter', [{}]); 不能用
	},
	exportShebaoDetail: function(type){
		var url = "file/exportShebaoDetail.action";
		var exportParam = {};
//		exportParams.date = date ? date : "";
		exportParam.type = type ? type : "111";
		downloadForm.createForm();
		$("#export_query").form('submit', {
			url : url,
			queryParams : exportParam,
			onSubmit : function() {
				console.log("正在导出,请稍后");
			},
			onLoadSuccess : function() {
				downloadForm.destoryForm();
			}
		});
	},
	exportShebaoCompany : function(type){
		console.log('exportExcel:' + type);
		var exportParam = {};
		var data = $('#datagrid_shebaoSummary').datagrid('getSelected');
		downloadForm.createForm();
		var url = "file/exportShebaoCompany.action";
		exportParam.configurable = 'reportForm';
		if (!type || type == 0) {
			delete exportParam.configurable;
			exportParam = data;
		}
		$("#export_query").form('submit', {
			url : url,
			queryParams : exportParam,
			onSubmit : function() {
				console.log("正在导出,请稍后");
			},
			onLoadSuccess : function() {
				downloadForm.destoryForm();
			}
		});
		
	},
	exportShebaoSummary : function(type){
		console.log('exportExcel:' + type);
		var exportParam = {};
		var form = $('#shebaoSummary_queryform');
		var data = getDataOfForm(form);
		downloadForm.createForm();
		var url = "file/exportShebaoSummary.action";
		exportParam.configurable = 'reportForm';
		if (!type || type == 0) {
			delete exportParam.configurable;
			if (data.succeed) {
				exportParam = data.data;
			}
		}
		$("#export_query").form('submit', {
			url : url,
			queryParams : exportParam,
			onSubmit : function() {
				console.log("正在导出,请稍后");
			},
			onLoadSuccess : function() {
				downloadForm.destoryForm();
			}
		});
		
	},
	onDblClickRow : function(index,row) {
		if(!shebaoCompanyEdit){
			session.editRow = $.extend({},row);
		}
		if (shebaoCompanyEdit != index) {
			if (employee.shebaoSummary.endEditing()) {
				$('#datagrid_shebaoCompany').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				shebaoCompanyEdit = index;
			} else {
				setTimeout(function() {$('#datagrid_shebaoCompany').datagrid('selectRow',shebaoCompanyEdit);
						}, 0);
			}
		}
		
	},
	endEditing : function() {
		if (shebaoCompanyEdit == undefined) {
			return true
		}
		if ($('#datagrid_shebaoCompany').datagrid('validateRow', shebaoCompanyEdit)) {
			$('#datagrid_shebaoCompany').datagrid('endEdit', shebaoCompanyEdit);
			shebaoCompanyEdit = undefined;
			return true;
		} else {
			return false;
		}
	},
	onEndEdit : function(index,row,changes) {
		var message = "您确定要更新吗？";
		var url = "employee/updateShebaoDetail.action";
		confirmDialog.createDialog(
				message,
				function(confirmId){
					var data = row;
					$.post(url, data, function(result){
//						var result =  eval("(" + result + ")");
						confirmDialog.destoryDialog(confirmId);
						if (!result.success) {
							$('#datagrid_shebaoCompany').datagrid('getData').rows[index] = session.editRow;
							$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
						}else{
							session.viewCompanyRow.subEndowmentIinsurance += (row.subEndowmentIinsurance -session.editRow.subEndowmentIinsurance);
							session.viewCompanyRow.subMedicare += ( row.subMedicare - session.editRow.subMedicare);
							session.viewCompanyRow.subUnemployedInsurance += (row.subUnemployedInsurance - session.editRow.subUnemployedInsurance );
							session.viewCompanyRow.subHouseIinsurance += (row.subHouseIinsurance - session.editRow.subHouseIinsurance);
							session.viewCompanyRow.cEndowmentIinsurance += (row.cEndowmentIinsurance - session.editRow.cEndowmentIinsurance);
							session.viewCompanyRow.cMedicare += ( row.cMedicare - session.editRow.cMedicare);
							session.viewCompanyRow.cUnemployedInsurance += (row.cUnemployedInsurance - session.editRow.cUnemployedInsurance);
							session.viewCompanyRow.cHouseIinsurance += (row.cHouseIinsurance - session.editRow.cHouseIinsurance);
							session.viewCompanyRow.cBirthIinsurance += (row.cBirthIinsurance - session.editRow.cBirthIinsurance);
							employee.shebaoSummary.updateDom(session.viewCompanyRow);
							$('#datagrid_shebaoSummary').datagrid('refreshRow',session.viewCompanyIndex);
						}
						session.editRow = {};
						$.messager.alert('消息', result.msg, 'info');
					});
				},function(){
					$('#datagrid_shebaoCompany').datagrid('getData').rows[index] = session.editRow;
					$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
				});
	},
	accept : function() {
		if (employee.shebaoSummaryendEditing()) {
			$('#datagrid_shebaoCompany').datagrid('acceptChanges');
		}
	},
	reject : function() {
		$('#datagrid_shebaoCompany').datagrid('rejectChanges');
		shebaoCompanyEdit = undefined;
	},
	getChanges : function() {
		var rows = $('#datagrid_shebaoCompany').datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
	},
	queryShebao : function(data, src) {
		$('#datagrid_shebaoCompany').datagrid({
			queryParams : data
		})
	},
	updateDom : function(row){
		$('#shebaoCompany_dyangl').html(row.subEndowmentIinsurance);
		$('#shebaoCompany_dyil').html(row.subMedicare);
		$('#shebaoCompany_dshiy').html(row.subUnemployedInsurance);
		$('#shebaoCompany_dzhuf').html(row.subHouseIinsurance);
		$('#shebaoCompany_cyangl').html(row.cEndowmentIinsurance);
		$('#shebaoCompany_cyil').html(row.cMedicare);
		$('#shebaoCompany_cshiy').html(row.cUnemployedInsurance);
		$('#shebaoCompany_czhuf').html(row.cHouseIinsurance);
		$('#shebaoCompany_cgongs').html(row.cInjuryInsurance);
		$('#shebaoCompany_cshengy').html(row.cBirthIinsurance);
	},
	generateCompanyType : function(){
		var type = $('#shebaoComany_sbtype').combobox('getValue');
		var company = $('#shebaoCompany_company').text();
		var data = {};
		if(type && ''!= type && company){
			data.type = type;
			data.company = company;
			var message = "确定要更新[" + company + "][" + type + "]的所有职员的" + type + "保险信息？";
			confirmDialog.createDialog(
					message,function(confirmId){
						$.post('employee/generateCompanyType.action',data,function(result){
//							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							$.messager.alert('消息', result.msg, 'info');
						});
					})
		}else{
			$.messager.alert('提示','请选择社保类型~');
		}
	},
	updateRadix : function(){
		var checkeds = $('#datagrid_shebaoCompany').datagrid('getChecked');
		var newRadix = Number($('#shebaoCompany_updateRadix').val());
		var newHouseHolde = $('#shebaoComany_company').combobox('getValue');
		if(checkeds.length === 0){
			$.messager.alert('提示', '您没有选择任何人');
			return;
		}
		var usernames = "";
		$.each(checkeds, function(n,obj){
			if(n<7){
				if(n === (checkeds.length -1)){
					usernames += obj.username;
				}else{
					usernames += (obj.username + ",");
				}
			}else if(n === 7){
				usernames += "..."
			}
		})
		var message = "您确定要更新：[" + checkeds[0].company + "]:<br/>" + usernames + "<br/>的基数为: " + newRadix + " 吗？";
		confirmDialog.createDialog(
				message,
				function(confirmId){
					$.each(checkeds,function(n,obj) {
						obj.radix = newRadix;
						obj.householdType = newHouseHolde; 
						wagesCalculate.calculateShebao(obj, function(result) {
							if (result) {
								$.post('employee/updateWagesRadix.action', obj, function(result){
									confirmDialog.destoryDialog(confirmId);
//									var result =  eval("(" + result + ")");
									if(result.success){
										var index = $('#datagrid_shebaoCompany').datagrid('getRowIndex',checkeds[n]);
										$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
									}else{
										$.messager.alert("更新", "[" + obj.username + "]" + result.msg + "失败,请您手动刷新页面。");
									}
								})
							} else {
								$.messager.alert('计算结果' , "[" + obj.username + ']计算失败');
							}
						})
					})
				}
		);
//		$.each(checkeds,function(n,obj) {
//			obj.radix = newRadix;
//			wagesCalculate.calculateShebao(obj, function(result) {
//				if (result) {
//					var index = $('#datagrid_shebaoCompany').datagrid('getRowIndex',checkeds[n]);
//					$('#datagrid_shebaoCompany').datagrid('refreshRow',index);
//				} else {
//					alert('计算失败');
//				}
//			})
//		})

	}
	},
	kaoqin : {
		queryKaoqin : function(data, src) {
			$('#datagrid_kaoqin').datagrid({
				queryParams : data
			})
		},
		locked : function(checked){
			var url = 'employee/lockedKaoqin.action';
//			var url = checked ? 'employee/lockedKaoqin.action':'employee/unlockeKaoqin.action';
			var data = {};
			data.locked = checked;
			$.post(url,data,function(result){
//				result = eval("("+result+")");
				if(result.success){
					disabledButton('kaoqin_toolbar',checked?checked:false);
					$('#kaoqin_generate').switchbutton('enable');
				}
//				$.messager.alert('提示',result.msg);
			})
		},
		view : function(){
			return;
		},
		onDblClickRow : function(index,row) {
			if(!kaoqinEdit){
				session.editKaoqinRow = $.extend({},row);
			}
			if (kaoqinEdit != index) {
				if (employee.shebaoSummary.endEditing()) {
					$('#datagrid_kaoqin').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					kaoqinEdit = index;
				} else {
					setTimeout(function() {$('#datagrid_kaoqin').datagrid('selectRow',kaoqinEdit);
							}, 0);
				}
			}
			
		},
		endEditing : function() {
			if (kaoqinEdit == undefined) {
				return true
			}
			if ($('#datagrid_kaoqin').datagrid('validateRow', kaoqinEdit)) {
				$('#datagrid_kaoqin').datagrid('endEdit', kaoqinEdit);
				kaoqinEdit = undefined;
				return true;
			} else {
				return false;
			}
		},
		onEndEdit : function(index,row,changes) {
//			var message = "您确定要更新吗？";
			var url = "employee/updateKaoqin.action";
//			confirmDialog.createDialog(
//					message,
//					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
//							var result =  eval("(" + result + ")");
//							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								$('#datagrid_kaoqin').datagrid('getData').rows[index] = session.editKaoqinRow;
								$('#datagrid_kaoqin').datagrid('refreshRow',index);
							}
							session.editKaoqinRow = {};
							$.messager.alert('消息', result.msg, 'info');
						});
//					},function(){
//						$('#datagrid_kaoqin').datagrid('getData').rows[index] = session.editKaoqinRow;
//						$('#datagrid_kaoqin').datagrid('refreshRow',index);
//						session.editKaoqinRow = {};
//					});
		},
		openWagesDate : function(){
			$('#dialog_wagesDate').window('open').window(
					'resize',
					{
						top : $(document).scrollTop()
								+ ($(window).height() - 480) * 0.5
					});
		},
		selectMonth : function(dom){
			var data = {};
			data.wagesMonth = dom.val();
			$('#datagrid_wagesDate').datagrid({queryParams : data});
		},
		generateWagesDate : function(){
			  var calendar = $('#calendar_wagesDate').calendar('options');
			    var data = {};
			    data.year = calendar.year;
			    data.month = calendar.month;
			    $.post('employee/generateWagesDates.action', data, function(result){
//					var result =  eval("(" + result + ")");
					var saveNums = 0;
					if(result.success){
						var query = "";
						query = data.year + "." + (data.month<10?"0"+data.month:data.month);
						$('#datagrid_wagesDate').datagrid({queryParams : {wagesMonth:query}});
						saveNums = Number(result.msg);
					}
					$.messager.alert('消息', "成功更新/插入数据库" + saveNums + "条数据！");
				});
		},
		wagesDateDblCickRow : function(index,row){
			onDblClickRow(index,row,'datagrid_wagesDate');
		},
		wagesDateEndEditing : function(){
			endEditing('datagrid_wagesDate');
		},
		wagesDateEndEdit : function(index,row,changes){
//			var message = "你确定要更新吗?";
			var url = "employee/updateWagesDate.action";
//			if(wagesDateAdd){
//				message = "您确定要增加吗？";
//			}
//			confirmDialog.createDialog(
//					message,
//					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
//							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								if(wagesDateAdd){
									removeIt('datagrid_wagesDate');
									wagesDateAdd = undefined;
									return;
								}
								$('#datagrid_wagesDate').datagrid('getData').rows[index] = session.dEditRow;
								$('#datagrid_wagesDate').datagrid('refreshRow',index);
							}else{
								if(wagesDateAdd){
									$('#datagrid_wagesDate').datagrid('reload');
									shebaoAdd = undefined;
									return;
								}
							}
							$.messager.alert('消息', result.msg, 'info');
						});
//					},function(){
//						if(wagesDateAdd){
//							removeIt('datagrid_wagesDate');
//							wagesDateAdd = undefined;
//							return;
//						}
//						$('#datagrid_wagesDate').datagrid('getData').rows[index] = session.dEditRow;
//						var selectedData = $('#datagrid_wagesDate').datagrid('getSelected');
//						session.dEditRow = $.extend({},selectedData);
//						$('#datagrid_wagesDate').datagrid('refreshRow',index);
//					});
		},
		wagesDateDelete : function(){
			var selectedRow = $('#datagrid_wagesDate').datagrid('getSelected');
			var message = "您确定要删除？";
			confirmDialog.createDialog(
					message,function(confirmId){
						$.post('employee/deleteWagesDate.action',selectedRow,function(result){
//							var result =  eval("(" + result + ")");
							confirmDialog.destoryDialog(confirmId);
							if(result.success){
								$('#datagrid_wagesDate').datagrid('reload');
							}
							$.messager.alert('消息', result.msg, 'info');
						});
					})
		},
		openGenerateKaoqin : function(){
			var date = new Date();
			var month = date.getMonth();
			var message = "在批量生成考勤表前，是否检查一下(" + month + "月、" + (month - 1) + "月)工作日表？";
			confirmDialog.createDialog(
					message,function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							employee.kaoqin.openWagesDate();
						},function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							employee.kaoqin.generateKaoqin();
							});
		},
		generateKaoqin : function(){
			var message = "现有的考勤表将被清除，您确定要重新生成当月考勤表吗？";
			confirmDialog.createDialog(
					message,function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							$.post('employee/generateKaoqin.action',{},function(result){
//								var result =  eval("(" + result + ")");
								if(result.success){
									$('#datagrid_kaoqin').datagrid('reload');
								}
								$.messager.alert('消息', result.msg, 'info');
							})
						},undefined,'confirmId2');
		},
		exportKaoqinExcel : function(type){
			console.log('exportExcel:' + type);
			var exportParam = {};
			var form = $('#kaoqin_form');
			var data = getDataOfForm(form);
			downloadForm.createForm();
			var url = "file/exportKaoqinExcel.action";
			exportParam.configurable = 'reportForm';
			if (!type || type == 0) {
				delete exportParam.configurable;
				if (data.succeed) {
					exportParam = data.data;
				}
			}
			$("#export_query").form('submit', {
				url : url,
				queryParams : exportParam,
				onSubmit : function() {
					console.log("正在导出,请稍后");
				},
				onLoadSuccess : function() {
					downloadForm.destoryForm();
				}
			});
		},
		deleteKaoqin : function(confirmId){
			confirmDialog.destoryDialog(confirmId);
			var kaoqinInfo = $('#datagrid_kaoqin').datagrid('getSelected');
			var url = "employee/deleteKaoqin.action";
			$.post(url, kaoqinInfo, function(result) {
//				var result =  eval("(" + result + ")");
				if(result.success){
					$('#datagrid_kaoqin').datagrid('reload');
				}
					$.messager.alert(result.msg,result.msg);
			})
			
		},
		openAddKaoqin : function(){
			var rights = session.user.roleId;
			$('#kaoqin_add_dialog').dialog({
				title : '增加员工考勤'
			});
			$('#kaoqin_add_dialog').window('open').window(
					'resize',
					{top : $(document).scrollTop()+ ($(window).height() - 480) * 0.5});
		},
		addKaoqin : function(){
			
		},
		queryUsername : function(data, src){
			$('#datagrid_kaoqin_username').datagrid({
				queryParams : data
			})
		}
	},
	monthWages : {
		queryMonthWages : function(data, src) {
			$('#datagrid_monthWages').datagrid({
				queryParams : data
			})
		},
		onDblClickRow : function(index,row){
			onDblClickRow(index,row,'datagrid_monthWages');
		},
		endEditing : function(){
			endEditing('datagrid_monthWages');
		},
		onEndEdit : function(index,row,changes){
			var url = "employee/updateMonthWages.action";
//			var message = "你确定要更新吗?";
//			if(monthWagesAdd){
//				message = "您确定要增加吗？";
//			}
//			confirmDialog.createDialog(
//					message,
//					function(confirmId){
						var data = row;
						$.post(url, data, function(result){
//							var result =  eval("(" + result + ")");
//							confirmDialog.destoryDialog(confirmId);
							if (!result.success) {
								if(wagesDateAdd){
									removeIt('datagrid_monthWages');
									wagesDateAdd = undefined;
									$.messager.alert('消息', result.msg, 'info');
									return;
								}
								$('#datagrid_monthWages').datagrid('getData').rows[index] = session.dEditRow;
								$('#datagrid_monthWages').datagrid('refreshRow',index);
								$.messager.alert('消息', result.msg, 'info');
							}else{
								if(wagesDateAdd){
									$('#datagrid_monthWages').datagrid('reload');
									shebaoAdd = undefined;
									return;
								}
								$('#datagrid_monthWages').datagrid('reload');
							}
						});
//					},function(){
//						if(wagesDateAdd){
//							removeIt('datagrid_monthWages');
//							wagesDateAdd = undefined;
//							return;
//						}
//						$('#datagrid_monthWages').datagrid('getData').rows[index] = session.dEditRow;
//						var selectedData = $('#datagrid_monthWages').datagrid('getSelected');
//						session.dEditRow = $.extend({},selectedData);
//						$('#datagrid_monthWages').datagrid('refreshRow',index);
//					});
		},
		generateMothWages : function(){
			var message = "现有的当月工资表将被清除，您确定要重新生成当月工资表吗？";
			confirmDialog.createDialog(
					message,function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							$.post('employee/generateMonthWages.action',{},function(result){
//								var result =  eval("(" + result + ")");
								if(result.success){
									$('#datagrid_monthWages').datagrid('reload');
								}
								$.messager.alert('消息', result.msg, 'info');
							})
						});
		},
	
		
		exportMonthWagesExcel : function(type){
			console.log('exportExcel:' + type);
			var exportParam = {};
			var form = $('#monthWages_form');
			var data = getDataOfForm(form);
			downloadForm.createForm();
			var url = "file/exportMonthWagesExcel.action";
			exportParam.configurable = 'reportForm';
			if (!type || type == 0) {
				delete exportParam.configurable;
				if (data.succeed) {
					exportParam = data.data;
				}
			}
			$("#export_query").form('submit', {
				url : url,
				queryParams : exportParam,
				onSubmit : function() {
					console.log("正在导出,请稍后");
				},
				onLoadSuccess : function() {
					downloadForm.destoryForm();
				}
			});
		},
		deleteMonthWage : function(){
			var monthInfo = ("");
		},
		fafang : function(){
			confirmDialog.createDialog(
					'您确定工资已经发放完毕了吗？',function(confirmId){
							confirmDialog.destoryDialog(confirmId);
							$.post('employee/fangfa.action',null,function(result){
//								result = eval("("+result+")");
								$.messager.alert('发放提示',result.msg); 
							})
						});
		}
	},
	gongzihuizong:{
		
		querygongzihuizong : function(data, src) {
			$('#datagrid_gongzihuizong').datagrid({
				queryParams : data
			})
		}
	},	
	jiagou:{
		queryJiagou : function(data, src) {
			var level = data.level;
			var name = data.name;
			var jiagou = session.jiagou;
			var newJiagou = [];
			if(name && name!=''){
				if(level && level != ''){
					var levelIndex = jiagou.selectLike("_" + level,undefined,false,true);
					for (var i = 0; i < levelIndex.length; i++) {
						newJiagou.push(jiagou[levelIndex[i]]);
					}
					jiagou = newJiagou;
				}
				newJiagou = [];
				var nameIndex = jiagou.selectLike(name);
				for (var i = 0; i < nameIndex.length; i++) {
					newJiagou.push(jiagou[nameIndex[i]].split("_")[1]);
				}
				employee.jiagou.openJiagou(newJiagou);
			}
		},
		openJiagou : function(array, id){
			id = (id?id:"treegrid_jiagou");
			$("#"+id).treegrid('collapseAll');
			for (var i = 0; i < array.length; i++) {
				$("#"+id).treegrid('expandTo',array[i]);
				if(0 === i){
					$("#"+id).treegrid('select',array[i]);
				}
			}
		},
		add : function(){
			var parent = $('#treegrid_jiagou').treegrid('getSelected');
			if(parent){
				var level = parent.level + 1;
				var firstLevel;
				var secondLevel;
				var thirdLevel;
				var icon = 'icon-organisation';
				var preId = parent.id;
				if(level == 2){
					firstLevel = parent.fourthLevel;
				}else if(level == 3){
					firstLevel = parent.firstLevel;
					secondLevel = parent.fourthLevel;
				}else if(level == 4){
					firstLevel = parent.firstLevel;
					secondLevel = parent.secondLevel;
					thirdLevel = parent.fourthLevel;
					icon = 'icon-man';
				}
				jiagouAdd = -1;
				$('#treegrid_jiagou').treegrid('append',{
					parent: preId,
					data: [{
						id:-1,
						firstLevel : firstLevel,
						secondLevel : secondLevel,
						thirdLevel : thirdLevel,
						fourthLevel:'正在添加...',
						iconCls:icon,
						level:level,
						total:0,
						onJob:0,
						preId:preId,
						sortCode:parent.sortCode,
						taxStructure:parent.taxStructure,
						guidance:parent.guidance,
						guidanceEmail:parent.guidanceEmail
					}]
				});
				jiagouEdit = -1;
				$('#treegrid_jiagou').treegrid('select',-1);
				employee.jiagou.edit();
			}
		},
		edit : function(){
			if (jiagouEdit != undefined){
				$('#treegrid_jiagou').treegrid('select', jiagouEdit);
				$('#treegrid_jiagou').treegrid('beginEdit', jiagouEdit);
				return;
			}
			var row = $('#treegrid_jiagou').treegrid('getSelected');
			if (row){
				jiagouEdit = row.id
				$('#treegrid_jiagou').treegrid('beginEdit', jiagouEdit);
			}
		},
		save : function(){
			if (jiagouEdit != undefined){
				var t = $('#treegrid_jiagou');
				var data = t.treegrid('find',jiagouEdit)
				t.treegrid('endEdit', jiagouEdit);
				jiagouEdit = undefined;
				jiagouAdd = undefined;
				$.post('employee/updateOrsaveOS.action',data,function(result){
//					result = eval("(" + result + ")");
					if(result.success){
//						t.treegrid('reload');
						t.treegrid({onLoadSuccess:function(){
//							if(jiagouEdit){
//								t.treegrid('expandTo',jiagouEdit);
//								t.treegrid('select', jiagouEdit);
//								jiagouEdit = undefined;
//								$.messager.alert('保存提示：',result.msg);
//							}
						}})
//						employee.jiagou.remove();
					}else{
//						jiagouEdit = undefined;
						$.messager.alert('保存提示：',result.msg);
					}
				})
			}
		},
		cancel : function(){
			if (jiagouEdit != undefined){
				if(jiagouAdd != undefined){
					$('#treegrid_jiagou').treegrid('pop', jiagouEdit);
					jiagouAdd = undefined;
					jiagouEdit = undefined;
					return ;
				}
				$('#treegrid_jiagou').treegrid('cancelEdit', jiagouEdit);
				jiagouEdit = undefined;
			}
		},
		remove : function(){
			var data = {};
			if (jiagouEdit != undefined){
				data = $('#treegrid_jiagou').treegrid('find',jiagouEdit);
			}else{
				data = $('#treegrid_jiagou').treegrid('getSelected');
			}
			jiagouEdit = data.id;
			if (data){
				$.post('employee/deleteOS.action',data,function(result){
//					result = eval("(" + result + ")");
					if(result.success){
						$('#treegrid_jiagou').treegrid('pop', jiagouEdit);
						jiagouEdit = undefined;
					}
					$.messager.alert('删除提示：',result.msg);
				})
			}
		},
		exportExcel : function(flag){
			downloadForm.createForm();
			var url = flag ? "file/exportOStruct.action":"file/exportOS.action";
			$("#export_query").form('submit', {
				url : url,
				onSubmit : function() {
					console.log("正在导出,请稍后");
				},
				onLoadSuccess : function() {
					downloadForm.destoryForm();
				}
			});
		}
	}
}
var wagesDateAdd = undefined;
var monthWagesAdd = undefined;
var shebaoAdd = undefined;
var shebaoEdit = undefined;
var shebaoCompanyEdit = undefined;
var kaoqinEdit = undefined;
var jiagouEdit = undefined;
var jiagouAdd = undefined;