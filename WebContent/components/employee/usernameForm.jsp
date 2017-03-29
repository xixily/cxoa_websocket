<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .form_width input{
        width: 100px;
    }
</style><!-- 768 -->
<div class="easyui-panel" style="width:1060px;overflow: hidden" >
    <div style="padding:2px">
        <form id="updateUsesrname_form" class="easyui-form"  method="post" data-options="validate:true">
            <table cellpadding="2">
                <tr>
                    <td>姓名:</td>
                    <td>
					<input id="form_hidden_ID" type="hidden" name="id" />
                    <input class="easyui-textbox" type="text" name="username"  data-options="required:true" /></td>
                    <td>家庭住址:</td>
                    <td><input id="textbox_addrss" class="easyui-textbox" type="text" name="homeAddress" /></td>
                    <td>毕业时间:</td>
                    <td><input class="easyui-textbox" type="text" name="degreeCertificate" /></td>
                    <td>户口地址:</td>
                    <td><input id="textbox_hukou" class="easyui-textbox" type="text" name="registeredAddress" /></td>
                </tr>
                <tr>
                    <td>公司:</td>
                    <td>
                    <input id="input_firlevel" class="easyui-textbox" type="text" name="firstLevel" data-options="required:true" readonly="true"/>
                    <!-- <input class="easyui-textbox" type="text" name="company"  readonly="true"/> -->
                    </td>
                    <td>联系电话:</td>
                    <td><input class="easyui-textbox" type="text" name="phoneNumber" data-options="prompt:'请输入11位手机号码...',validType:'mobile'
                    " /></td>
                    <td>身份证复印件:</td>
                    <td>
                    	<input class="easyui-combobox" name="identityCardCopy" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                    <td>邮编:</td>
                    <td><input class="easyui-textbox" type="text" name="postCode" /></td>
                </tr>
                <tr>
                    <td>部门:</td><!-- 部门ID下拉 -->
                    <td><input id="input_secondLevel" class="easyui-textbox" type="text" name="secondLevel" data-options="required:true"  readonly="true"/></td>
                    <td>家庭电话:</td>
                    <td><input class="easyui-textbox" type="text" name="homeNumber" /></td>
                    <td>户口本:</td>
                    <td>
                    	<input class="easyui-combobox" name="familyRegister" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                    <td>备注:</td>
                    <td><input class="easyui-textbox" type="text" name="remarks" /></td>
                </tr>
                <tr>
                    <td>岗位:</td><!-- 三级 -->
                    <td><input id="input_thirdLevel" class="easyui-textbox" type="text" name="thirdLevel" data-options="required:true" readonly="true"/></td>
                    <td>入职时间:</td>
                    <td><input id="date_hiredate" class="easyui-datebox" type="text" name="hiredate"
                    <%--data-options = "onChange:function(newValue,oldValue){--%>
                    <%--var date = new Date(newValue);--%>
                    <%--date.setMonth(date.getMonth + 3);--%>
					<%--var data = {};--%>
					<%--alert(newValue);--%>
					<%--data.zhuanzhengTime = (date.getFullYear()+ '.' +((date.getMonth()+1)<10?('0'+(date.getMonth()+1)):(date.getMonth()+1)) + '.' + (date.getDate()<10?('0'+date.getDate()):date.getDate()));--%>
                    <%--$('#updatewages_form').form('load',data);--%>
                    <%--}"--%>
                     /></td>
                    <td>离职证明:</td>
                    <td>
                    	<input class="easyui-combobox" name="leavingCertificate" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                    <td>合同编号:</td>
                    <td><input class="easyui-textbox" type="text" name="contractNumber" /></td>
                </tr>
                <tr>
                    <td>小组:</td><!-- 四级 -->
                    <td>
                    <input id="form_hidden_departmentId" type="hidden" name="departmentId" />
                    <input id="combox" class="easyui-combobox" name="fourthLevel" data-options="
                    url: 'employee/getOStruct.action',
                    valueField:'id',
                    textField:'fourthLevel',
                    onSelect: function(rec){
                        var obj = session.ostructure[rec.id]
                        $('#updateUsesrname_form').form('load',{
                        firstLevel : obj.firstLevel,
                        secondLevel : obj.secondLevel,
                        thirdLevel : obj.thirdLevel,
                        cellCore : obj.cellCore,
                        departmentId : obj.departmentId,
                        txStruct : obj.taxStructure
                        });
		            },
			        onLoadSuccess: function(res){
                        session.ostructure = {};
                        $.each(res, function(i, obj){
                            session.ostructure[obj.id] = obj;
                        })
			        }
                    ">
                    </td>
                    <td>转正时间:</td>
                    <td><input id="zhuangZheng_datebox" class="easyui-datebox" type="text" name="zhuanzhengTime" /></td>
                    <td>合同:</td>
                    <td>
                    	<input class="easyui-combobox" name="contract" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '√协议',
								value: '√协议'
							},{
                            text: '╳',
                            value: '╳'
                            }]" />
                    </td>
                    <td>投保时间:</td>
                    <td><input class="easyui-textbox" type="text" name="dueSocialSecurity" /></td>
                </tr>
                <tr>
                    <td>职位:</td>
                    <td><input class="easyui-textbox" type="text" name="position" /></td>
                    <td>过去离职日期:</td>
                    <td><input class="easyui-datebox" type="text" name="pastLeaveTime" /></td>
                    <td>入职表:</td>
                    <td>
                    	<input class="easyui-combobox" name="entryForm" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                    <td>社保医院:</td>
                    <td><input class="easyui-textbox" type="text" name="socialSecurityHospital" /></td>
                </tr>
                <tr>
                    <td>性别:</td>
                    <td>
                    	<input id="combox_sex" class="easyui-combobox" name="sex" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '男',
								value: '男'
							},{
								text: '女',
								value: '女'
							}]" />
                    </td>
                    <td>早期入职时间:</td>
                    <td><input id="datebox_entry" class="easyui-datebox" type="text" name="earlyEntryDate" /></td>
                    <td>工资是否发放:</td>
                    <td>
                    	<input class="easyui-combobox" name="noSignReason" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '是',
								value: '是'
							},{
								text: '否',
								value: '否'
							}]" />
                    </td>
                    <td>现居地址:</td>
                    <td><input class="easyui-textbox" type="text" name="nowAddress" /></td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td><input id="textbox_id" class="easyui-textbox" name="identityCard" data-options="prompt:'请输入18位身份证号...',validType:'idcard'
                    "/></td>
                    <td>户口性质:</td>
                    <td>
                    <input class="easyui-combobox" name="householdType"  data-options="
                    valueField:'householdType',
                    textField:'householdType',
                    method:'get',
                    url:'employee/getHouseholdType.action',
                    "/>
                    <!-- <input id="combox_hoseholdType" class="easyui-combobox" name="householdType"  data-options="
                    valueField:'name',
                    textField:'name',
                    data : [{name:'城镇'},{name:'农业'},{name:'深户'},{name:'非深户'},{name:'其他'}]
                    "> -->
                    </td>
                    <td>签定时间:</td>
                    <td><input id="signed_date" class="easyui-datebox" type="text" name="signedTime" data-options="
                    onChange : function(newValue,oldValue){
                    		alert(newValue);
                    		var date = new Date(newValue);
                    		date.setYear(date.getFullYear()+3);
                    		var newDate = date.getFullYear()+ '.' + (date.getMonth()<10 ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1)) + '.' + (date.getDate()-1);
							$('end_datebox').datebox('setValue',newDate);
                    	}
                    "/></td>
                    <td>现工作地:</td>
                    <td><input class="easyui-textbox" type="text" name="workPlace" /></td>
                </tr>
                <tr>
                    <td>出生年月:</td>
                    <td><input id="textbox_borth" class="easyui-textbox" type="text" name="borthDay" /></td>
                    <td>保险:</td>
                    <td>
                    <input class="easyui-combobox" name="insurance" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '是',
								value: '是'
							},{
								text: '是（已缴）',
								value: '是（已缴）'
							},{
								text: '外地报销',
								value: '外地报销'
							},{
								text: '否',
								value: '否'
							}]" />
                   <!--  <input class="easyui-textbox" type="text" name="insurance" /> -->
                    </td>
                    <td>终止时间:</td>
                    <td><input id="end_datebox" class="easyui-datebox" type="text" name="terminationTime" /></td>
                    <td>邮箱:</td>
                    <td><input id="vie_mail" class="easyui-textbox" type="email" name="email" data-options="validType:'email'" /></td>
                </tr>
                <tr>
                    <td>民族:</td>
                    <td><input id="text_nation" class="easyui-textbox" type="text" name="nation" ></td>
                 	<td>保险通知单:</td>
                    <td>
                    	<input class="easyui-combobox" name="baoxianTongzhidan" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                    <td>离职时间:</td>
                    <td><input class="easyui-datebox" type="text" name="leaveTime" /></td>
                    <td>部门调动时间:</td>
                    <td><input class="easyui-datebox" type="text" name="" /></td>
                </tr>
                <tr>
                    <td>学历:</td>
                    <td>
                   <!--  <input class="easyui-textbox" type="text" name="degree" /> -->
                    	<input id="combox_degree" class="easyui-combobox" name="degree"  data-options="
							valueField: 'name',
							textField: 'name',
							data: [
							{name : '博士'	},
							{name : '硕士'	},
							{name : '本科'	},
							{name : '大专'	},
							{name : '高中'	},
							{name : '中专'	},
							{name : '技校'	},
							{name : '初中'	},
							{name : '其他'	}
							]" />
                    </td>
                    <td>保险公司:</td>
                    <td>
                    <input id="combox_bx" class="easyui-combobox" name="insuranceCompany" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
                  <!--   <input id="combox_bxc1" class="easyui-combobox" name="insuranceCompany" data-options="
                    url: 'employee/getInsuranceCompany.action',
                    valueField:'value',
                    textField:'text',
                    filter: function(q, row){
							var opts = $(this).combobox('options');
							return row[opts.textField].indexOf(q) == 0;f
						},
					formatter: function(row){
						var opts = $(this).combobox('options');
						return row[opts.textField];
					}
                    " /> -->
                    </td>
                  
                    <td>原编号:</td>
                    <td><input class="easyui-textbox" type="text" name="originalNumber" /></td>
                  <!--   <td>办理工资卡:</td>
                    <td><input class="easyui-textbox" type="text" name="panCard" /></td> -->
                    <td>报税架构:</td>
                    <td>
                    <input id="combox_c5" class="easyui-combobox" name="txStruct" data-options="
                    url: 'employee/getTxs.action',
                    valueField:'id',
                    textField:'name',
                    " />
                    </td>
                </tr>
                <tr>
                    <td>毕业院校:</td>
                    <td><input class="easyui-textbox" type="text" name="graduatedSchool" /></td>
                    <td>公司名称:</td>
                    <td>
                    <input id="combox_c1" class="easyui-combobox" name="company" data-options="
                    url: 'employee/getCompany.action',
                    valueField:'cmopany',
                    textField:'cmopany',
                    " />
                    </td>
<!--                     <td><input class="easyui-textbox" type="text" name="reportForm" /></td> -->
                    <td>岗位说明书:</td>
                    <td>
                    	<input class="easyui-combobox" name="secrecyAgreement" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                    <td>关系:</td>
                    <td><input class="easyui-textbox" type="text" name="managementSystem" /></td>
                </tr>
                <tr>
                    <td>专业:</td>
                    <td><input class="easyui-textbox" type="text" name="major" /></td>
                    <td>转入本公司时间:</td>
                    <td><input class="easyui-datebox" type="text" name="zhuanruGongsiTime" /></td>
                    <td>入职报表:</td>
                    <td><input class="easyui-textbox" type="text" name="ruzhiReport" /></td>
                    <td>简历:</td>
                    <td>
                    	<input class="easyui-combobox" name="resume" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                </tr>
                <tr>
                    <td>级别:</td>
                    <td>
                    <input id="combox_level" class="easyui-combobox" name="level" data-options="
                    url: 'employee/getLevel.action',
                    valueField:'name',
                    textField:'name'
                    ">
                    </td>
                    <%--<td>负责人:</td>--%>
                    <%--<td><input class="easyui-textbox" type="text" name="chargedBy"  readonly="true"/></td>--%>
                    <td>照片:</td>
                    <td>
                    	<input class="easyui-combobox" name="photo" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '√',
								value: '√'
							},{
								text: '╳',
								value: '╳'
							}]" />
                    </td>
                    <td>离职报表</td>
                    <td><input class="easyui-textbox" type="text" name="lizhiReport" />
                    <input id="form_hidden_Sercret" type="hidden" name="sercret" />
                    </td>
                    <c:if test="${sessionInfo.roleId <= 1 || sessionInfo.roleId == 100}">
                    <td>工资保密</td>
                    <td><input id="user_ifSecret" name="ifSecret" class="easyui-switchbutton" data-options="onText:'是',offText:'否'" onclick="employee.ifSecret"></td>
                    </c:if>
                </tr>
                <tr>
                    <td>合同续签:</td>
                    <td><input class="easyui-textbox" type="text" name="contractRenewal" /></td>
                    <%--<td>签字人:</td>--%>
                    <%--<td><input class="easyui-textbox" type="text" name="signedBy"  readonly="true"/></td>--%>
                     <td>招聘来源:</td>
                    <td><input class="easyui-textbox" type="text" name="recruitmentSources" /></td>
                    <td>转正报表</td>
                    <td><input class="easyui-textbox" type="text" name="zhuanzhengReport" /></td>
                    <td colspan="2" rowspan="2">
                        <div style="text-align:center;">
                            <a id="btn_employeeSave" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="submitForm($(this),function(result){
	                            $('#btn_employeeEdit').linkbutton('enable');
                            	if(result.success){
                            		if(result.obj&&result.obj>0){
	                            	$('#updateUsesrname_form').form('load', {id:result.obj});
                            		}
                            	}
                            	$.messager.alert('更新提示',result.msg);
                            },true)">保存</a>
                            <a id="btn_employeeRest" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="clearForm($(this))">重置</a>
                            <a id="btn_employeeEdit" href="javascript:void(0)" style="width:60px;display:;" class="easyui-linkbutton" onclick="employee.editEmployee(1)">编辑</a><br/>
                            <a id="btn_wagesInfo" href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="employee.wages.openWages(this)">工资信息</a>
                            <!-- <a id="btn_wagesInfo" href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="$('#dialog_wagesInfo').dialog('open')">工资信息</a>  -->
                            <a id="btn_employeeMailto" href="mailto:dengxuefeng@chaoxing.com?cc=dengxuefeng@chaoxing.com&bcc=dengxuefeng@chaoxing.com" style="width:60px;"  class="easyui-linkbutton">发送邮件</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="closeDialog($(this))">关闭</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <%--<td>级别:</td>--%>
                    <%--<td>--%>
                    <%--<input id="combox_level" class="easyui-combobox" name="level" data-options="--%>
                    <%--url: 'employee/getLevel.action',--%>
                    <%--valueField:'name',--%>
                    <%--textField:'name'--%>
                    <%--">--%>
                    <%--</td>--%>
                    <td>细胞核</td>
                    <td><input class="easyui-textbox" type="text" name="cellCore" /></td>
                    <td>年假累计</td>
                    <td>
                    <input type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:2" name="annualLleave">
                    <%--<input class="easyui-textbox" type="text" name="cellCore" />--%>
                    </td>
                    <td>部门调整报表</td>
                    <td><input class="easyui-textbox" type="text" name="bumentiaozhengReport" /></td>
                </tr>
                <tr>
                <td>年假累计</td>
                <td><input class="easyui-textbox" type="text" name="sickLleaveTotal" /></td>
                <td>是否外籍</td>
                <td>
                <input class="easyui-combobox" name="ifForeign" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '是',
								value: 0
							},{
								text: '否',
								value: 1
							}]" />
                </td>
                <td>是否理工学</td>
                <td>
                <input class="easyui-combobox" name="ifEngineering" data-options="
							valueField: 'value',
							textField: 'text',
							data: [{
								text: '否',
								value: 0
							},{
								text: '外籍或港澳台',
								value: 1
							},{
								text: '留学',
								value: 2
							}]" />
                </td>
                </tr>

                </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
    $('#date_hiredate').datebox({
    onChange:function(newValue,oldValue){
    var date = new Date(newValue);
    var newDate1 = date.getFullYear()+ "." + (date.getMonth()<10 ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1)) + "." + (date.getDate()<10 ? ('0'+(date.getDate())) : (date.getDate()));
    $('#signed_date').datebox('setValue',newDate1);
    date.setMonth(date.getMonth()+3);
    var newDate = date.getFullYear()+ "." + (date.getMonth()<10 ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1)) + "." + (date.getDate()<10 ? ('0'+(date.getDate())) : (date.getDate()));
    $('#zhuangZheng_datebox').textbox('setValue',newDate);
    }
    })
    $('#signed_date').datebox({
    onChange:function(newValue,oldValue){
    var date = new Date(newValue);
    //		date.setMonth(date.getMonth()+3);
    date.setYear(date.getFullYear()+3);
    var newDate = date.getFullYear()+ "." + (date.getMonth()<10 ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1)) + "." + ((date.getDate()-1)<10 ? ('0'+(date.getDate()-1)) : (date.getDate()-1));
    $('#end_datebox').datebox('setValue',newDate);
    }
    })
    $('#textbox_id').textbox({
    onChange : function(newValue, oldValue) {
    if(newValue && newValue.length==18){
    borthday = newValue.substr(6, 4) + '.'
    + newValue.substr(10, 2) + '.'
    + newValue.substr(12, 2);
    $('#textbox_borth').textbox('setValue', borthday);
    }
    }
    })
/*     $("#textbox_addrss").textbox({
    onChange : function(newValue, oldValue) {
    if (oldValue && oldValue.length > 0) {
    return;
    }
    $('#textbox_hukou').textbox('setValue', newValue);
    }
    }) */
    })
</script>