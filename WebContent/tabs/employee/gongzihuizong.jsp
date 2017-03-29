<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!--工资汇总 -->
<div id="tab_gongzihuizong" title="工资汇总" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_gongzihuizong" title="工资汇总查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/employee/gongzihuizong_queryform.jsp',collapsible:true,tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_gongzihuizong').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
		<table id="datagrid_gongzihuizong" class="easyui-datagrid" style="width: 100%;height:492px;"
			data-options="url:'employee/getgongzihuizong.action',
			title:'工资汇总',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			striped:true,
			pageList:[10,15,20,30,50],
			rowStyler : function(index, row) {
			    if (row.listprice > 30){ 

				return 'background-color:#6293BB;color:#fff;font-weight:bold;';

				}
			},
			
			onDblClickCell : function(index, field, value) {
								employee.view(1,$('#datagrid_gongzihuizong').datagrid('getSelected'));}"
								>
			
			<thead>
				<tr>
					<th data-options="field:'id',width:40,sortable:true">id</th> 
					<th data-options="field:'username',width:120,sortable:true">姓名</th> 
					<th data-options="field:'fGongziZonge',width:100,sortable:true">工资总额</th>
					<th data-options="field:'lishiSalary',width:100,sortable:true">历史工资总额</th>
					<th data-options="field:'fSheBaoZiFu',width:100,sortable:true">社保自付</th>
					<th data-options="field:'fSheBaoGongsi',width:100,sortable:true">公司社保</th>
					<th data-options="field:'firstLevel',width:100,sortable:true">一级</th>
					<th data-options="field:'secondLevel',width:100,sortable:true">二级</th>
					<th data-options="field:'thirdLevel',width:100,sortable:true">三级</th>
					<th data-options="field:'fourthLevel',width:100,sortable:true">四级</th>
					<th data-options="field:'ruzhiReport',width:100,sortable:true">入职报表</th>
					<th data-options="field:'lizhiReport',width:100,sortable:true">离职报表</th>
					<th data-options="field:'zhuanzhengReport',width:100,sortable:true">转正报表</th>
					<th data-options="field:'bumenttiaozhengReport',width:100,sortable:true">部门调整报表</th>
					<th data-options="field:'tiaoxinReport',width:100,sortable:true">调薪报表</th>
					<th data-options="field:'leaveDate',width:100,sortable:true">离职时间</th>
					<th data-options="field:'ifSecret',width:100,sortable:true">工资保密</th>
				</tr>
			</thead>
		</table>
	</div>

<div id="userName_info" class="easyui-dialog" title="查看修改职员信息"
		style="width: 1080px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
              <!--   href : 'components/usernameForm.jsp', -->
                modal : false,
                closed : true
            ">
           	<jsp:include page="../../components/employee/usernameForm.jsp"></jsp:include>
	</div>
	<div id="dialog_wagesInfo" class="easyui-dialog" title="查看修改职员工资信息"
		style="width: 980px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
                modal : false,
                closed : true
            ">
           	<jsp:include page="../../components/employee/wages.jsp"></jsp:include>
	</div>

</div>
