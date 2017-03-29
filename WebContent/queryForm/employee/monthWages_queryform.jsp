<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#monthWages_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#monthWages_search').trigger('click');
		}
		});
})
</script>
<form id="monthWages_form" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>姓名:</td>
			<td><input name="username" class="easyui-textbox" style="width:100px;" /></td>
			<td>公司名称:</td>
			<td>
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " style="width:100px;" />
            </td>
            <td>小组:</td>
			<td><input name="fourthLevel" class="f1 easyui-textbox" style="width:100px"/></td>
			<td>开户行:</td>
			<td><input name="accountBank" class="easyui-textbox" style="width:100px" /></td>
			<td>职工账号:</td>
			<td><input name="account" class="easyui-textbox" style="width:100px" /></td>
			<td>身份证号:</td>
			<td><input name="identityCard" class="easyui-textbox" style="width:100px" /></td>
            <!-- <td>入职报表:</td>
			<td><input name="ruzhiReport" class="easyui-datebox" style="width:100px" />
			</td>

			<td>离职报表:</td>
			<td><input name="lizhiReport" class="easyui-datebox" style="width:100px" /></td>

			<td>转正报表:</td>
			<td><input name="zhuanzhengReport" class="easyui-datebox" style="width:100px" /></td>
            <td></td> -->
            
		</tr>
		<tr>
			<td colspan="4"></td>
			<td>
            <input id="monthWages_search" class="easyui-linkbutton do_action" appaction="employee.monthWages.queryMonthWages"  type="button" value="查找"
				style="width:46px;height:26px;" />
            </td>
            <td></td>
            <td>
            <input class="easyui-linkbutton"  type="button" value="重置"
				onclick="clearForm($(this))" style="width:46px;height:26px;" />
			</td>
		</tr>
	</table>
</form>