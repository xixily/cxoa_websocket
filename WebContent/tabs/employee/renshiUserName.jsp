<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	if(session.logined){
		employee.employee.initEmployee();
	}
})
</script>
<!-- 人事userName  -->
<div id="tab_renshiUserName" title="职员信息管理" style="padding: 10px;"
	data-options="iconCls:'icon-man',closable:true">
	<!-- 组合查询form表单 -->
	<div id="renshi_form" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/employee/renshi_queryform.jsp',collapsible:true,tools:[
				{iconCls:'icon-reload',handler:function(){$('#renshi_form').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;"></div>
	<jsp:include page="../../toolBar/employee/renshiUsername_toolbar.jsp"></jsp:include>
<%-- 	<jsp:include page="../components/toolbar.jsp"></jsp:include> --%>
	<table id="employee_datas" style="width:98%;height:489px;">
	<thead data-options="frozen:true">
		<tr>
		<th data-options="field:'id',width:60,sortable:true">员工编号</th>
		<th data-options="field:'username',width:60">姓名</th>
		</tr>
	</thead>
	<thead>
		<tr>
		<th data-options="field:'firstLevel',width:40,sortable:true">公司</th>
		<th data-options="field:'secondLevel',width:80,sortable:true">部门</th>
		<th data-options="field:'thirdLevel',width:60,sortable:true">岗位</th>
		<th data-options="field:'fourthLevel',width:100,sortable:true">小组</th>
		<th data-options="field:'position',width:80,sortable:true">职位</th>
		<th data-options="field:'sex',width:40,sortable:true">性别</th>
		<th data-options="field:'identityCard',width:125,sortable:true">身份证号</th>
		<th data-options="field:'borthDay',width:80,sortable:true">出生年月</th>
		<th data-options="field:'nation',width:40,sortable:true">民族</th>
		<th data-options="field:'householdType',width:60,sortable:true">户口性质</th>
		<th data-options="field:'insurance',width:60,sortable:true">保险</th>
		<th data-options="field:'insuranceCompany',width:80,sortable:true">保险公司</th>
		<th data-options="field:'company',width:80,sortable:true">公司名称</th>
		<th data-options="field:'degree',width:80,sortable:true">学历</th>
		<th data-options="field:'workPlace',width:80,sortable:true">工作地点</th>
		<th data-options="field:'earlyEntryDate',width:80,sortable:true">早期入职时间</th>
		<th data-options="field:'hiredate',width:80,sortable:true">入职时间</th>
		<th data-options="field:'zhuanzhengTime',width:80,sortable:true">转正时间</th>
		<th data-options="field:'leaveTime',width:80,sortable:true">离职时间</th>
		<th data-options="field:'phoneNumber',width:80,sortable:true">联系方式</th>
		<th data-options="field:'graduatedSchool',width:100,sortable:true">毕业学校</th>
		<th data-options="field:'major',width:100,sortable:true">专业</th>
		<th data-options="field:'homeAddress',width:100,sortable:true">家庭住址</th>
		<th data-options="field:'homeNumber',width:80,sortable:true">家庭电话</th>
		<th data-options="field:'pastLeaveTime',width:80,sortable:true">过去离职时间</th>
		<th data-options="field:'maritalStatus',width:60,sortable:true">婚姻状况</th>
		<th data-options="field:'cellCore',width:60,sortable:true">细胞核</th>
		<th data-options="field:'cellCoreEmail',width:100,sortable:true">细胞核邮箱</th>
		<th data-options="field:'guidance',width:60,sortable:true">指导</th>
		<th data-options="field:'guidanceEmail',width:100,sortable:true">指导邮箱</th>
		<th data-options="field:'managementSystem',width:100,sortable:true">关系</th>
		<th data-options="field:'signedTime',width:80,sortable:true">签定时间</th>
		<th data-options="field:'terminationTime',width:80,sortable:true">终止时间</th>
		<th data-options="field:'registeredAddress',width:80,sortable:true">户口地址</th>
		<th data-options="field:'Postcode',width:80,sortable:true">邮编</th>
		<th data-options="field:'socialSecurityHospital',width:80,sortable:true">社保医院</th>
		<th data-options="field:'dueSocialSecurity',width:80,sortable:true">投保时间</th>
		<th data-options="field:'level',width:60,sortable:true">级别</th>
		<th data-options="field:'email',width:100,sortable:true">邮箱</th>
		<th data-options="field:'ruzhiReport',width:80,sortable:true">入职报表</th>
		<th data-options="field:'lizhiReport',width:80,sortable:true">离职报表</th>
		<th data-options="field:'zhuanzhengReport',width:80,sortable:true">转正报表</th>
		<th data-options="field:'bumentiaozhengReport',width:80,sortable:true">部门调整报表</tsh>
		<th data-options="field:'contract',width:80,sortable:true">合同</th>
		</tr>
		</thead>
	</table>
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
