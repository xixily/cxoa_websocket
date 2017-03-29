<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#renshi_form').keydown(function(e){
		if(e.keyCode==13){
		   $('#fapiao_search').trigger('click');
		}
		});
})
</script>
<form id="fahuoQuery_form" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>公司名称:</td>
			<td><input name="company" class="easyui-textbox" style="width:160px" /></td>
			<td>开票单位:</td>
			<td><input name="departMement" class="easyui-textbox" style="width:160px" /></td>
			<td>发票类型:</td>
			<td>
			<select class="easyui-combobox" name="type" style="width:160px;">
		    <option value="">  </option>
		    <option value="增值税票">增值税票</option>
		    <option value="服务业发票">服务业发票</option>
		    <option value="普票">普票</option>
			</select>
			<!-- <input name="fapiao_type" class="easyui-textbox" style="width:160px"/> -->
			</td>
			<!-- td>查询情况:</td>
			<td><input name="fapiao_type" class="easyui-textbox" style="width:160px"/></td> -->
			<!-- <td>发票类型:</td> -->
			<!-- <td><input name="fapiao_type" class="easyui-textbox" style="width:160px"/></td> -->
			<td><input type="checkbox" onchange="
			if($(this).is(':checked')){
			$('#fahuoQuery_form').form('load',{
					queryStatus:''});
			}else{
			$('#fahuoQuery_form').form('load',{
					queryStatus:'0'});
			}
			"></td>
			<td><span>显示所有发票数据</span></td>
			<!-- <td>不显示已开发票数据：</td> 
			<td><input class="easyui-switchbutton" data-options="onText:'否',offText:'是',onChange:function(checked){if(!checked){$('#fahuoQuery_form').form('load',{
					queryStatus:0});}}"></td> -->
			<td><input id="hidden_queryStatus" name="queryStatus" type="hidden" value="0"/></td>
		</tr>
		<tr>
			<td></td>
			<td>
			<input id="fapiao_search" class="easyui-linkbutton do_action" appaction="hetong.fapiao.queryFapiao"
				type="button" value="查询" onclick="" style="width:46px;height:26px;" />
			</td>
			<td>
			<input class="easyui-linkbutton" id="btn_reset" type="button" value="重置"
				onclick="clearForm($(this))" style="width:46px;height:26px;" />
			</td>
		</tr>
	</table>
</form>