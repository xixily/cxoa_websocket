<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.mytable {
	border : dashed 1px;
	float : left;
}
</style>
<div id = "shebaoCompany_toolbar" style="padding-left: 2px;">
<!-- 公司名称：<input class="easyui-combobox" name="company" style="width:110px" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    required:true
                    " /> -->
<div class="mytable">

户口性质：<input id="shebaoComany_company" class="easyui-combobox" name="householdType" style="width:110px"  data-options="
                    valueField:'householdType',
                    textField:'householdType',
                    method:'get',
                    url:'employee/getHouseholdType.action',
                    required:true
                    "/>
基数：<input id="shebaoCompany_updateRadix" class="easyui-numberbox" value=0 style="width:110px" data-options="min:0,precision:2">
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="employee.shebaoSummary.updateRadix()">修改基数</a>
</div>
<div class="mytable" style="margin-left:6px;">
社保类型：<input id="shebaoComany_sbtype" class="easyui-combobox" name="householdType" style="width:110px"  data-options="
                    valueField:'shebaoType',
                    textField:'shebaoType',
                    method:'get',
                    url:'employee/getShebaoType.action',
                    required:true
                    "/>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-excel" onclick="employee.shebaoSummary.generateCompanyType()">重新计算公司社保类型</a>
<%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="employee.shebaoSummary.updateType()">重新计算公司社保类型</a>--%>
</div>
<span class="btn-separator" style="margin:0 6px;"></span>
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-excel" onclick="employee.shebaoSummary.exportShebaoCompany(0)">导出当前公司社保表</a>
<span>选择模式: </span>
		<select onchange="$('#datagrid_shebaoCompany').datagrid({singleSelect:(this.value==0)})">
			<option value="0">单选模式</option>
			<option value="1">多选模式</option>
		</select>	
</div>


<div id ="shebaoComany_footerbar">
<span>选择模式: </span>
		<select onchange="$('#datagrid_shebaoCompany').datagrid({singleSelect:(this.value==0)})">
			<option value="0">单选模式</option>
			<option value="1">多选模式</option>
		</select><br/>
</div>