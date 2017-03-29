<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .form_width input{
        width: 100px;
    }
</style><!-- 768 -->
<div class="easyui-panel" >
    <div style="padding:2px">
        <form id="hetong_fapiao_form" class="easyui-form"  method="post" data-options="novalidate:true">
            <table cellpadding="2">
                <tr>
                    <td>序号:</td>
                    <td>
                    <input id="htFapiao_id" class="easyui-textbox" type="text" name="id"  data-options="disabled:true" /></td>
                    <td>开票时间:</td>
                    <td><input class="easyui-textbox" type="text" name="date" data-options="readonly:true" /></td>
                    <td>开票公司:</td>
                    <td><input class="easyui-textbox" type="text" name="company" data-options="readonly:true" /></td>
                    <td>开票单位:</td>
                    <td><input class="easyui-textbox" type="text" name="departMement" data-options="readonly:true" /></td>
                </tr>
                <tr>
                    <td>发票类型:</td>
                    <td><input class="easyui-textbox" type="text" name="type" data-options="readonly:true" /></td>
                    <td>发票品名:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="name"  data-options="readonly:true" /></td>
                    <td>金额:</td>
                    <td><input class="easyui-textbox" type="text" name="money" data-options="readonly:true" /></td>
                    <td>备注:</td>
                    <td><input class="easyui-textbox" type="text" name="remark" data-options="readonly:true" /></td>
                </tr>
                <tr>
                    <td>合同编号:</td>
                    <td><input class="easyui-textbox" type="text" name="hetongNumber" data-options="readonly:true" /></td>
                    <td>回款情况:</td>
                    <td><input class="easyui-textbox" type="text" name="huiKuan" data-options="readonly:true"  /></td>
                    <td>大写金额:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="capitalMoney" data-options="readonly:true"  /></td>
                    <td>申请人:</td>
                    <td><input class="easyui-textbox" type="text" name="Applicant" data-options="readonly:true"  /></td>
                </tr>
                <tr>
                    <td>汇款时间:</td>
                    <td><input class="easyui-textbox" type="text" name="remittanceDate" data-options="readonly:true"  /></td>
                    <td>发票数量:</td>
                    <td><input class="easyui-textbox" type="text" name="queryStatus" data-options="onChange:function(){
                    console.log($(this));
                    console.log('%o',$(this).textbox('options'));
                    var data = {};
                    data.queryStatus = $(this).textbox('getValue');
                    data.id = $('#htFapiao_id').textbox('getValue');
                    console.log('%o',data);
                    if(data.queryStatus && data.queryStatus !=''&&data.id&&data.id!=0){
                    confirmDialog.createDialog(
						'您确定要更新吗？',
						function(confirmId){
							$.post('hetong/updateFapiao.action', data, function(result){
								confirmDialog.destoryDialog(confirmId);
								if (!result.success) {
									$('#datagrid_fapiao').datagrid('refreshRow',index);
								}
								$('#datagrid_fapiao').datagrid('reload');
								$.messager.alert('消息', result.msg, 'info');
							});
						});
                    }
                    }" /></td>
                    <td>资金类型:</td>
                    <td><input class="easyui-textbox" type="text" name="account" data-options="readonly:true"  /></td>
                    <td>账户:</td>
                    <td>
                    <input class="easyui-textbox" type="text" name="fundType" data-options="readonly:true"  /></td>
                </tr>
                <tr>
                    <td>录库人:</td>
                    <td><input class="easyui-textbox" type="text" name="recorder" data-options="readonly:true"  /></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td colspan="2" rowspan="2">
                        <div style="text-align:center;">
                            <a id="btn_htFapiao_save" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="submitForm($(this))">保存</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="closeDialog($(this))">关闭</a>
                        </div>
                    </td>
                </tr>
                </table>
        </form>
    </div>
</div>
