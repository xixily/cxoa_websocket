<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div id="p" class="easyui-panel" style="width:100%;height:60px;padding:10px;background-color: #ffb3b3">
	<h3>职员工资计算</h3>
</div>
<table id="tab_wages" class="easyui-datagrid"  style="width:100%;height:auto"
       data-options="singleSelect:true,collapsible:true,url:'employee/renshiUser.action',method:'get',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'id'">员工编号</th>
        <th data-options="field:'username'">姓名</th>
        <th data-options="field:'firstLevel'">一级</th>
        <th data-options="field:'secondLevel'">二级</th>
        <th data-options="field:'thirdLevel'">三级</th>
        <th data-options="field:'fourthLevel'">四级</th>
        <th data-options="field:'position'">职位</th>
        <th data-options="field:'sex'">性别</th>
        <th data-options="field:'hiredate'">入职时间</th>
        <th data-options="field:'leaveTime'">离职时间</th>
        <th data-options="field:'workPlace'">工作地点</th>
        <th data-options="field:'phoneNumber'">联系方式</th>
        <th data-options="field:'degree'">学历</th>
    </tr>
    </thead>
</table>
