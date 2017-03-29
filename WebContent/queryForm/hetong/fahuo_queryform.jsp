<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#fahuoQuery_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#query_form_btn').trigger('click');
		}
		});
})
</script>
<form id="fahuoQuery_form" method="post"
	enctype="multipart/form-data">
	<table>
		<tr>
			<td>序号:</td>
			<td><input name="orderid" class="easyui-numberbox" style="width:160px" min="1" /></td>
			<td>合同编号:</td>
			<td><input name="hetongCode" class="easyui-numberbox" style="width:160px" min="1"/></td>
			<td>收件人:</td>
			<td><input name="d_contact" class="easyui-textbox" style="width:160px"/></td>
			<td>邮寄单位:</td>
			<td><input name="d_company" class="easyui-textbox" style="width:160px"/></td>
		</tr>
		<tr>
			<td>邮寄地址:</td>
			<td><input name="d_address" class="easyui-textbox" style="width:160px"/></td>
			<td>邮寄时间:</td>
			<td><input name="jDate" class="easyui-textbox" style="width:160px"/></td>
			<td>录库人:</td>
			<td><input name="recorder" class="easyui-textbox" style="width:160px" /></td>
			<td>邮寄凭证号:</td>
			<td><input name="mailno" class="easyui-textbox" style="width:160px" /></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td>
			<input id="query_form_btn" class="easyui-linkbutton do_action" appaction="hetong.fahuo.queryFahuo"
				type="button" value="查询" onclick="" style="width:46px;height:26px;" />
			</td>
			<td></td>
			<td>
			<input class="easyui-linkbutton" id="btn_reset" type="button" value="重置"
				onclick="clearForm($(this))" style="width:46px;height:26px;" />
			</td>
		</tr>
	</table>
</form>