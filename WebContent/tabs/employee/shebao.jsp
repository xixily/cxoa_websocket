<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 社保维护 -->
<div id="tab_shebao" title="社保维护" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_shebao" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/employee/shebao_queryform.jsp',collapsible:true,tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_shebao').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>

	<div style="width: 98%">
		<table id="datagrid_shebao" class="easyui-datagrid" style="width: 100%;height:492px;"
			data-options="url:'employee/getShebao.action',
			title:'社保比例信息',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			striped:true,
			pageList:[10,15,20,30,50],
			toolbar: [{
			iconCls: 'icon-add',
			text : '增加',
			handler: function(){employee.shebao.append()}
			},'-',{
				iconCls: 'icon-remove',
				text : '删除',
				handler: function(){employee.shebao.remove()}
			}],
			onDblClickRow : employee.shebao.onDblClickRow,
			onClickCell : employee.shebao.endEditing,
			onEndEdit: employee.shebao.onEndEdit">
			<thead>
				<tr>
					<!-- <th data-options="field:'sid',width:40">id</th> -->
					<!-- <th data-options="field:'company',width:120">公司名称</th> -->
					<th data-options="field:'company',width:120,sortable : true,
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'cmopany',
                                textField:'cmopany',
                                method:'get',
                                url:'employee/getCompany.action',
                                required:true
                            }
                        }">公司名称</th>
					<!-- <th data-options="field:'shebaoType',width:100 ">社保类型</th> -->
					<th data-options="field:'shebaoType',width:100,sortable : true,
					formatter:function(value,row){
                            return row.shebaoType;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                               <!--  valueField:'id', -->
                                valueField:'shebaoType',
                                textField:'shebaoType',
                                method:'get',
                                url:'employee/getShebaoType.action',
                                required:true
                            }
                        }">社保类型</th>
					<!-- <th data-options="field:'typeCode',width:100 ">类型编号</th> -->
					<th data-options="field:'radixMin',width:100,editor:{type:'numberbox',options:{precision:2}}">基数下限</th>
					<th data-options="field:'radixMax',width:100 ,editor:{type:'numberbox',options:{precision:2}}">基数上限</th>
					<th data-options="field:'cRadio',width:100 ,editor:{type:'numberbox',options:{ min:0,max:1,precision:4}}">单位比例</th>
					<th data-options="field:'radio',width:100 ,editor:{type:'numberbox',options:{ min:0,max:1,precision:4}}">个人比例</th>
					<!-- <th data-options="field:'cFixedV,width:100 ,editor:{type:'numberbox',options:{precision:2}}">单位固定值</th> -->
					<th data-options="field:'cFixedValue',width:100 ,editor:{type:'numberbox',options:{precision:2}}">单位固定值</th>
					<th data-options="field:'fixedValue',width:100 ,editor:{type:'numberbox',options:{precision:2}}">个人固定值</th>
					<!-- <th data-options="field:'householdType',width:100 ,editor:'textbox'">户口性质</th> -->
					<th data-options="field:'householdType',width:100,
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'householdType',
                                textField:'householdType',
                                method:'get',
                                url:'employee/getHouseholdType.action',
                                required:true
                            }
                        }">户口性质</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
