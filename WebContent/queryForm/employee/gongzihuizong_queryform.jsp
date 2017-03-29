<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#gongzihuizong_queryform').keydown(function(e){
		if(e.keyCode==13){
		   $('#gongzihuizhong_search').trigger('click');
		}
		});
})
</script>
<form id="gongzihuizong_queryform" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>姓名:</td>
			<td><input name="username" class="easyui-textbox" style="width:100px;" /></td>
			<td>小组:</td>
			<td><input name="fourthLevel" class="easyui-textbox" style="width:100px;" /></td>
			<td rowspan="2">
				<div class="comboxy">
					<span class="combtitle">可配查询</span>
					<select class="easyui-combobox" name="configurable" style="margin-bottom: 2px; height: 26px;width:120px;" >
					<option value=""></option>
					<option value="username">姓名</option>
					<option value="fGongziZonge">工资总额</option>
					<option value="fSheBaoZiFu">社保自付</option>
					<option value="fSheBaoGongsi">公司社保</option>
					<option value="firstLevel">一级</option>
					<option value="secondLevel">二级</option>
					<option value="thirdLevel">三级</option>
					<option value="lishiSalary">历史工资</option>
					<option value="tiaoxinReport">调薪报表</option>
					<option value="ruzhiReport">入职报表</option>
					<option value="lizhiReport">离职报表</option>
					<option value="zhuanzhengReport">转正报表</option>
					<option value="bumenttiaozhengReport">部门调整报表</option>
					<option value="tiaoxinReport">调薪报表</option>
				</select>
				<div style="height: 2px; border: 0px;"></div>
				<input name="configurable_value" class="f1 easyui-textbox" />
	</div>
	</td>
            </tr>
         <tr>
         
            <td>
            <input id="gongzihuizhong_search" class="easyui-linkbutton do_action" appaction="employee.gongzihuizong.querygongzihuizong"  type="button" value="查找"
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