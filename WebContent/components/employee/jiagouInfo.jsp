<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<style>
    .form_width input{
        width: 100px;
    }
</style><!-- 768 -->
<div class="easyui-panel" style="width:1060px;overflow: hidden" >
    <div style="padding:2px">
        <form id="jiagou_info_form" class="easyui-form"  method="post" data-options="novalidate:true">
            <table cellpadding="2">
                <tr>
                    <td>一级:</td>
                    <td>
					<input id="form_hidden_jiagou_ID" type="hidden" name="id" />
                    <input class="easyui-textbox" type="text" name="firstLevel"  data-options="required:true" /></td>
                    <td>二级:</td>
                    <td><input id="textbox_addrss" class="easyui-textbox" type="text" name="secondLevel" /></td>
                    <td>三级:</td>
                    <td><input class="easyui-datebox" type="text" name="thirdLevel" /></td>
                    <td>四级:</td>
                    <td><input class="easyui-datebox" type="text" name="fourthLevel" /></td>
                </tr>
                <tr>
                    <td>指导:</td>
                    <td>
                    <input id="input_firlevel" class="easyui-textbox" type="text" name="guidance" data-options="required:true" readonly="true"/>
                    </td>
                    <td>指导邮箱:</td>
                    <td><input class="easyui-textbox" type="text" name="guidanceEmail" data-options="validType:'email'
                    " /></td>
                    <td>细胞核:</td>
                    <td><input class="easyui-textbox" type="text" name="cellCore" /></td>
                    <td>细胞核邮箱:</td>
                    <td><input class="easyui-textbox" type="text" name="cellCoreEmail" /></td>
                </tr>
                <tr>
                    <td>报税架构:</td>
                    <td><input class="easyui-textbox" type="text" name="taxStructure" /></td>
                    <td>排序代码:</td>
                    <td><input class="easyui-textbox" type="text" name="sortCode" /></td>
                    <td colspan="2">
                        <div style="text-align:center;">
                            <a id="btn_jiagou_add" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="$.messager.alert('TODO','employee.jiagou.addJiagou');">新增</a>
                            <a id="btn_jiagou_save" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="$.messager.alert('TODO','employee.jiagou.saveJiagou');">保存</a>
                            <a id="btn_jiagou_edit" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="$.messager.alert('TODO','employee.jiagou.editJiagou');">编辑</a>
                            <a id="btn_jiagou_rest" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="clearForm($(this))">重置</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="closeDialog($(this))">关闭</a>
                        </div>
                    </td>
                </tr>
        </form>
    </div>
</div>
</div>