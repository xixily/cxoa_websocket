<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 考勤管理 -->
<div id="tab_shebaoSummary" title="社保汇总" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_kaoqin" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/employee/kaoqin_queryform.jsp',collapsible:true,tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_kaoqin').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
		<table id="datagrid_kaoqin" class="easyui-datagrid" style="width:100%;height:489px;"
			data-options="
			url:'employee/queryKaoqin.action',
			title:'考勤表',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			striped:true,
			rownumbers:true,
			pageSize:15,
			rowStyler : function(index, row) {
								var date = new Date();
								var dd = date.getFullYear() + ((date.getMonth())<10? ( '0' + (date.getMonth())):date.getMonth()); 
								if (row.lizhiReport&&row.lizhiReport.substring(0,6) == dd) {
									return 'background-color:#E88282;color:#fff;font-weight:bold;';
								}
								if(row.level =='实习生'){
									return 'background-color:yellow;';
								}
								if(row.ruzhiReport&&row.ruzhiReport.substring(0,6) == dd){
									return 'background-color:#00FF00;';
								}
							},
			pageList:[10,15,20,30,50,200],
			loadFilter : function(data){
				if (typeof(data.d)=='number'){
					return data.d.toFixed(2);
				} else {
					return data;
				}
			},
			onBeforeLoad : function(param){
				 $.get('employee/getIfGenerateKaoqin.action',null,function(result){
					 if(result.success){
						 if(result.obj == 1){
							 $('#kaoqin_generate').switchbutton('check');
						 }else{
							 $('#kaoqin_generate').switchbutton('uncheck');
						 }
					 }
				 })
			},
			toolbar : '#kaoqin_toolbar',
			onDblClickRow : employee.kaoqin.onDblClickRow,
			onClickCell : employee.kaoqin.endEditing,
			onEndEdit: employee.kaoqin.onEndEdit">
			
			<thead>
				<tr>
					<th data-options="field:'username',width:80,sortable:true">姓名</th><br>
					<th data-options="field:'firstLevel',width:60,sortable:true">公司</th>
					<th data-options="field:'secondLevel',width:100,sortable:true">部门</th>
					<th data-options="field:'thirdLevel',width:100,sortable:true">岗位</th>
					<th data-options="field:'fourthLevel',width:120,sortable:true">小组</th>
					<th data-options="field:'identityCard',width:140,sortable:true">身份证号</th>
					<th data-options="field:'chuqinDay',width:40,sortable:true,editor:{type:'numberbox'}">出勤天数</th>
					<th data-options="field:'zhuanzhengChaeDay',width:40,sortable:true,editor:{type:'numberbox'}">转正差额天数</th>
					<th data-options="field:'chidaoYingkouDay',width:40,sortable:true,editor:{type:'numberbox'}">迟到应扣天数</th>
					<th data-options="field:'shiJiaHour',width:40,sortable:true,editor:{type:'numberbox'}">事假时数</th>
					<th data-options="field:'bingJiaHour',width:40,sortable:true,editor:{type:'numberbox'}">病假时数</th>
					<th data-options="field:'kuangGongHour',width:40,sortable:true,editor:{type:'numberbox'}">旷工时数</th>
					<th data-options="field:'hunJiaDay',width:40,sortable:true,editor:{type:'numberbox'}">婚假天数</th>
					<th data-options="field:'chanJiaDay',width:40,sortable:true,editor:{type:'numberbox'}">产假天数</th>
					<th data-options="field:'sangJiaDay',width:40,sortable:true,editor:{type:'numberbox'}">丧假天数</th>
					<th data-options="field:'nianJiaDay',width:40,sortable:true,editor:{type:'numberbox'}">年假天数</th>
					<th data-options="field:'hiredate',width:100,sortable:true">入职时间</th>
					<th data-options="field:'leaveTime',width:100,sortable:true">离职时间</th>
					<th data-options="field:'zhuanzhengTime',width:100,sortable:true">转正时间</th>
					<th data-options="field:'ruzhiReport',width:100,sortable:true">入职报表</th>
					<th data-options="field:'lizhiReport',width:100,sortable:true">离职报表</th>
					<th data-options="field:'zhuanzhengReport',width:100,sortable:true">转正报表</th>
					<th data-options="field:'level',width:100,sortable:true">级别</th>
					<th data-options="field:'kaoQinremarks',width:100,sortable:true,editor:{type:'textbox'}">考勤备注</th>
				</tr>
			</thead>
		</table>
		<jsp:include page="../../toolBar/employee/kaoqin_toolbar.jsp"></jsp:include>
		<div id="dialog_wagesDate" class="easyui-dialog" title="维护工作日表"
		style="width: 360px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
                modal : false,
                closed : true
            ">
           	<jsp:include page="../../components/employee/wagesDate.jsp"></jsp:include>
	</div>
	</div>
	
	<div id="kaoqin_add_dialog" class="easyui-dialog" title="增加考勤记录"
		style="width: 680px;overflow: hidden"
		data-options="
                iconCls : 'icon-edit',
                modal : false,
                closed : true
            ">
           	<jsp:include page="../../components/employee/kaoqinAdd.jsp"></jsp:include>
	</div>

</div>
