<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 菜单维护 -->
<div id="tab_menus" title="社保维护" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 系统菜单维护 -->
	<div id="form_menus" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/system/menus_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_shebao').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>
<%-- url: '${pageContext.request.contextPath}/model/treegrid_data1.json', --%>
	<div style="width: 98%">
	<table id="datagrid_menus" title="Folder Browser" class="easyui-treegrid" style="height:510px;"
			data-options="
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				checkbox:true,
				url: 'system/queryMenus.action',
				method: 'get',
				idField: 'menuId',
				treeField: 'menuName',
				pagination: true,
                pageSize: 15,
                pageList: [10,15,20,30],
				onLoadSuccess: function(row, data){
					$('#datagrid_menus').treegrid('collapseAll');
				}
			
			">
		<thead>
			<tr>
				<th data-options="field:'menuId'" width="60">id</th>
				<th data-options="field:'menuName'" width="220">菜单名</th>
				<th data-options="field:'menuLevel'" width="60">菜单级别</th>
				<th data-options="field:'_preMenuId'" width="60">父级菜单</th>
				<th data-options="field:'url'" width="220">地址(URL)</th>
				<th data-options="field:'sortCode'" width="100">排序编号</th>
				<th data-options="field:'iconCls'" width="80">图标</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
