<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#renshi_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#employee_search').trigger('click');
		}
		});
})
</script>
<form id="renshiquery_form" action="renshiUserName.action" method="post"
	enctype="multipart/form-data">
	<table>
		<tr>
			<td>姓名:</td>
			<td><input name="username" class="f1 easyui-textbox" style="width:160px" /></td>

			<td>小组:</td>
			<td><input name="fourthLevel" class="f1 easyui-textbox" style="width:160px"/></td>

			<td>保险公司:</td>
			<td>
                    <input class="easyui-combobox" name="insuranceCompany" data-options="
                    url: 'employee/getInsuranceCompany.action',
                    valueField:'value',
                    textField:'text',
                    " />
			</td>
			<td>公司名称:</td>
			<td>
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
            </td>
			<td rowspan="2">
				<div class="comboxy">
					<span class="combtitle">可配查询</span> 
					<select class="easyui-combobox"	name="configurable" style="margin-bottom: 2px; height: 26px;width:120px;" >
						<option value=""></option>
						<option value="username">username</option>
						<option value="identityCard">身份证号</option>
						<option value="firstLevel">公司</option>
						<option value="secondLevel">部门</option>
						<option value="thirdLevel">岗位</option>
						<option value="fourthLevel">小组</option>
						<option value="level">级别</option>
						<option value="cellCore">细胞核</option>
						<option value="cellCoreEmail">细胞核邮箱</option>
						<option value="guidance">指导</option>
						<option value="guidanceEmail">指导邮箱</option>
						<option value="position">职位</option>
						<option value="workPlace">工作地点</option>
						<option value="sex">性别</option>
						<option value="borthDay">出生年月</option>
						<option value="nation">民族</option>
						<option value="degree">学历</option>
						<option value="graduatedSchool">毕业院校</option>
						<option value="major">专业</option>
						<option value="phoneNumber">联系电话</option>
						<option value="homeAddress">家庭地址</option>
						<option value="homeNumber">家庭电话</option>
						<option value="householdType">户口性质</option>
						<option value="insurance">保险</option>
						<option value="insuranceCompany">保险公司</option>
						<option value="resume">简历</option>
						<option value="photo">照片</option>
						<option value="degreeCertificate">学历证件</option>
						<option value="pastLeaveTime">过去离职日期</option>
						<option value="earlyEntryDate">早期入职时间</option>
						<option value="identityCardCopy">身份证复印件</option>
						<option value="familyRegister">户口本</option>
						<option value="leavingCertificate">离职证明</option>
						<option value="contract">合同</option>
						<option value="managementSystem">管理制度</option>
						<option value="signedTime">签定时间</option>
						<option value="terminationTime">终止时间</option>
						<option value="registeredAddress">户口地址</option>
						<option value="postcode">邮编</option>
						<option value="remarks">备注</option>
						<option value="contractNumber">合同编号</option>
						<option value="dueSocialSecurity">计划入保时间</option>
						<option value="socialSecurityHospital">社保医院</option>
						<option value="originalNumber">原编号</option>
						<option value="recruitmentSources">招聘来源</option>
						<option value="contractRenewal">合同续签</option>
						<option value="originalNumber">原编号</option>
						<option value="secrecyAgreement">岗位说明书</option>
						<option value="panCard">办理工资卡</option>
						<option value="email">邮箱</option>
						<option value="reportForm">报表</option>
						<option value="ruzhiReport">入职报表</option>
						<option value="lizhiReport">离职报表</option>
						<option value="zhuanzhengReport">转正报表</option>
						<option value="bumentiaozhengReport">部门调整报表</option>
					</select>
					<div style="height: 2px; border: 0px;"></div>
					<input name="configurable_value" class="f1 easyui-textbox" />
				</div>
			</td>
		</tr>
		<tr>
			<td>学历:</td>
			<td><input name="degree" class="easyui-textbox" style="width:160px" /></td>
			
			<td>入职时间:</td>
			<td><input name="hiredate" class="easyui-datebox" style="width:160px" />
			</td>

			<td>离职时间:</td>
			<td><input name="leaveTime" class="easyui-datebox" style="width:160px" /></td>

			<td>转正时间:</td>
			<td><input name="zhuanzhengTime" class="easyui-datebox" style="width:160px" /></td>


		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
			<!--  <a href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="submitForm($(this))">保存</a> -->
			<input id="employee_search" class="easyui-linkbutton do_action" appaction="employee.queryEmployee"
				type="button" value="查询" onclick="" style="width:46px;height:26px;" />
			</td>
			<td></td>
			<td>
            <!--  <a href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="clearForm($(this))">重置</a> -->
			<input class="easyui-linkbutton" id="btn_reset" type="button" value="重置"
				onclick="clearForm($(this))" style="width:46px;height:26px;" />
			</td>
		</tr>
	</table>
</form>