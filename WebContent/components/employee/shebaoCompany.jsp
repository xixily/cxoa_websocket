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
	$('#shebaoCompany_queryform').keydown(function(e){
		if(e.keyCode==13){
		   $('#shebaoCompany_search').trigger('click');
		}
		});
})
</script>
<div class="easyui-panel" style="width: 100%; max-height: 455px;">
	<!-- height:560px; -->
	<!-- 个人文本信息 -->
	<div style="padding: 5px 10px;">
		<span class="spanFont"><strong>公司名称：</strong><span
			id="shebaoCompany_company"></span></span><br />
		<table class="mytable" style="border: dashed 1px; padding-left: 3px;">
			<tr>
				<th><span class="spanFont">公司养老保险总额|</span></th>
				<th><span class="spanFont">代扣养老保险总额|</span></th>
				<th><span class="spanFont">公司失业保险总额|</span></th>
				<th><span class="spanFont">代扣失业保险总额|</span></th>
				<th><span class="spanFont">公司工伤保险总额|</span></th>
				<th><span class="spanFont">公司生育保险总额</span></th>
				<th><span class="spanFont">公司医疗保险总额|</span></th>
				<th><span class="spanFont">代扣医疗保险总额|</span></th>
				<th><span class="spanFont">公司住房保险总额|</span></th>
				<th><span class="spanFont">代扣住房保险总额|</span></th>
			</tr>
			<tr>
				<td><span class="spanFont" id="shebaoCompany_cyangl">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_dyangl">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_cshiy">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_dshiy">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_cgongs">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_cshengy">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_cyil">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_dyil">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_czhuf">0.00</span></td>
				<td><span class="spanFont" id="shebaoCompany_dzhuf">0.00</span></td>
			</tr>
		</table>
	</div>
	<div>
		<form id="shebaoCompany_queryform" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>职员姓名:</td>
					<td><input class="easyui-textbox" name="username"
						style="width: 100%" data-options="prompt:'职员姓名...'"></td>
					<td></td>
					<td><input id="shebaoCompany_search"
						class="easyui-linkbutton do_action"
						appaction="employee.shebaoSummary.queryShebaoCompany" type="button" value="查找"
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
		<jsp:include page="../../toolBar/employee/shebaoCompany_toolbar.jsp"></jsp:include>
		<table id="datagrid_shebaoCompany" class="easyui-datagrid"
			style="width: 98%; margin: 5px 20px;" data-options="">
		</table>
	</div>

</div>