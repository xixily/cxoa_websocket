<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#jiagou_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#jiagou_serach').trigger('click');
		}
		});
})
</script>
<form id="jiagou_form" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>级别:</td>
			<td>
			<select class="easyui-combobox"	name="level" style="margin-bottom: 2px; height: 26px;width:120px;" >
				<option value=""></option>
				<option value="1">一级</option>
				<option value="2">二级</option>
				<option value="3">三级</option>
				<option value="4">四级</option>
			</select>
			<input name="firstLevel" class="easyui-textbox" style="width:100px;" />
			</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
            <td colspan="4"></td>
            <td>
            <input id="jiagou_serach" class="easyui-linkbutton do_action" appaction="employee.jiagou.queryJiagou"  type="button" value="查找"
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