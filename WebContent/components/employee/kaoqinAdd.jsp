<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<style>
.form_width input {
	width: 86px;
}

.spanFont {
	font-size: 12px;
	color: gray;
}

.mytable {
	border: dashed 1px;
}

.mytable th, td {
	padding-left: 4px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#kaoqin_add_queryform').keydown(function(e){
		if(e.keyCode==13){
		   $('#kaoqinAdd_search').trigger('click');
		}
		});
})
</script>
<div class="easyui-panel" style="width: 100%;height:420px;">
	<div>
		<form id="kaoqin_add_queryform" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>职员姓名:</td>
					<td><input class="easyui-textbox" name="username"
						style="width: 100%" data-options="prompt:'职员姓名...'"></td>
					<td></td>
					<td>身份证号:</td>
					<td><input class="easyui-textbox" name=identityCard
						style="width: 100%" data-options="prompt:'身份证号码...'"></td>
					<td></td>
					<td><input id="kaoqinAdd_search"
						class="easyui-linkbutton do_action"
						appaction="employee.kaoqin.queryUsername" type="button" value="查找"
						style="width: 46px; height: 26px;" /></td>
					<td></td>
					<td><input class="easyui-linkbutton" type="button" value="重置"
						onclick="clearForm($(this))" style="width: 46px; height: 26px;" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="margin: 0 8px;">
		<table id="datagrid_kaoqin_username" class="easyui-datagrid"
			style="width: 99%; height: 133px; margin: 120px;" data-options="
			url : 'employee/renshiUser.action',
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,	
			pageSize:3,
			pageList:[3,5,10,20],
			onDblClickRow : employee.kaoqin.kaoqinAddDblCickRow,
			">
		<thead>
		<tr>
            <th data-options="field:'id',width:40">Id</th>
            <th data-options="field:'username',width:100">姓名</th>
            <th data-options="field:'identityCard',width:95">身份证号</th>
            <th data-options="field:'company',width:100">公司名称</th>
            <th data-options="field:'hiredate',width:95,editor:{type:'numberbox',min:0,max:21}">入职时间</th>
            <th data-options="field:'leaveTime',width:95,editor:{type:'numberbox',min:0,max:21}">离职时间</th>
            <th data-options="field:'zhuanzhengTime',width:95,editor:{type:'numberbox',min:0,max:21}">转正时间</th>
            <th data-options="field:'ruzhiReport',width:95,editor:{type:'numberbox',min:0,max:21}">入职报表</th>
            <th data-options="field:'lizhiReport',width:95,editor:{type:'numberbox',min:0,max:21}">离职报表</th>
            <th data-options="field:'zhuanzhengReport',width:95,editor:{type:'numberbox',min:0,max:21}">转正报表</th>
        </tr>
        </thead>
		</table>
	</div>
	<div style="margin:12px auto;height:210px;width:630px;">
		<form class="form_width" action="">
			<table cellpadding="2">
                <tr>
                    <td>姓名:</td>
                    <td>
					<input id="kaoqin_form_hiddenID" type="hidden" name="id" />
                    <input class="easyui-textbox" type="text" name="username"  data-options="required:true" /></td>
                    <td>出勤天数:</td>
                    <td><input class="easyui-numberbox" value="21" data-options="min:0" name="chuqinDay" /></td>
                    <td>转正差额天数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0" name="zhuanzhengChaeDay" /></td>
                    <td>迟到应扣天数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0" name="chidaoYingkouDay" /></td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="identityCard"  data-options="required:true" /></td>
                    <td>事假小时数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0,precision:1" name="shiJiaHour" /></td>
                    <td>病假小时数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0,precision:1" name="bingJiaHour" /></td>
                    <td>旷工小时数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0,precision:1" name="kuangGongHour" /></td>
                </tr>
                <tr>
                    <td>公司:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="firstLevel"/></td>
                    <td>婚假天数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0,precision:1" name="hunJiaDay" /></td>
                    <td>产假天数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0,precision:1" name="chanJiaDay" /></td>
                    <td>丧假天数:</td>
                    <td><input class="easyui-numberbox" value="0" data-options="min:0,precision:1" name="sangJiaDay" /></td>
                </tr>
                <tr>
                	<td>部门:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="secondLevel"/></td>
                    <td>入职时间:</td>
                    <td><input type="text" class="easyui-datebox" name="hiredate" /></td>
                    <td>离职时间:</td>
                    <td><input type="text" class="easyui-datebox" name="leaveTime" /></td>
                    <td>转正时间:</td>
                    <td><input type="text" class="easyui-datebox" name="zhuanzhengTime" /></td>
                </tr>
                <tr>
                	<td>岗位:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="thirdLevel"/></td>
                    <td>入职报表:</td>
                    <td><input class="easyui-textbox" type="text" name="ruzhiReport" /></td>
                    <td>离职报表:</td>
                    <td><input class="easyui-datebox" type="text" name="lizhiReport" /></td>
                    <td>转正报表:</td>
                    <td><input class="easyui-textbox" type="text" name="zhuanzhengReport" /></td>
                </tr>
                <tr>
                    <td>小组:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="fourthLevel"/></td>
                    <td>级别:</td>
                    <td><input class="easyui-textbox" type="text" name="level" /></td>
                    <td>考勤备注:</td>
                    <td><input class="easyui-datebox" type="text" name="kaoQinremarks" /></td>
                    <td>年假天数:</td>
                    <td><input class="easyui-datebox" type="text" name="nianJiaDay" /></td>
                </tr>
                <tr>
	                  <td colspan="8">
	                        <div style="text-align:center;">
	                            <a id="btn_employeeSave" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="submitForm($(this))">保存</a>
	                            <a id="btn_employeeRest" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="clearForm($(this))">重置</a>
	                            <a id="btn_employeeEdit" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="employee.editEmployee()">编辑</a>
	                            <a id="btn_wagesInfo" href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="employee.wages.openWages(this)">工资信息</a>
	                            <!-- <a id="btn_wagesInfo" href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="$('#dialog_wagesInfo').dialog('open')">工资信息</a>  -->
	                            <a id="btn_employeeMailto" href="mailto:dengxuefeng@chaoxing.com?cc=dengxuefeng@chaoxing.com&bcc=dengxuefeng@chaoxing.com" style="width:60px;"  class="easyui-linkbutton">发送邮件</a>
	                            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="closeDialog($(this))">关闭</a>
	                        </div>
	                    </td>
                </tr>
                </table>
		</form>
	</div>
</div>