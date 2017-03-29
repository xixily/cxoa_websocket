<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 发票管理 -->
<div id="tab_fahuo" title="发票管理" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',collapsible:true,closable:true">
<!-- 组合查询form表单 -->
	<div id="query_form_fapiao" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/hetong/fapiao_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#query_form_fahuo').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
		<table id="datagrid_fapiao" class="easyui-datagrid" style="width:100%;height:489px;"
			data-options="
			url:'hetong/queryFapiao.action',
			title:'发票表',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			queryParams:{queryStatus:0},
			striped:true,
			pageList:[10,15,20,30,50,200],
			toolbar : '#fapiao_toolbar',
			onDblClickCell : hetong.fapiao.editFapiao
			">
		<!-- 	onDblClickCell : hetong.fapiao.onDblClickRow,
			onClickCell : hetong.fapiao.endEditing,
			onEndEdit: hetong.fapiao.onEndEdit -->
			<thead>
				<tr>
					<th data-options="field:'id',width:50,sortable:true">序号</th><br>
					<th data-options="field:'date',width:60,sortable:true">开票时间</th>
					<th data-options="field:'company',width:50,sortable:true">开票公司</th>
					<th data-options="field:'departMement',width:100,sortable:true">开票单位</th>
					<th data-options="field:'type',width:120,sortable:true">发票类型</th>
					<th data-options="field:'name',width:100,sortable:true">发票品名</th>
					<th data-options="field:'money',width:50,sortable:true">金额</th>
					<th data-options="field:'remark',width:80">备注</th>
					<th data-options="field:'hetongNumber',width:80,sortable:true">合同编号</th>
					<th data-options="field:'huiKuan',width:80,sortable:true">回款情况</th>
					<th data-options="field:'capitalMoney',width:60,sortable:true">大写金额</th>
					<th data-options="field:'Applicant',width:120">申请人</th>
					<th data-options="field:'remittanceDate',width:50,sortable:true">汇款时间</th>
					<th data-options="field:'queryStatus',width:50,sortable:true,editor:{type:'numberbox',options:{ min:0}}">发票数量</th>
					<th data-options="field:'account',width:60,sortable:true">资金类型 </th>
					<th data-options="field:'fundType',width:50,sortable:true">账户</th>
					<th data-options="field:'recorder',width:60,sortable:true">录库人</th>
				</tr>
			</thead>
		</table>
		<jsp:include page="../../toolBar/hetong/fapiao_toolbar.jsp"></jsp:include>
		
		<div id="hetong_fapiao_info" class="easyui-dialog" title="发票信息"
		data-options="iconCls : 'icon-edit',modal : false,closed : true,closable:false" style="width:980px;">
			<jsp:include page="../../components/hetong/fapiaoForm.jsp"></jsp:include>
		</div>
	</div>
</div>
