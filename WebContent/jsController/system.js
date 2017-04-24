var system = {
		menus2 : {
			queryMenus : function(data, src) {
				var level = data.menuLevel;
				var name = data.menuName;
				var menus = session.menus;
				var newMenu = [];
				if(name && name!=''){
					var nameIndex = menus.selectLike(name);
					for (var i = 0; i < nameIndex.length; i++) {
						newMenu.push(menus[nameIndex[i]].split("_")[1]);
					}
					employee.menus.openMenus(newMenu);
				}
			},
			openMenus : function(array, id){
				id = (id?id:"treegrid_menus");
				$("#"+id).treegrid('collapseAll');
				for (var i = 0; i < array.length; i++) {
					$("#"+id).treegrid('expandTo',array[i]);
					if(0 === i){
						$("#"+id).treegrid('select',array[i]);
					}
				}
			},
			add : function(){
				var parent = $('#treegrid_menus').treegrid('getSelected');
				if(parent){
					var level = parent.menuLevel + 1;
					var sortCode;
					var icon = 'icon-organisation';
					var preId = parent.id;
					if(level == 2){
						icon = 'icon-page';
					}else if(level == 3){
						icon = 'icon-page';
					}else{
						firstLeve = parent.firstLeve;
						secondLevel = parent.secondLevel;
						thirdLevel = parent.fourthLevel;
						icon = 'icon-man';
					}
					menusAdd = -1;
					$('#treegrid_menus').treegrid('append',{
						parent: preId,
						data: [{
							id:-1,
							firstLeve : firstLeve,
							secondLevel : secondLevel,
							thirdLevel : thirdLevel,
							fourthLevel:'�������...',
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
					menusEdit = -1;
					$('#treegrid_menus').treegrid('select',-1);
					employee.menus.edit();
				}
			},
			edit : function(){
				if (menusEdit != undefined){
					$('#treegrid_menus').treegrid('select', menusEdit);
					$('#treegrid_menus').treegrid('beginEdit', menusEdit);
					return;
				}
				var row = $('#treegrid_menus').treegrid('getSelected');
				if (row){
					menusEdit = row.id
					$('#treegrid_menus').treegrid('beginEdit', menusEdit);
				}
			},
			save : function(){
				if (menusEdit != undefined){
					var t = $('#treegrid_menus');
					var data = t.treegrid('find',menusEdit)
					t.treegrid('endEdit', menusEdit);
					menusEdit = undefined;
					menusAdd = undefined;
					$.post('employee/updateOrsaveOS.action',data,function(result){
//						result = eval("(" + result + ")");
						if(result.success){
							t.treegrid('reload');
//						employee.menus.remove();
						}
						$.messager.alert('������ʾ��',result.msg);
					})
				}
			},
			cancel : function(){
				if (menusEdit != undefined){
					if(menusAdd != undefined){
						$('#treegrid_menus').treegrid('pop', menusEdit);
						menusAdd = undefined;
						menusEdit = undefined;
						return ;
					}
					$('#treegrid_menus').treegrid('cancelEdit', menusEdit);
					menusEdit = undefined;
				}
			},
			remove : function(){
				var data = {};
				if (menusEdit != undefined){
					data = $('#treegrid_menus').treegrid('find',menusEdit);
				}else{
					data = $('#treegrid_menus').treegrid('getSelected');
				}
				menusEdit = data.id;
				if (data){
					$.post('employee/deleteOS.action',data,function(result){
//						result = eval("(" + result + ")");
						if(result.success){
							$('#treegrid_menus').treegrid('pop', menusEdit);
							menusEdit = undefined;
						}
						$.messager.alert('ɾ����ʾ��',result.msg);
					})
				}
			},
			exportExcel : function(){
				downloadForm.createForm();
				var url = "file/exportOS.action";
				$("#export_query").form('submit', {
					url : url,
					onSubmit : function() {
						console.log("���ڵ���,���Ժ�");
					},
					onLoadSuccess : function() {
						downloadForm.destoryForm();
					}
				});
			}
		},
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
			debugger;
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
						console.log("�Զ����sortCodeʧ��...")
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
				t.treegrid('endEdit', menuEdit);
				menuEdit = undefined;
				menuAdd = undefined;
				$.post(url,data,function(result){
					if(result.success){
						if(result.obj && result.obj != ''){
							t.treegrid('find',menuEdit).menuId = result.obj;
						}
					}
					$.messager.alert('������ʾ��',result.msg);
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
					$.messager.confirm("��ʾ��","��Ŀ¼Ϊ�߼�Ŀ¼��ɾ��������ͬʱɾ�������Ӽ�Ŀ¼����ȷ�ϣ�", function(ok){
						if(ok){
							$.post('system/removeMenus.action',data,function(result){
								if(result.success){
									$('#menus_treeGrid').treegrid('pop', menuEdit);
									menuEdit = undefined;
								}
								$.messager.alert('ɾ����ʾ��',result.msg);
							})
						}
					})
				}else{
					$.post('system/removeMenus.action',data,function(result){
						if(result.success){
							$('#menus_treeGrid').treegrid('pop', menuEdit);
							menuEdit = undefined;
						}
						$.messager.alert('ɾ����ʾ��',result.msg);
					})
				}
			}
		},
		upLevel: function(){
			var row = $('#menus_treeGrid').treegrid('getSelected');
			if(row){
				$.messager.confirm("��ʾ��","��ȷ��Ҫ���[" + row.menuName + "]�ļ�����",function(ok){
					if(ok){
						$.post("system/upMenuLevel.action",{menuId: row.menuId}, function(result){
							if(result.success){
								$('#menus_treeGrid').treegrid({
									onLoadSuccess:function(){
										$('#menus_treeGrid').treegrid('expandTo',row.menuId);
										$('#menus_treeGrid').treegrid('select',row.menuId);
									}
								});
								$('#menus_treeGrid').treegrid('reload');
							}
							$.messager.alert("��ʾ��",result.msg);
						})
					}
				});
			}
		},
		downLevel: function(){
			var row = $('#menus_treeGrid').treegrid('getSelected');
			if(row){
				$.messager.confirm("��ʾ��","��ȷ��Ҫ����[" + row.menuName + "]�ļ�����",function(ok){
					if(ok){
						$.post("system/downMenuLevel.action",{menuId: row.menuId}, function(result){
							if(result.success){
								$('#menus_treeGrid').treegrid({
									onLoadSuccess:function(){
										$('#menus_treeGrid').treegrid('expandTo',row.menuId);
										$('#menus_treeGrid').treegrid('select',row.menuId);
									}
								});
								$('#menus_treeGrid').treegrid('reload');
							}
							$.messager.alert("��ʾ��",result.msg);
						})
					}
				});
			}
		},
		exportExcel : function(flag){
			downloadForm.createForm();
			var url = flag ? "file/exportOStruct.action":"file/exportOS.action";
			$("#export_query").form('submit', {
				url : url,
				onSubmit : function() {
					console.log("���ڵ���,���Ժ�");
				},
				onLoadSuccess : function() {
					downloadForm.destoryForm();
				}
			});
		}
	}
}
// �ַ�ASCII��
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
		debugger;
		arry.push(obj);
		if(obj.children && obj.children.length>0){
			getChildrens(obj.children, arry);
		}
		arry.children = undefined;
	})
}
var menusEdit = undefined;
var menusAdd = undefined;