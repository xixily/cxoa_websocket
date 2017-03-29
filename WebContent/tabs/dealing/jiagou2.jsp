<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 架构管理 -->
<div id="tab_jiagou2" class="easyui-panel" title="架构管理" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
	<div style="width: 98%">
	<table id="datagrid_jiagou2" title="架构表" class="easyui-treegrid" style="height:510px;"
			data-options="
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				checkbox:true,
				url: 'employee/queryStruct.action',
				method: 'get',
				idField: 'id',
				treeField: 'name',
				pagination: true,
                pageSize: 15,
                pageList: [10,15,20,30]			
			">
		<thead>
				<tr>
					<th data-options="field:'id'" width="60">id</th>
					<th data-options="field:'name',width:100,sortable:true">name</th>
					<th data-options="field:'level',width:100,sortable:true">级别</th>
					<th data-options="field:'firstLevel',width:80,sortable:true">一级</th>
					<th data-options="field:'secondLevel',width:60,sortable:true">二级</th>
					<th data-options="field:'thirdLevel',width:100,sortable:true">三级</th>
					<th data-options="field:'leader',width:140,sortable:true">领导</th>
					<th data-options="field:'email',width:140,sortable:true">邮箱</th>
					<th data-options="field:'sortCode',width:140,sortable:true">排序代码</th>
				</tr>
			</thead>
	</table>
	</div>
</div>
