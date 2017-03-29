<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">
$(document).ready(function() {
	if(session.logined){
		hetong.fahuo.intiFahuo();
	}
})
</script>

<!-- 发货管理 -->
<div id="tab_fahuo" title="发货管理" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="query_form_fahuo" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/hetong/fahuo_queryform.jsp',collapsible:true,tools:[
				{iconCls:'icon-reload',handler:function(){$('#query_form_fahuo').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>
	<%--hetong/queryHetong.action--%>
	<div style="width: 98%">
		<table id="datagrid_fahuo" class="easyui-datagrid" style="width:100%;height:489px;"
			data-options="
			<%--url:'datagrid.json',--%>
			title:'发货表',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			striped:true,
			pageList:[10,15,20,30,50,200],
			toolbar : '#fahuo_toolbar'">
			
			<thead>
				<tr>
					<th data-options="field:'send',width:80">寄件</th>
					<th data-options="field:'orderid',width:50,sortable:true">序号</th><br>
					<th data-options="field:'hetongCode',width:60,sortable:true">合同编号</th>
					<th data-options="field:'d_contact',width:50,sortable:true">收件人</th>
					<th data-options="field:'d_company',width:100,sortable:true">邮寄单位</th>
					<th data-options="field:'d_address',width:120,sortable:true">邮寄地址</th>
					<th data-options="field:'d_tel',width:100,sortable:true">联系电话</th>
					<th data-options="field:'postMethod',width:50,sortable:true">邮寄方式</th>
					<th data-options="field:'jDate',width:80,sortable:true">邮寄时间</th>
					<th data-options="field:'mailno',width:80,sortable:true">邮寄凭证号</th>
					<th data-options="field:'remark',width:80,sortable:true">备注</th>
					<th data-options="field:'d_post_code',width:60,sortable:true">邮编</th>
					<th data-options="field:'content',width:120,sortable:true">内容</th>
					<th data-options="field:'areaCode',width:50,sortable:true">区号</th>
					<th data-options="field:'sender',width:50,sortable:true">发货人</th>
					<th data-options="field:'city',width:50,sortable:true">城市</th>
					<th data-options="field:'area',width:60,sortable:true">地区 </th>
					<th data-options="field:'recorder',width:60,sortable:true">录库人</th>
				</tr>
			</thead>
		</table>
		<jsp:include page="../../toolBar/hetong/fahuo_toolbar.jsp"></jsp:include>
	</div>
	<div id="kuaidi_form" class="easyui-dialog" data-options="title:'打印页面',width:'518px',height:'800px',closed:true,cache:false,modal:false"></div>
</div>
