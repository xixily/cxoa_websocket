<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>行政人员查看合同列表</title>

</head>

<body> -->
<link href="/app/views/hetong/css/global.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/style.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet" type="text/css" />
<div class="main">
			
	<!-- <div class="location">首页 &gt; <a href="#">合同信息</a></div> -->
	<div class="contract">
	<form id="form1" name="form1" method="post" action="">
		<div class="query_list" style="border-bottom:solid #eee 1px; background:#def6fd;">
			<ul>
				<li>
					<div class="quy_tit">合同编号：</div>
					<div id="contractId" class="leftF">${contract.id }</div>
				</li>
				<li>
					<div class="quy_tit">用户ID：</div>
					<input type="text" name="textfield" class="fidtext"  value="${contract.cid }"/>
				</li>
				<li>
					<div class="quy_tit">单位ID：</div>
					<input type="text" name="textfield" class="fidtext"  value="${contract.didNum }"/>
				</li>
			<%-- 	<li>
					<div class="quy_tit">项目名称：</div>
					<input type="text" name="textfield" class="fidtext" value="${contract.id }"/>
				</li> --%>
				<li>
					<div class="quy_tit">印花税情况：</div>
					<select id="yinhuashui" name="yinhuashui" class="leftF">
						<option id="weitie" value="未贴">未贴</option>
						<option id="yitie"value="已贴">已贴</option> 
					</select>
				</li>
				<!-- <li>
					<div class="quy_tit">会计分类：</div>
					<select id="huiji" name="huiji" class="leftF">
						<option value="0">服务</option>
						<option value="1">服务</option>
					</select>
				</li> -->
				<li>
					<div class="quy_tit">归档时间：</div>
					<!-- <input type="text" name="textfield" class="fidtext"/> -->
					<input id="guidangDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  name="textfield" class="fidtext" value="${contract.guidangDate }"></input>
					
					
				</li>
				<li>
					<div class="quy_tit">归档份数：</div>
					<input type="text" name="textfield" class="fidtext" value="${contract.agreementNumber }"/>
				</li>
				<li>
					<div class="quy_tit">归档编号：</div>
					<input type="text" name="textfield" class="fidtext" value="${contract.guidangCode }"/>
				</li>
				<li>
					<div class="quy_tit">坏账金额：</div>
					<input type="text" name="textfield" class="fidtext" id="huaizhangAmount" value="${contract.year }"/>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="tract_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">所属公司：</div>
				<input id="company2" type="text" name="textfield" class="fidtext" value="${contract.company }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="company" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.company }</p>
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">单位名称：</div>
				<input type="text" name="textfield" class="fidtext"  value="${contract.depart }"/>
				 <a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="danweiName" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.depart }</p>
			</td>
			
			<td valign="top" width="25%">
				<div class="quy_tit">登记时间：</div>
				<input type="text" name="textfield" class="fidtext"  value=<fmt:formatDate pattern="yyyy-MM-dd" value="${contract.dengjiTime}"/>/> 
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="dengjiTime" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.dengjiTime }</p>
			</td>
			<!-- <td valign="top" width="25%">
				<div class="quy_tit">岗位性质：</div>
				<select id="gangwei" name="gangwei" class="leftF" value="sdfsdf">
					<option value="0">前场</option>
					<option value="1">后场</option>
				</select>
			</td> -->
			
			<td valign="top" width="25%">
				<div class="quy_tit">岗位性质：</div>
				<input type="text" name="textfield" class="fidtext"  value="${customerDepart.firstLevel }"/>
			</td>
			
		  </tr>
		  <tr>
			<!-- <td valign="top">
				<div class="quy_tit">部门名称：</div>
				<select id="bumen" name="bumen" class="leftF">
					<option value="0">教图湖北市场</option>
					<option value="1">教图湖北市场</option>
				</select>
			</td> -->
			
			<td valign="top" width="25%">
				<div class="quy_tit">部门名称：</div>
				<input type="text" name="textfield" class="fidtext"  value="${customerDepart.secondLevel }"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">小组/细胞核：</div>
				<input type="text" name="textfield" class="fidtext" value="${customerDepart.fourthLevel }"/>
			</td>
			
			
			<td valign="top">
				<div class="quy_tit">申 请 人：</div>
				<input type="text" name="textfield" class="fidtext" value="${customerDepart.charger }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="shenqingren" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.operator }</p>
			</td>
			<td valign="top">
				<div class="quy_tit">联系方式：</div>
				<input type="text" name="textfield" class="fidtext" value=""/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="telphone" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.lianxifangshi }</p>
			</td>
		  </tr>
		  <tr>
			<td valign="top">
				<div class="quy_tit">合同金额：</div>
				<input type="text" name="textfield" class="fidtext" value="${contract.contractMoney }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="contractAmount" type="text" name="textfield" class="fidtext" />
				</div>
				<p class="text">${contractVO.contractMoney }</p>
			</td>
			<td valign="top">
				<div class="quy_tit">项目结束时间：</div>
				<input type="text" name="textfield" class="fidtext" value="${contract.endTime }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="endTime" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.endTime }</p>
			</td>
			<!-- <td valign="top" width="25%">
				<div class="quy_tit">省　　份：</div>
				<select id="shengfen" name="shengfen" class="leftF">
					<option value="0">全国</option>
					<option value="1">北京</option>
					<option value="2">上海</option>
				</select>
			</td> -->
			<td valign="top">
				<div class="quy_tit">省份：</div>
				<input type="text" name="textfield" class="fidtext" value="${customerDepart.province }"/>
			</td>
			
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
		  </tr>
		</table>
		</div>
		
		
		<div class="tareaTable">
			<h3>产品信息：</h3>
			<table width="600" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="54">序号</th> 
				<th width="116">产品名称</th>
				<th width="133">产品金额（元）</th>
				<th width="99">数量和年限</th>
				<th width="98">生效日期</th>
				<th width="100">截止日期</th>
			  </tr>
			
			 <c:forEach var="a" items="${itemPriceList }" >
			  <tr>
				<td>${a.id}</td>
				<td>${a.name}</td>
				<td>${a.money}</td>
				<td>${a.amount}</td>
				<td>${a.begain}</td>
				<td>${a.end}</td>
			  </tr> 
			  </c:forEach> 
			</table>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="productInfo" type="text" name="textfield" class="fidtext" />
			<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
			<p class="text" style="color:red">${contractVO.productInfo }</p>
			
		</div>
		<div class="tareaTable">
			<h3>发票情况：</h3>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="6%">序号</th>
				<th width="12%">开票日期</th>
				<th width="9%">开票公司</th>
				<th width="13%">开票单位</th>
				<th width="11%">发票类型</th>
				<th width="13%">发票品名</th>
				<th width="9%">金额</th>
				<th width="9%">回款情况</th>
				<!-- <th width="7%">申请人</th> -->
				<th width="11%">回款日期</th>
				<th width="11%">备注</th>
			  </tr>
			  
			 <c:forEach var="f" items="${faPiaoList }" >
			  <tr>
				<td>${f.id}</td>
				<td>${f.date}</td>
				<td>${f.company}</td>
				<td>${f.departMement}</td>
				<td>${f.type}</td>
				<td>${f.name}</td>
				<td>${f.money}</td>
				<td>${f.huiKuan}</td>
				<td>${f.receivedpaymentsdate}</td>
				<td>${f.remark}</td>
			  </tr>
			  </c:forEach>
			  
			
			<!--   <tr>
				<td bgcolor="#f5f6f6">1023</td>
				<td bgcolor="#f5f6f6">2018-3-31</td>
				<td bgcolor="#f5f6f6">世纪超星</td>
				<td bgcolor="#f5f6f6">广西财经学院</td>
				<td bgcolor="#f5f6f6">服务业发票</td>
				<td bgcolor="#f5f6f6">超星中文发现</td>
				<td bgcolor="#f5f6f6">20000元</td>
				<td bgcolor="#f5f6f6">20000元</td>
				<td bgcolor="#f5f6f6">王微微</td>
				<td bgcolor="#f5f6f6">2017-3-31</td>
			  </tr> -->
			</table>
			  <a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="fapiaoCondition" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text" style="color:red">${contractVO.fapiaoStatus }</p>
		</div>
		<div class="tareaTable">
			<h3>快递情况：</h3>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="6%">序号</th>
				<th width="7%">收件人</th>
				<th width="9%">收件单位</th>
				<th width="12%">收件地址</th>
				<th width="10%">联系电话</th>
				<th width="8%">发件日期</th>
				<!-- <th width="7%">发件人</th> -->
				<!-- <th width="13%">发件地址</th> -->
				<th width="7%">快递内容</th>
				<th width="8%">快递公司</th>
				<th width="13%">快递单号</th>
			  </tr>
			  
			  <c:forEach var="f" items="${fahuoList }" >
			  <tr>
			    <td>${f.orderid}</td>
				<td>${f.d_contact}</td>
				<td>${f.d_company}</td>
				<td>${f.d_address}</td>
				<td>${f.d_tel}</td>
				<td>${f.jDate}</td>
				<td>${f.content}</td>
				<td>${f.postMethod}</td>
				<td>${f.mailno}</td>
			  </tr>
			  </c:forEach> 
			
			<!--   <tr>
				<td bgcolor="#f5f6f6">1023</td>
				<td bgcolor="#f5f6f6">王微微</td>
				<td bgcolor="#f5f6f6">世纪超星</td>
				<td bgcolor="#f5f6f6">上地七街</td>
				<td bgcolor="#f5f6f6">17800000000</td>
				<td bgcolor="#f5f6f6">2017-3-31</td>
				<td bgcolor="#f5f6f6">李松宋</td>
				<td bgcolor="#f5f6f6">上帝五街</td>
				<td bgcolor="#f5f6f6">画册</td>
				<td bgcolor="#f5f6f6">申通快递</td>
				<td bgcolor="#f5f6f6">001290993021901</td>
			  </tr> -->
		
			</table>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="kuaidiCondition" type="text" name="textfield" class="fidtext" />
			<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
			<p class="text" style="color:red">${contractVO.youjiStatus }</p>
		</div>
		
		
		<div class="tareaTable">
			<h3>合同概要：</h3>
			<textarea style="height:60px;">${contract.agreementText }</textarea>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="htgaiyao" type="text" name="textfield" class="fidtext" />
			<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
			<p class="text" style="color:red">${contractVO.agreementText }</p>
		</div>
		<div class="tareaTable">
			<h3>付款方式：</h3>
			<textarea>${contract.payMethod }</textarea>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="payMethod" type="text" name="textfield" class="fidtext" />
			<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
			<p class="text" style="color:red">${contractVO.payMethod }</p>
		</div>
		<div class="tareaTable">
			<h3>备　　注：</h3>
			<textarea style="height:60px;">${contract.remarksText }</textarea>
			<!-- <a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify"><input id="remark" type="text" name="textfield" class="fidtext" />
			<input type="button" name="button" value="确定" class="bnt" /></div> -->
		</div>
		
		<div class="tareaTable">
			<h3>问题描述：</h3>
			<textarea style="height:60px;"></textarea>
		</div>
	</form>
	</div>
	<div class="ract_bottom">
		<a id="pass" class="bnt_yi" href="javascript:void(0)">通过</a>
		<a id="noPass" class="bnt_er" href="javascript:void(0)">未通过</a>
		<a id="cancel" class="bnt_sa" href="javascript:void(0)">取消</a>
	</div>
</div>

<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script  type="text/javascript" src="/app/views/hetong/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		$('#yinhuashui').selectlist({
			width: 130,
			height: 18
		});		
	})
	
$(document).ready(function(){
	$('li[data-value="${contract.tiehuaStatus}"').trigger('click');
});
	
	
	
//通过按钮	
  $("#pass").click(function(){
		var id = $("#contractId").text();
		var yinhuashui = $("#yinhuashui .select-button").val();
		var guidangDate = $("#guidangDate").val();
		var huaizhangAmount = $("#huaizhangAmount").val();
		var company = $("#company2").val();//所属公司
		 $.get('ht/updateContractXingzheng.action',{"id":id,"yinhuashui":yinhuashui,"guidangDate":guidangDate,"huaizhangAmount":huaizhangAmount,"company":company},function(res){
		 }) 
		
		 /*  $.get('ht/deleteErrorInfo.action',{"id":id},function(res){
			  
		 })  */
		
		 $.get('ht/updateContractCondition.action',{"id":id},function(res){
			if(res.success==true){
				$.messager.alert('提示：',res.msg);
				$.get('ht/contractList.action',function(result){
					$('#container').html(result);
					})
				
			}else{
				$.messager.alert('提示：',res.msg);
			}
	}) 
})

//未通过   更新合同状态  添加错误消息
$("#noPass").click(function(){
	var id = $("#contractId").text();
	
	var company = $("#company").val();//所属公司
	var depart =$("#danweiName").val();//单位名称
	var dengjiTime = $("#dengjiTime").val();
	var operator = $("#shenqingren").val();
	var lianxifangshi = $("#telphone").val();
	var contractMoney = $("#contractAmount").val();
	var endTime = $("#endTime").val();
	var productInfo = $("#productInfo").val();
	var fapiaoStatus = $("#fapiaoCondition").val();
	var youjiStatus = $("#kuaidiCondition").val();
	var agreementText = $("#htgaiyao").val();
	var payMethod = $("#payMethod").val();
	/* var remark = $("#remark").val(); */
	var data = {"id":id,"company":company,"depart":depart,"dengjiTime":dengjiTime,"operator":operator,"lianxifangshi":lianxifangshi,
			"contractMoney":contractMoney,"endTime":endTime,"productInfo":productInfo,"fapiaoStatus":fapiaoStatus,"youjiStatus":youjiStatus,
			"agreementText":agreementText,"payMethod":payMethod};
	$.get('ht/addErrorInfo.action',data,function(res){
		
	})
	
	 $.get('ht/updatecontractNoPass.action',{"id":id},function(res){
		if(res.success==true){
			$.messager.alert('提示：',res.msg);
			$.get('ht/contractList.action',function(result){
				$('#container').html(result);
				})
		}else{
			$.messager.alert('提示：',res.msg);
		}
}) 
	
})

$("#cancel").click(function(){
	$.get('ht/contractList.action',function(result){
		$('#container').html(result);
	})
})





/* <a class="addbnt icons" href="javascript:void(0)"></a>
<div style="display:none" class="Modify">
<input id="company" type="text" name="textfield" class="fidtext" />
<input type="button" name="button" value="确定" class="bnt" /></div> */

//提示错误消息 
$(".addbnt").click(function(){
$(this).next().toggle();
})
//
/* $(".bnt").click(function(){
	$(this).parent().attr("style","display:none");
	
}) */
	
/* 	var errorInfo = $(this).prev().val();
	var shuxingming = $(this).prev().attr("id");
	
	//错误信息保存库里  在问题描述里添加错误信息
   
	var data = {"contractId":contractId,"shuxingming":shuxingming,"errorInfo":errorInfo,};
	$.get('ht/addErrorInfo.action',data,function(res){
		
		
	}) */ 


</script>
