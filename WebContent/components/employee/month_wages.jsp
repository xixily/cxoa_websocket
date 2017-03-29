<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="easyui-panel" style="width:960px;overflow: hidden" ><!-- height:560px; -->
	<div>
		<form id="company_excel">
			<div>
	        <label for="name">公司名称:</label>
	        <input class="easyui-validatebox" type="text" name="company" data-options="required:true" />
	 		</div>
	 		<div>
	        <label for="name">公司名称:</label>
	        <input class="easyui-validatebox" type="button" name="company" data-options="required:true" value="下载"/>
	 		</div>
		</form>
		
		<form>
			<input id="combox_c1" class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
		</form>
	</div>
</div>