<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="easyui-panel" >
	<table title="Folder Browser" class="easyui-treegrid" style="width:700px;height:250px"
			data-options="
				url: '/model/treegrid_data1.json',
				method: 'get',
				checkbox: true,
				rownumbers: true,
				idField: 'id',
				treeField: 'name'
			">
		<thead>
			<tr>
				<th data-options="field:'name'" width="220">Name</th>
				<th data-options="field:'size'" width="100" align="right">Size</th>
				<th data-options="field:'date'" width="150">Modified Date</th>
			</tr>
		</thead>
	</table>
</div>