var system = {
		menus : {
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
						$.messager.alert('保存提示：',result.msg);
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
						$.messager.alert('删除提示：',result.msg);
					})
				}
			},
			exportExcel : function(){
				downloadForm.createForm();
				var url = "file/exportOS.action";
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
var menusEdit = undefined;
var menusAdd = undefined;