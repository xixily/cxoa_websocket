<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#shebao_queryform').keydown(function(e){
		if(e.keyCode==13){
		   $('#shebao_search').trigger('click');
		}
		});
})
</script>
<form id="shebao_queryform" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>公司名称:</td>
			<td>
                     <input class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
            </td>
			<td>社保类型:</td>
			<td>
                     <input class="easyui-combobox" name="shebaoType" data-options="
                    url: 'employee/getShebaoType.action',
                    valueField:'shebaoType',
                    textField:'shebaoType',
                    " />
            </td>
            <td></td>
            <td>
            <input id="shebao_search" class="easyui-linkbutton do_action" appaction="employee.shebao.queryShebao"  type="button" value="查找"
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