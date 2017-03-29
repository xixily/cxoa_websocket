<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <style scoped>
        .f1{
            width:80px;
        }
    </style>
<!-- 工资管理 -->
<div id="tab_shebaoSummary" title="工资管理" style="padding: 10px;overflow: hidden"	data-options="iconCls:'icon-edit',closable:true">
<!-- 组合查询form表单 -->
	<div id="form_monthWages" title="组合查询" class="easyui-panel"
		data-options="iconCls:'icon-search',href:'${pageContext.request.contextPath}/queryForm/monthWages_queryform.jsp',tools:[
				{iconCls:'icon-reload',handler:function(){$('#form_kaoqin').panel('open').panel('refresh')}}]"
		style="width: 98%; padding: 10px; margin-bottom: 10px;">
	</div>
	<div style="width: 98%">
		<table id="datagrid_monthWages" class="easyui-datagrid" style="width: 100%;height:510px;"
			data-options="
			url:'employee/queryMonthWages.action',
			title:'工资表',
			fitColumns:false,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			pageSize:15,
			striped:true,
			pageList:[10,15,20,30,50,200],
			loadFilter : function(data){
				if (typeof(data.d)=='number'){
					return data.d.toFixed(2);
				} else {
					return data;
				}
			},
			toolbar : '#monthWages_toolbar',
			onDblClickRow : employee.monthWages.onDblClickRow,
			onClickCell : employee.monthWages.endEditing,
			onEndEdit: employee.monthWages.onEndEdit">
			
			<thead>
				<tr>
					<th data-options="field:'username',width:60,sortable:true">姓名</th>
					<th data-options="field:'firstLevel',width:40,sortable:true">公司</th>
					<th data-options="field:'secondLevel',width:100,sortable:true">部门</th>
					<th data-options="field:'thirdLevel',width:100,sortable:true">岗位</th>
					<th data-options="field:'fourthLevel',width:120,sortable:true">小组</th>
					<th data-options="field:'company',width:120,sortable:true">公司名称</th>
					<th data-options="field:'accountBank',width:60,sortable:true">开户行</th>
					<th data-options="field:'account',width:120,sortable:true">职工帐号</th>
					<th data-options="field:'identityCard',width:140,sortable:true">身份证号</th>
					<th data-options="field:'chuqinDay',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">出勤天数</th>
					<th data-options="field:'zhuanzhengChaeDay',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">转正差额天数</th>
					<!-- <th data-options="field:'kaoqinTotal',width:60,sortable:true">应扣总额</th> -->
					<c:if test="${sessionInfo.roleId <= 1 || sessionInfo.roleId == 100}">
					<th data-options="field:'salary',width:70,sortable:true,editor:{type:'numberbox',options:{precision:2}}">工资总额</th>
					<th data-options="field:'selfTax',width:70,sortable:true,editor:{type:'numberbox',options:{precision:2}}">个人所得税</th>
					<th data-options="field:'kaoqinTotal',width:60,sortable:true">应扣总额</th>
					<th data-options="field:'subTotal',width:60,sortable:true">代扣五险总额</th>
					<!-- <th data-options="field:'subTotal',width:60,sortable:true}">代扣五险总额</th> -->
					</c:if>
					<th data-options="field:'fakuan',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">罚款</th>
					<th data-options="field:'jiangjin',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">奖金</th>
					<th data-options="field:'bufaSalary',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">补发工资</th>
					<c:if test="${sessionInfo.roleId <= 1 || sessionInfo.roleId == 100}">
					<th data-options="field:'yingfaTotal',width:70,sortable:true">应发总额</th>
					<th data-options="field:'shifaTotal',width:70,sortable:true">实发总额</th>
					<th data-options="field:'cTotal',width:60,sortable:true">单位五险总额</th>
					<!-- <th data-options="field:'cTotal',width:60,sortable:true}">单位五险总额</th> -->
					<th data-options="field:'secrecySubsidy',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">保密补贴</th>
					<th data-options="field:'communicationSubsidy',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">通讯补贴</th>
					<th data-options="field:'lunchSubsidy',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">午餐补贴</th>
					</c:if>
					<th data-options="field:'shiJiaHour',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">事假时数</th>
					<th data-options="field:'bingJiaHour',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">病假时数</th>
					<th data-options="field:'kuangGongHour',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">旷工时数</th>
					<th data-options="field:'chidaoYingkouDay',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">迟到应扣天数</th>
					<!-- <th data-options="field:'hunJiaDay',width:60,sortable:true,editor:{type:'numberbox',options:{precision:2}}">婚假天数</th> -->
					<th data-options="field:'chanJiaDay',width:60,sortable:true">产假天数</th>
					<!-- <th data-options="field:'sangJiaDay',width:60,sortable:true">丧家天数</th> -->
					<!-- <th data-options="field:'nianJiaDay',width:60,sortable:true">年假天数</th> -->
					<th data-options="field:'annualLleave',width:60,sortable:true">年假累计</th>
					<!-- <th data-options="field:'cSickPayTotal',width:60,sortable:true">累计带薪病假</th> -->
					<th data-options="field:'sickLleaveTotal',width:60,sortable:true">病假累计</th>
					<c:if test="${sessionInfo.roleId <= 1 || sessionInfo.roleId == 100}">
					<th data-options="field:'subEndowmentIinsurance',width:60,sortable:true">代扣养老保险</th>
					<th data-options="field:'subMedicare',width:60,sortable:true">代扣医疗保险</th>
					<th data-options="field:'subUnemployedInsurance',width:60,sortable:true">代扣失业保险</th>
					<th data-options="field:'subHouseIinsurance',width:60,sortable:true">代扣住房保险</th>
					</c:if>
					<!-- <th data-options="field:'cEndowmentIinsurance',width:60,sortable:true">公司养老保险</th>
					<th data-options="field:'cMedicare',width:60,sortable:true">公司医疗保险</th>
					<th data-options="field:'cUnemployedInsurance',width:60,sortable:true">公司失业保险</th>
					<th data-options="field:'cHouseIinsurance',width:60,sortable:true">公司住房保险</th>
					<th data-options="field:'cInjuryInsurance',width:60,sortable:true">公司工伤保险</th>
					<th data-options="field:'cBirthIinsurance',width:60,sortable:true">公司生育保险</th> -->
					<!-- <th data-options="field:'rubaoTime',width:100,sortable:true">入保时间</th> -->
					<th data-options="field:'hiredate',width:100,sortable:true">入职时间</th>
					<c:if test="${sessionInfo.roleId <= 1 || sessionInfo.roleId == 100}">
					<th data-options="field:'basicWage',width:60,sortable:true">基本工资</th>
					<th data-options="field:'postSalary',width:60,sortable:true">岗位工资</th>
					<th data-options="field:'performanceRelatedPay',width:60">绩效工资</th>
					</c:if>
					<!-- <th data-options="field:'householdType',width:70,sortable:true">户口性质</th> -->
					<th data-options="field:'leaveTime',width:100,sortable:true">离职时间</th>
					<th data-options="field:'zhuanzhengTime',width:100,sortable:true">转正时间</th>
					<!-- <th data-options="field:'ruzhiReport',width:100,sortable:true">入职报表</th>
					<th data-options="field:'lizhiReport',width:100,sortable:true">离职报表</th>
					<th data-options="field:'zhuanzhengReport',width:100,sortable:true">转正报表</th> -->
					<c:if test="${sessionInfo.roleId <= 1 || sessionInfo.roleId == 100}">
					<th data-options="field:'remarks',width:100,sortable:true">工资备注</th>
					</c:if>
					<th data-options="field:'kaoQinremarks',width:100,sortable:true,editor:{type:'textbox'}">考勤备注</th>
					<c:if test="${sessionInfo.roleId <= 1 || sessionInfo.roleId == 100}">
					<th data-options="field:'lishiSalary',width:100,sortable:true">历史工资</th>
					<th data-options="field:'lishiSalary',width:100,sortable:true">其他扣款</th>
					</c:if>
				</tr>
			</thead>
		</table>
		<jsp:include page="../../toolBar/employee/monthWages_toolbar.jsp"></jsp:include>
		<!-- <div  style="width: 98%">
			 <form id="ff" action="form1_proc.php" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>职员编号:</td>
                    <td><input name="employeeId" class="f1 easyui-numberbox" data-options = "disabled:true" ></input></td>
                    <td>姓名:</td>
                    <td><input name="name" class="f1 easyui-textbox" ></input></td>
                    <td>小组:</td>
                    <td><input name="fourthLevel" class="f1 easyui-textbox" ></input></td>
                    <td>身份证号:</td>
                    <td><input name="name" class="f1 easyui-textbox" ></input></td>
                    <td>工资总额:</td>
                    <td><input name="name" class="f1 easyui-textbox" ></input></td>
                </tr>
                <tr>
                   <td>个人所得税:</td>
                   <td><input name="selfTax" class="f1 easyui-textbox" ></input></td>
                   <td>应扣总额:</td>
                   <td><input name="kaoqinTotal" class="f1 easyui-textbox" ></input></td>
                   <td>代扣五险总额:</td>
                   <td><input name="subTotal" class="f1 easyui-textbox" ></input></td>
                   <td>罚款:</td>
                   <td><input name="fakuan" class="f1 easyui-textbox" ></input></td>
                   <td>奖金:</td>
                   <td><input name="jiangjin" class="f1 easyui-textbox" ></input></td>
                </tr>
                <tr>
                   <td>补发工资:</td>
                   <td><input name="bufaSalary" class="f1 easyui-textbox" ></input></td>
                   <td>应发总额:</td>
                   <td><input name="yingfaTotal" class="f1 easyui-textbox" ></input></td>
                   <td>实发总额:</td>
                   <td><input name="shifaTotal" class="f1 easyui-textbox" ></input></td>
                   <td>单位五险总额:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                   <td>保密补贴:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                </tr>
                <tr>
                   <td>通讯补贴:</td>
                   <td><input name="communicationSubsidy" class="f1 easyui-textbox" ></input></td>
                   <td>午餐补贴:</td>
                   <td><input name="lunchSubsidy" class="f1 easyui-textbox" ></input></td>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                </tr>
                <tr>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                   <td>Name:</td>
                   <td><input name="name" class="f1 easyui-textbox" ></input></td>
                </tr>
            </table>
        </form>
		</div> -->
	</div>
</div>
