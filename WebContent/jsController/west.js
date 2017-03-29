var west = {
	initWestTree : function() {
		$.ajax({
			type : 'GET',
			dataType : "json",
			url : 'menu/allMenu.action',
			success : function(data) {
//				console.log('后台传过来的值o%', data);
				$.each(data, function(i, n) {
					if (n.uls) {
						$('#menu').accordion('add', {
							title : n.menuName,
							id : 'menu_' + n.menuId,
							selected : false,
							content : '<ul id ="ulMenu_' + n.menuId + '"></ul>'
						});
						west.getSmallMenus(n.uls, n.menuId);
					} else {

					}
				});
				$('#menu').accordion({
					autoHeight : false,
					navigator : true
				});
			}
		});
		/*$('#menu').accordion({
			autoHeight : false,
			navigator : true
		});*/
	},
	getSmallMenus : function(data, menuId, callback) {//获取ul小菜单
//		console.log(data);
		$('#ulMenu_' + menuId).tree(
				{
					data : data,
					onClick : function(node) {
						if (node) {
							west.openTab(node.text, node.url);
						}
					}
				});
	},
	openTab : function(text, url) {
		if ($("#tabs").tabs('exists', text)) {
			$("#tabs").tabs('select', text);
		} else if(!opentTabs && url){
			opentTabs = true;
			$.ajax({
				url:url,
				type:'GET',
				async : true,
				timeout : 20000,
				success : function(data) {
					opentTabs = undefined;
					$("#tabs").tabs('add', {
						title : text,
						closable : true,
						content : data,
						onAdd : west.getDataGrid()
					});
				},
				error:function(xhr,status,statusText){
					opentTabs = undefined;
					$.messager.alert(xhr.status);
				}
				
			});
//			$.get(url, function(data) {
//				opentTabs = undefined;
//				$("#tabs").tabs('add', {
//					title : text,
//					closable : true,
//					content : data,
//					onAdd : west.getDataGrid()
//				});
//			});

		}
	},
	getDataGrid : function() {

	}
}
var opentTabs = undefined;
/*function initWestTree(){
 $.ajax({
 type: 'GET',
 dataType: "json",
 url: 'menu/allMenu.action',
 success: function (data) {
 $.each(data, function (i, n) {
 if(n.uls){
 $('#menu').accordion('add', {
 title: n.menuName,
 id: 'menu_' + n.menuId,
 selected: false,
 content:'<ul id ="ulMenu_' + n.menuId +'"></ul>'
 });
 console.log('获取UL小菜单');
 getSmallMenus(n.uls, n.menuId);
 }else{

 }
 });
 }
 });    
 $('#menu').accordion({
 autoHeight:false,
 navigator:true
 });
 }*/
/*function getTree(){
 $("#tree").tree({
 url:'../servlet/Nodes_Do?id=0',
 onBeforeExpand:function(node,param){  
 $('#tree').tree('options').url = "../servlet/Nodes_Do?id=" + node.id;
 },  
 loadFilter: function(data){	
 if (data.msg){	
 return data.msg;	
 } else {	
 return data;	
 }	
 }, 
 lines : true,
 onClick : function(node) {
 if (node.attributes) {
 openTab(node.text, node.attributes.url);
 }
 }
 });
 }*/
/*//获取ul小菜单
 function getSmallMenus(data, menuId, callback){
 console.log('获取小UL____tree');
 $('#ulMenu_'+ menuId).tree({
 data:data,
 onClick : function(node) {
 if (node) {
 console.log('UL onClick事件');
 openTab(node.text, node.url);
 console.log('======>>> o%', node);
 openTab(node.text, "layout/components/employeeInfo.jsp");
 }
 }
 });*/
/*	$('#ulMenu_'+ menuId).tree({
 onClick : function(node) {
 if (node) {
 openTab(node.text, node.attributes.url);
 openTab(node.text, "layout/components/employeeInfo.jsp");
 }
 }
 })
 }*/
//点击树节点center获取URL内容
/*function openTab(text, url) {
 if ($("#tabs").tabs('exists', text)) {
 $("#tabs").tabs('select', text);
 } else {
 $.get(url, function(data){
 $("#tabs").tabs('add', {
 title : text,
 closable : true,
 content : data,
 onAdd : getDataGrid()
 });
 });

 }
 }
 function getDataGrid(){

 }*/
