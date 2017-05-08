//菜单编辑========start
var menuEdit = undefined;
var menuAdd = undefined;
var system = {
		menus:{
			queryMenus : function(data, src) {
				var level = (data.menuLevel && data.menuLevel!='') ? data.menuLevel : undefined;
				var name = data.menuName;
				var arry = [];
				var data = $('#menus_treeGrid').treegrid('getData');
				getChildrens(data, arry);
				var flag = true;
				$.each(arry, function(i, obj){
					if(obj.menuName.isLike(name)){
						if(level){
							flag = (obj.menuLevel == level);
						}
						if(flag){
							$('#menus_treeGrid').treegrid('expandTo',array[i]);
							$('#menus_treeGrid').treegrid('select',array[i]);
						}
					}
				})
			},
			add : function(){
				
				var parent = $('#menus_treeGrid').treegrid('getSelected');
				var data = $.extend(true,{},parent);
				delete data.children;
				delete data.state;
				if(parent){
					data.menuId = -1;
					data.menuLevel = parent.menuLevel + 1;
					data._preMenuId = parent.menuId;
					data._parentId = parent.menuId;
					if(parent.sortCode.match(/^[A-Za-z]+/).length>0){
						try{
							var tempChar =charCode.getNextChar(parent.sortCode.substring(0,1)) || "Z";
							data.sortCode = tempChar + parent.sortCode.substring(1);
						}catch(e){
							console.log("自动添加sortCode失败...")
						}
					}
					menuAdd = -1;
					$('#menus_treeGrid').treegrid('append',{
						parent: data._parentId,
						data: [data]
					});
					menuEdit = -1;
					$('#menus_treeGrid').treegrid('expandTo',-1);
					$('#menus_treeGrid').treegrid('select',-1);
					system.menus.edit();
				}
			},
			edit : function(){
				if (menuEdit != undefined){
					$('#menus_treeGrid').treegrid('select', menuEdit);
					$('#menus_treeGrid').treegrid('beginEdit', menuEdit);
					return;
				}
				var row = $('#menus_treeGrid').treegrid('getSelected');
				if (row){
					menuEdit = row.menuId
					$('#menus_treeGrid').treegrid('beginEdit', menuEdit);
				}
			},
			save : function(){
				var url = menuAdd ? "system/saveMenu.action" : "system/updateMenu.action";
				if (menuEdit != undefined){
					var t = $('#menus_treeGrid');
					var data = t.treegrid('find',menuEdit)
					data.preid = data._preMenuId;
					data.url = data.url || '';
					t.treegrid('endEdit', menuEdit);
					var tempIndex = menuEdit;
					menuEdit = undefined;
					menuAdd = undefined;
					$.post(url,data,function(result){
//						if(result.success){
//							if(result.obj && result.obj != ''){
//								 
////								t.treegrid('find',tempIndex).menuId = result.obj;
//							}
//						}
						$('#menus_treeGrid').treegrid('reload',data.preid);
						$.messager.alert('保存提示：',result.msg);
					})
				}
			},
			cancel : function(){
				if (menuEdit != undefined){
					if(menuAdd != undefined){
						$('#menus_treeGrid').treegrid('pop', menuEdit);
						menuAdd = undefined;
						menuEdit = undefined;
						return ;
					}
					$('#menus_treeGrid').treegrid('cancelEdit', menuEdit);
					menuEdit = undefined;
				}
			},
			remove : function(){
				var data = {};
				var flag = true;
				if (menuEdit != undefined){
					data = $('#menus_treeGrid').treegrid('find',menuEdit);
				}else{
					data = $('#menus_treeGrid').treegrid('getSelected');
				}
				menuEdit = data.menuId;
				if (data){
					if(data.menuLevel<3){
						$.messager.confirm("提示：","该目录为高级目录，删除操作将同时删除它的子集目录，请确认！", function(ok){
							if(ok){
								$.post('system/removeMenus.action',data,function(result){
									if(result.success){
										$('#menus_treeGrid').treegrid('pop', menuEdit);
										menuEdit = undefined;
									}
									$('#menus_treeGrid').treegrid('reload',data.preid);
									$.messager.alert('删除提示：',result.msg);
								})
							}
						})
					}else{
						$.post('system/removeMenus.action',data,function(result){
							if(result.success){
								$('#menus_treeGrid').treegrid('pop', menuEdit);
								menuEdit = undefined;
							}
							$('#menus_treeGrid').treegrid('reload',data.preid);
							$.messager.alert('删除提示：',result.msg);
						})
					}
				}
			},
			upLevel: function(){
				var row = $('#menus_treeGrid').treegrid('getSelected');
				var preMenu = $('#menus_treeGrid').treegrid('getParent',row.menuId);
				if(preMenu){
					row.preid = preMenu._preMenuId;
					if(row){
						$.messager.confirm("提示：","您确定要提高[" + row.menuName + "]的级别吗？",function(ok){
							if(ok){
								$.post("system/updateLevel.action", row, function(result){
//									if(result.success){
////										$('#menus_treeGrid').treegrid({
////											onLoadSuccess:function(){
////												$('#menus_treeGrid').treegrid('expandTo',row.menuId);
////												$('#menus_treeGrid').treegrid('select',row.menuId);
////											}
////										});
////										$('#menus_treeGrid').treegrid('reload');
//									}
									$('#menus_treeGrid').treegrid('reload',row.preid);
									$.messager.alert("提示：",result.msg);
								})
							}
						});
					}
				}else{
					$.messager.alert("该记录已经是最高级别了。");
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
		},
		roleMange: {
		findRole: function(data){
			var bootNode = $('#roles_treeGrid').treegrid('getData');
			var array = system.roleMange.getNodes(bootNode);
			$.each(array, function(i, obj){
				if(obj.roleName.indexOf(data.roleName)>=0){
					$('#roles_treeGrid').treegrid('expandTo',obj.roleId);
					$('#roles_treeGrid').treegrid('select',obj.roleId);
				}
			})
		},
		menu2role: function(menusId, roleId){
			menusId = menusId || "menuList";
			roleId = roleId || "roleResources";
			var selected = $('#' + menusId).treegrid('getSelected');
			if(selected){
				if($('#' + roleId).treegrid('find',selected.menuId)){
					return false;
				};
				var data = {};
				var menus = system.roleMange.getMenuNodeIds(selected);
				data.roleId = $("#role_id").val();
				data.ids = menus;
				data.sid = selected.menuId;
				if(data.roleId && data.sid ){
					$.post('system/addRoleMenus.action', data, function(result){
						if(result.success){
							system.roleMange.addPreNodes(selected);
							system.roleMange.appendNodes(selected._parentId, [selected]);
						}
						$.messager.alert("提示：", result.msg);
					})
				}else{
					$.messager.alert("提示：", "获取角色信息失败，请确认。");
				}
			}else{
				$.messager.alert("提示：", "请先选择菜单！")
			}
		},
		deleteFromRole: function(roleId){
			roleId = roleId || "roleResources"
			var selected = $('#' + roleId).treegrid('getSelected');
			if(selected){
				var data = {};
				var menus = system.roleMange.getMenuNodeIds(selected);
				data.ids = menus;
				data.roleId = $("#role_id").val();
				if(data.roleId){
					$.post('system/removeRoleMenus.action', data, function(result){
						if(result.success){
							$('#' + roleId).treegrid('remove', selected.menuId);
						}
						$.messager.alert("提示：", result.msg);
					})
				}
			}else{
				$.messager.alert("提示：", "请先选择角色菜单！");
			}
		},
		/**
		 *
		 * @param praent int
		 * @param data constructor is  Array()
		 */
		appendNodes: function(praent, data){
			$('#roleResources').treegrid('append',{
				parent: praent,
				data: data
			});
			$('#roleResources').treegrid('expandTo',data[0].menuId);
			$('#roleResources').treegrid('select',data[0].menuId);
		},
		addPreNodes: function(menu){
			if(!menu) return ;
			var preId = menu._parentId;
			var preNode = undefined;
			if(preId){
				preNode = $('#roleResources').treegrid('find',preId);
				if(!preNode){
					preNode = $.extend(true, {}, $('#menuList').treegrid('find',preId));
					preNode.children = [];
					system.roleMange.addPreNodes(preNode);
					system.roleMange.appendNodes(preNode._parentId, [preNode]);
				}
			}
		},
		openWindow: function(row){
			$('#role_id').val(row.roleId);
			$('#role_manager_dialog').window('open');
			$('#roleResources').treegrid({ //本角色菜单
				url:'system/queryResourceById.action',
				title: row.roleName + "权限",
				queryParams: {
					rid: row.roleId
				}
			});
			var parent = $('#roles_treeGrid').treegrid('getParent',row.roleId);
			var parentId = parent ? (parent.roleId || 1) : 1;
			var title = parentId == 1 ? "超级管理员权限" : parent.roleName + "权限";
			$('#menuList').treegrid({ //父级菜单
				url:'system/queryResourceById.action',
				title: title,
				queryParams: {
					rid: parentId
				}
			});
		},
		getMenuNodeIds: function(objs, arry){
			arry = arry || [];
//					var tempObj = undefined;
			if(objs){
				if(objs.constructor.name !== "Array"){
					objs = [objs];
				}
				$.each(objs, function(i, obj){
//							tempObj = $.extend(true,{},obj);
//							delete tempObj.children;
					arry.push(obj.menuId);
					if(obj.children && obj.children.length>0){
						system.roleMange.getMenuNodeIds(obj.children, arry);
					}
				})
			}
			return arry;
		},
		getNodes: function(objs, arry){
			arry = arry || [];
			var tempObj = undefined;
			if(objs){
				if(objs.constructor.name !== "Array"){
					objs = [objs];
				}
				$.each(objs, function(i, obj){
					tempObj = $.extend(true,{},obj);
					delete tempObj.children;
					arry.push(tempObj);
					if(obj.children && obj.children.length>0){
						system.roleMange.getNodes(obj.children, arry);
					}
				})
			}
			return arry;
		},
		openAdd: function(){
			var selected = $('#roles_treeGrid').treegrid('getSelected');
			$('#role_form .easyui-linkbutton').linkbutton('enable')
			if(selected){
				if((Number(selected.roleLevel)+1)<4){
					$('#role_form').form('clear');
//						$('#preId_hidden').val(selected.roleId);
					$('#role_form').form('load',{
						preId: selected.roleId,
						roleLevel: (Number(selected.roleLevel)+1),
						preName: selected.roleName,
					});
					$('#roleInfo').dialog('open');
				}
			}else{
				$.messager.alert("提示：","请先选择角色。")
			}
		},
		addRole: function(dom){
			var preId = $('#preId_hidden').val();
			$('#role_form').form({
				url : 'system/addRole.action'
			});
			if(preId){
				submitForm(dom,function(result){
					if(result.success){
						$('#roles_treeGrid').treegrid('reload',preId);
						$('#roleInfo').dialog('close')
					}
					$.messager.alert("提示：",result.msg);
				})
			}else{
				$.messager.alert('提示：','父级id不存在，请重新打开添加窗口。');
			}
		},
		removeRoel: function(){
			var selected = $('#roles_treeGrid').treegrid('getSelected');
			$.post('system/removeRole.action',{rid: selected.roleId},function(result){
				if(result.success){
					$('#roles_treeGrid').treegrid('remove', selected.roleId);
				}
				$.messager.alert("提示：",result.msg);
			})
		}
	}
}
//字符ASCII码
var charCode = {
	ascii2char: {65:'A',66:'B',67:'C',68:'D',69:'E',70:'F',71:'G',72:'H',73:'I',74:'J',75:'K',76:'L',77:'M',78:'N',79:'O',80:'P',81:'Q',82:'R',83:'S',84:'T',85:'U',86:'V',87:'W',88:'X',89:'Y',90:'Z',
		97:'a',98:'b',99:'c',100:'d',101:'e',102:'f',103:'g',104:'h',105:'i',106:'j',107:'k',108:'l',109:'m',110:'n',111:'o',112:'p',113:'q',114:'r',115:'s',116:'t',117:'u',118:'v',119:'w',120:'x',121:'y'},
	char2ascii:{A:65,B:66,C:67,D:68,E:69,F:70,G:71,H:72,I:73,J:74,K:75,L:76,M:77,N:78,O:79,P:80,Q:81,R:82,S:83,T:84,U:85,V:86,W:87,X:88,Y:89,Z:90,
		a:97,b:98,c:99,d:100,e:101,f:102,g:103,h:104,i:105,j:106,k:107,l:108,m:109,n:110,o:111,p:112,q:113,r:114,s:115,t:116,u:117,v:118,w:119,x:120,y:121},
	getNextChar: function(cr){
		var ascii = cr;
		if(typeof ascii === 'string'){
			ascii = (charCode.char2ascii[cr] + 1) || 90;
		}
		return charCode.ascii2char[ascii];
	}
}
var getChildrens = function(objs, arry){
	$.each(objs, function(i, obj){
		arry.push(obj);
		if(obj.children && obj.children.length>0){
			getChildrens(obj.children, arry);
		}
		arry.children = undefined;
	})
}
