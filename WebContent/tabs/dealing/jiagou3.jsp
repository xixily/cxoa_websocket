<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 架构管理 -->
<div id="tab_jiagou" class="easyui-panel" title="架构管理" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_jiagou" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/jiagou_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_jiagou').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
		<table id="datagrid_jiagou" class="easyui-datagrid" style="width:100%;height:489px;"
			data-options="
			url:'employee/queryJiagou.action',
			title:'考勤表',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			pageList:[10,15,20,30,50,200],
			toolbar :  [{
				iconCls: 'icon-add',
				text : '增加',
				handler: function(){alert('增加')}
			},'-',{
				iconCls: 'icon-edit',
				text : '编辑',
				handler: function(){alert('编辑')}
			},'-',{
				iconCls: 'icon-remove',
				text : '删除',
				handler: function(){alert('删除')}
			}],
			onDblClickRow : function(){
				$.messager.alert('TODO','把onDblClickRow 做成一个通用的工具');
				return;
				//employee.jiagou.onDblClickRow;
			},
			onClickRow : function(){
			$.messager.alert('TODO','把onClickRow 做成一个通用的工具');
			return ;
			employee.jiagou.endEditing;
			} ,
			onEndEdit: function(){
			$.messager.alert('TODO','把onEndEdit 做成一个通用的工具');
			return ;
			employee.jiagou.onEndEdit;
			}">
			
			<thead>
				<tr>
					<th data-options="field:'firstLevel',width:80,sortable:true">一级</th>
					<th data-options="field:'secondLevel',width:60,sortable:true">二级</th>
					<th data-options="field:'thirdLevel',width:100,sortable:true">三级</th>
					<th data-options="field:'fourthLevel',width:100,sortable:true">四级</th>
					<th data-options="field:'guidance',width:140,sortable:true">指导</th>
					<th data-options="field:'guidanceEmail',width:140,sortable:true">指导邮箱</th>
					<th data-options="field:'cellCore',width:140,sortable:true">细胞核</th>
					<th data-options="field:'cellCoreEmail',width:140,sortable:true">细胞核邮箱</th>
					<th data-options="field:'taxStructure',width:140,sortable:true">报税架构</th>
					<th data-options="field:'sortCode',width:140,sortable:true">排序代码</th>
				</tr>
			</thead>
		</table>
	<%-- 	<jsp:include page="../toolBar/kaoqin_toolbar.jsp"></jsp:include>
		<div id="dialog_wagesDate" class="easyui-dialog" title="维护工作日表"
		style="width: 360px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
                modal : false,
                closed : true
            ">
           	<jsp:include page="../components/wagesDate.jsp"></jsp:include>
	</div> --%>
	</div>
	
	<div id="jiagou_info_dialog" class="easyui-dialog" title="架构信息"
		style="width: 680px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
                modal : false,
                closed : true
            ">
           	<jsp:include page="../../components/employee/jiagouInfo.jsp"></jsp:include>
	</div>

</div>
