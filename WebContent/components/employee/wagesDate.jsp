<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="easyui-panel" style="width:342px;overflow: hidden" >
	<div id="calendar_wagesDate" class="easyui-calendar" style="width:340px;height:190px;" data-options = "firstDay:1"></div>
	<table id="datagrid_wagesDate" class="easyui-datagrid" style="width: 100%;height:345px;"
			data-options="
			url:'employee/queryWagesDate.action',
			fitColumns:false,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:31,
			pageList:[28,29,30,31],
			toolbar: '#wagesDate_toolbar',
			loadFilter : function(data){
				if (typeof(data.d)=='number'){
					return data.d.toFixed(2);
				} else {
					return data;
				}
			},
			onDblClickRow : employee.kaoqin.wagesDateDblCickRow,
			onClickCell : employee.kaoqin.wagesDateEndEditing,
			onEndEdit: employee.kaoqin.wagesDateEndEdit">
    <thead>
        <tr>
            <th data-options="field:'date',width:95,editor:{type:'datebox'}">日期</th>
            <th data-options="field:'ruzhiDay',width:100,editor:{type:'numberbox',min:0,max:21}">入职工作日</th>
            <th data-options="field:'lizhiDay',width:95,editor:{type:'numberbox',min:0,max:21}">离职工作日</th>
        </tr>
    </thead>
</table>
<div id ="wagesDate_toolbar">
<a id="btn"  class="easyui-linkbutton" data-options="iconCls:'icon-databse_edit'" onclick="employee.kaoqin.generateWagesDate()">根据日历表生成工作日</a>
<a  class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="employee.kaoqin.wagesDateDelete()">删除</a>
<span>时间:</span>
<input class="easyui-datebox" data-options="prompt:'请选择时间...',
formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	return y+'.'+(m<10?'0'+m:m);
},
onSelect: function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var data = {};
	data.wagesMonth = y+'.'+(m<10?'0'+m:m);
	$('#datagrid_wagesDate').datagrid({queryParams:data})
	}
"  style="width:80px;">
</div>
<%-- <div id ="wagesDate_footbar">
<!-- <a  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="employee.kaoqin.wagesDateDblCickRow()">编辑</a> -->
<span>请选择时间:</span>
<input class="easyui-datebox" data-options="prompt:'请选择时间...',
formatter:function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	return y+'.'+(m<10?'0'+m:m);
},
onSelect: function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var data = {};
	data.wagesMonth = y+'.'+(m<10?'0'+m:m);
	$('#datagrid_wagesDate').datagrid({queryParams:data})
	}
"  style="width:80px;">
<!-- <input class="easyui-textbox" data-options="prompt:'请输入年份...'" style="width:60px;">
<span>年</span>
<select onchange="var data = {};data.wagesMonth = $(this).val();$('#datagrid_wagesDate').datagrid({queryParams:data})">
	<option value="1">一月</option>
	<option value="2">二月</option>
	<option value="3">三月</option>
	<option value="4">四月</option>
	<option value="5">五月</option>
	<option value="6">六月</option>
	<option value="7">七月</option>
	<option value="8">八月</option>
	<option value="9">九月</option>
	<option value="10">十月</option>
	<option value="11">十一月</option>
	<option value="12">十二月</option>
</select>
<span>月</span>
<br/> -->
</div> --%>
</div>