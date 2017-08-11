<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售人员创建合同</title> -->
<link href="/app/views/hetong/css/global.css" rel="stylesheet" type="text/css" />
<!-- <link href="/app/views/hetong/css/style.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/space.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet" type="text/css" /> -->
<style>
.pop_product {
    height: 380px;
}
.popwindow {
	height: initial;2
}
.selectNew {
    z-index: 1;
}
p {
    margin: 0 0 ;
}
.selectNav ul li {
    padding: 2px 0 0 6px;
    border-bottom: 1px solid #d4c6cb;
}

.courier_list li.li09 {
    width: 423px;
}

.selectNav {
    width: 228px;
    background: url(/app/views/hetong/images/icons.png) no-repeat 210px 0;
}

.selectNew {
    width: 226px;
}

.courier_list li.li05 {
    width: 423px;
}

.courier_list li.li08 {
    width: inherit;
}
.tract_list .quy_tit {
    width: 90px;
}


.mainContent{
    width: 840px;
    /* padding: 20px 0; */
    float:left;
}
body{overflow: hidden;}

.mytitle{
    background: #eadede;
    padding: 2px 8px;
    color: #121312;
    font-size: 14px;
    border-radius: 2px;
    width: 90px;
}

.ract_bottom {
    margin-bottom: 20px;
}

.tract_list .Modify {
    clear: both;
    padding: 4px 0 0 92px;
}
</style>

<div style="position: absolute; left: 230px;  right: 30px; top:80px; bottom:30px; overflow: auto;">
<div style="width:1800px;">
<div class="mainContent" style="margin-right:40px;">
<div class="mytitle">修改后合同</div>
	<div class="contract">
    <form id="form1" name="form1" method="post" action="">
		<div class="tract_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">合同编号：</div>
				<div id="htNum3" class="leftF">${contract.id }</div>
				<div id="htNum" style="display:none">${contractCopy.id }</div>
				<!-- <p class="text">所属公司所属公司</p> -->
			</td>
			
			<td valign="top" width="25%">
				<div class="quy_tit">所属公司：</div>
				<input id="company" type="text" name="textfield" class="fidtext" value="${contractCopy.company }" disabled="disabled"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="companyVO" type="text" autocomplete="off" name="textfield" class="fidtext" /></div> 
				<p class="text">${ContractVOCopy.company }</p> 
			</td>
			<td valign="top" width="25%">
				
				<div class="quy_tit">单位名称：</div>
				<input id="danwei" type="text" name="textfield" class="fidtext" value="${contractCopy.depart }" disabled="disabled"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="danweiNameVO" type="text" autocomplete="off" name="textfield" class="fidtext" />
				</div>
				 <p class="text">${ContractVOCopy.depart }</p>
				<!-- <input type="text" name="textfield" class="fidtext" /> -->
			</td>
		  </tr>
		  <tr>
			
			<td valign="top">
				<div class="quy_tit">用户ID：</div>
				<input id="userId" type="text" name="textfield" class="fidtext" value="${contractCopy.cid }" disabled="disabled"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">单位ID：</div>
				<input id="danweiId" type="text" name="textfield" class="fidtext" value="${contractCopy.didNum }" disabled="disabled"/>
			</td>
			<td valign="top">
				<div class="quy_tit">合同金额：</div>
				<input id="contractAmount" type="text" name="textfield" disabled="disabled" class="fidtext" value="${contractCopy.contractMoney }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="contractAmountVO" type="text" autocomplete="off" name="textfield" class="fidtext" />
				</div>
				<p class="text">${ContractVOCopy.contractMoney }</p> 
			</td>
		  </tr>
		  <tr>
			
			<td valign="top">
				<div class="quy_tit">合同份数：</div>
				<input id="contractCount" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contractCopy.agreementNumber }"/>
				<%-- <a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="agreementNumberVO" type="text" autocomplete="off" name="textfield" class="fidtext" />
				</div>
				<p class="text">${ContractVOCopy.agreementNumber }</p>  --%>
			</td>
			
			<td valign="top">
				<div class="quy_tit">合同到期时间：</div>
				<%-- <input id="endTime" type="text" name="textfield" class="fidtext" value="${contract.endTime }"/> --%>
				<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" disabled="disabled" id="endTime" name="textfield" class="fidtext" value="${contractCopy.endTime }"></input>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="endTimeVO" type="text" autocomplete="off" name="textfield" class="fidtext" />
				</div>
				<p class="text">${ContractVOCopy.endTime }</p> 
			</td>
		  </tr>
		</table>
		</div>
		
		<div class="tareaTable">
			<h3>产品信息：<!-- <a class="product" href="#">添加产品</a> --></h3>
			<table id="productTable" width="600" border="0" cellspacing="0" cellpadding="0">
			
			  <tr>
				<th width="43">序号</th>
				<th width="163">产品名称</th>
				<th width="108">产品金额（元）</th>
				<th width="76">数量和年限</th>
				<th width="77">生效日期</th>
				<th width="76">截止日期</th>
			  </tr>
			  
			  <c:forEach var="a" items="${itemPriceListCopy }" >
			  <tr>
				<td bgcolor="#f5f6f6">${a.id}</td>
				<td bgcolor="#f5f6f6">${a.name}</td>
				<td bgcolor="#f5f6f6">${a.money}</td>
				<td bgcolor="#f5f6f6">${a.amount}</td>
				<td bgcolor="#f5f6f6">${a.begain}</td>
				<td bgcolor="#f5f6f6">${a.end}</td>
			  </tr>
			  </c:forEach>
			</table>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="productInfoVO" type="text" autocomplete="off" name="textfield" class="fidtext" />
			</div>
			<p class="text" style="color:red;">${ContractVOCopy.productInfo}</p> 
		</div>
		
		
		
		<div class="tareaTable">
			<h3>发票情况：</h3>
			<table id="tableAboutFapiao" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="5%">序号</th>
				<th width="10%">开票日期</th>
				<th width="6%">开票公司</th>
				<th width="10%">开票单位</th>
				<th width="6%">发票类型</th>
				<th width="12%">发票品名</th>
				<th width="8%">金额</th>
				<th width="8%">回款金额</th>
				<th width="9%">回款日期</th>
				<th width="6%">资金类型</th>
				<th width="4%">账户</th>
				<th width="4%">预计回款时间</th>
				<th width="6%">财务月份</th>
				<th width="6%">备注</th>
				
			  </tr>
			  <c:forEach var="f" items="${faPiaoListCopy}" >
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
				<td>${f.fundType}</td>
				<td>${f.account}</td>
				<td>${f.yujihuikuanDate}</td>
				<td>${f.caiwuMonth}</td>
				<td>${f.remark}</td>
			  </tr>
			  </c:forEach>
			  
			</table>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="fapiaoConditionVO" type="text"  autocomplete="off" name="textfield" class="fidtext" />
			</div>
			<p class="text" style="color:red;">${ContractVOCopy.fapiaoStatus }</p> 
		</div> 
		
		 <div class="tareaTable">
			<h3>快递情况：</h3>
			<table id="tableAboutFahuo" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="5%">序号</th>
				<th width="6%">收件人</th>
				<!-- <th width="10%">收件单位</th> -->
				<th width="8%">收件地址</th>
				<th width="11%">联系电话</th>
				<!-- <th width="9%">发件日期</th> -->
				<th width="7%">快递内容</th>
				<th width="9%">快递公司</th>
				<th width="15%">快递单号</th>
				<th width="15%">邮寄日期</th>
				
			  </tr>
			  <c:forEach var="f" items="${fahuoListCopy}" >
			  <tr>
				<td bgcolor="#f5f6f6">${f.orderid}</td>
				<td bgcolor="#f5f6f6">${f.d_contact}</td>
				<%-- <td bgcolor="#f5f6f6">${f.d_company}</td> --%>
				<td bgcolor="#f5f6f6">${f.d_address}</td>
				<td bgcolor="#f5f6f6">${f.d_tel}</td>
				<%-- <td bgcolor="#f5f6f6">${f.jDate}</td> --%>
				<td bgcolor="#f5f6f6">${f.content}</td>
				<td bgcolor="#f5f6f6">${f.postMethod}</td>
				<td bgcolor="#f5f6f6">${f.mailno}</td>
				<td bgcolor="#f5f6f6">${f.jDate}</td>
			  </tr>
			  </c:forEach>
			  
			</table>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="kuaidiConditionVO" type="text" autocomplete="off" name="textfield" class="fidtext" />
			</div>
			<p class="text" style="color:red;">${ContractVOCopy.youjiStatus }</p>
		</div> 
		<div class="tareaTable">
			<h3>合同概要：</h3>
			<textarea id="contractContent" style="height:60px;">${contractCopy.agreementText }</textarea>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="htgaiyao" type="text" autocomplete="off" name="textfield" class="fidtext" /></div>
			<p class="text" style="color:red;">${ContractVOCopy.agreementText }</p>
		</div>
		<div class="tareaTable">
			<h3>付款方式：</h3>
			<textarea id="payMethod">${contractCopy.payMethod }</textarea>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="payMethodVO" type="text" autocomplete="off" name="textfield" class="fidtext" /></div>
			<p class="text" style="color:red;">${ContractVOCopy.payMethod }</p>
		</div>
		<div class="tareaTable">
			<h3>备　　注：</h3>
			<textarea id="remark" style="height:60px;">${contractCopy.remarksText }</textarea>
		</div>
	</form>
	</div>
	<div class="ract_bottom">
	</div>
</div>

<div class="mainContent">
<div class="mytitle">原合同</div>
	<div class="contract">
    <form id="form1" name="form1" method="post" action="">
		<div class="tract_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">合同编号：</div>
				<div id="htNum2" class="leftF">${contract.id }</div>
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">所属公司：</div>
				<input id="company" type="text" name="textfield" class="fidtext" value="${contract.company }" disabled="disabled"/>
				 <p class="text">${ContractVO.company }</p> 
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">单位名称：</div>
				<input id="danwei" type="text" name="textfield" class="fidtext" value="${contract.depart }" disabled="disabled"/>
				 <p class="text">${ContractVO.depart }</p>
				<!-- <input type="text" name="textfield" class="fidtext" /> -->
			</td>
		  </tr>
		  <tr>
			<td valign="top">
				<div class="quy_tit">用户ID：</div>
				<input id="userId" type="text" name="textfield" class="fidtext" value="${contract.cid }" disabled="disabled"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">单位ID：</div>
				<input id="danweiId" type="text" name="textfield" class="fidtext" value="${contract.didNum }" disabled="disabled"/>
			</td>
			<td valign="top">
				<div class="quy_tit">合同金额：</div>
				<input id="contractAmount" type="text" name="textfield" disabled="disabled" class="fidtext" value="${contract.contractMoney }"/>
				<p class="text">${ContractVO.contractMoney }</p> 
			</td>
		  </tr>
		  <tr>
			<td valign="top">
				<div class="quy_tit">合同份数：</div>
				<input id="contractCount" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.agreementNumber }"/>
				<%-- <p class="text">${ContractVO.agreementNumber }</p>  --%>
			</td>
			
			<td valign="top">
				<div class="quy_tit">合同到期时间：</div>
				<%-- <input id="endTime" type="text" name="textfield" class="fidtext" value="${contract.endTime }"/> --%>
				<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" disabled="disabled" id="endTime" name="textfield" class="fidtext" value="${contract.endTime }"></input>
				<p class="text">${ContractVO.endTime }</p> 
			</td>
		  </tr>
		</table>
		</div>
		
		<div class="tareaTable">
			<h3>产品信息：<!-- <a class="product" href="#">添加产品</a> --></h3>
			<table id="productTable" width="600" border="0" cellspacing="0" cellpadding="0">
			
			  <tr>
				<th width="43">序号</th>
				<th width="163">产品名称</th>
				<th width="108">产品金额（元）</th>
				<th width="76">数量和年限</th>
				<th width="77">生效日期</th>
				<th width="76">截止日期</th>
				<!-- <th width="57">操作</th> -->
			  </tr>
			  
			  <c:forEach var="a" items="${itemPriceList }" >
			  <tr>
				<td bgcolor="#f5f6f6">${a.id}</td>
				<td bgcolor="#f5f6f6">${a.name}</td>
				<td bgcolor="#f5f6f6">${a.money}</td>
				<td bgcolor="#f5f6f6">${a.amount}</td>
				<td bgcolor="#f5f6f6">${a.begain}</td>
				<td bgcolor="#f5f6f6">${a.end}</td>
			  </tr>
			  </c:forEach>
			</table>
			<p class="text" style="color:red;">${ContractVO.productInfo }</p> 
		</div>
		
		
		<div class="tareaTable">
			<h3>发票情况：</h3>
			<table id="tableAboutFapiao" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="5%">序号</th>
				<th width="10%">开票日期</th>
				<th width="6%">开票公司</th>
				<th width="10%">开票单位</th>
				<th width="6%">发票类型</th>
				<th width="12%">发票品名</th>
				<th width="8%">金额</th>
				<th width="8%">回款金额</th>
				<th width="9%">回款日期</th>
				<th width="6%">资金类型</th>
				<th width="4%">账户</th>
				<th width="4%">预计回款时间</th>
				<th width="6%">财务月份</th>
				<th width="6%">备注</th>
				
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
				<td>${f.fundType}</td>
				<td>${f.account}</td>
				<td>${f.yujihuikuanDate}</td>
				<td>${f.caiwuMonth}</td>
				<td>${f.remark}</td>
			  </tr>
			  </c:forEach>
			  
			</table>
			<p class="text" style="color:red;">${ContractVO.fapiaoStatus }</p> 
		</div> 
		
		 <div class="tareaTable">
			<h3>快递情况：</h3>
			<table id="tableAboutFahuo" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="5%">序号</th>
				<th width="6%">收件人</th>
				<!-- <th width="10%">收件单位</th> -->
				<th width="8%">收件地址</th>
				<th width="11%">联系电话</th>
				<!-- <th width="9%">发件日期</th> -->
				<th width="7%">快递内容</th>
				<th width="9%">快递公司</th>
				<th width="15%">快递单号</th>
				<th width="15%">邮寄日期</th>
				
			  </tr>
			  <c:forEach var="f" items="${fahuoList }" >
			  <tr>
				<td bgcolor="#f5f6f6">${f.orderid}</td>
				<td bgcolor="#f5f6f6">${f.d_contact}</td>
				<%-- <td bgcolor="#f5f6f6">${f.d_company}</td> --%>
				<td bgcolor="#f5f6f6">${f.d_address}</td>
				<td bgcolor="#f5f6f6">${f.d_tel}</td>
				<%-- <td bgcolor="#f5f6f6">${f.jDate}</td> --%>
				<td bgcolor="#f5f6f6">${f.content}</td>
				<td bgcolor="#f5f6f6">${f.postMethod}</td>
				<td bgcolor="#f5f6f6">${f.mailno}</td>
				<td bgcolor="#f5f6f6">${f.jDate}</td>
			  </tr>
			  </c:forEach>
			  
			</table>
			<p class="text" style="color:red;">${ContractVO.youjiStatus }</p>
		</div> 
		<div class="tareaTable">
			<h3>合同概要：</h3>
			<textarea id="contractContent" style="height:60px;">${contract.agreementText }</textarea>
			<p class="text" style="color:red;">${ContractVO.agreementText }</p>
		</div>
		<div class="tareaTable">
			<h3>付款方式：</h3>
			<textarea id="payMethod">${contract.payMethod }</textarea>
			<p class="text" style="color:red;">${ContractVO.payMethod }</p>
		</div>
		<div class="tareaTable">
			<h3>备　　注：</h3>
			<textarea id="remark" style="height:60px;">${contract.remarksText }</textarea>
		</div>
	</form>
	</div>
	<div class="ract_bottom">
	</div>
</div>

<div class="clear"></div>
</div>
<div class="ract_bottom">
		<a id="pass" class="bnt_yi" href="javascript:void(0)">通过</a>
		<a id="noPass" class="bnt_er" href="javascript:void(0)">未通过</a>
		<a id="cancel" class="bnt_sa" href="javascript:void(0)">取消</a>
</div>
</div>

<!-- --------------------------------------------------- -->

<script type="text/javascript" src="/app/views/hetong/js/popwindow.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script  type="text/javascript" src="/app/views/hetong/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
$(function(){
	$('select').selectlist({
		width: 130,
		height: 18
	});		
})
	
	// --取消按钮,返回合同列表页
$("#cancel").click(function(){
	$.get('public/ht/contractList.action',function(result){
		$('#container').html(result);
	})
})

// --提示错误消息 
$(".addbnt").click(function(){
	$(this).next().toggle();
})

//  --未通过   更新合同状态  添加错误消息
$("#noPass").click(function(){
	var id = $("#htNum").text();  //副本合同id
	var id2 = $("#htNum2").text();
	var company = $("#companyVO").val();//所属公司
	var depart =$("#danweiNameVO").val();//单位名称
	var contractMoney = $("#contractAmountVO").val();//合同金额
	var endTime = $("#endTimeVO").val();//合同到期时间
	var productInfo = $("#productInfoVO").val();
	var fapiaoStatus = $("#fapiaoConditionVO").val();
	var youjiStatus = $("#kuaidiConditionVO").val();
	var agreementText = $("#htgaiyaoVO").val();  //合同概要
	var payMethod = $("#payMethodVO").val();  //付款方式
	var data = {"id":id,"company":company,"depart":depart,
			"contractMoney":contractMoney,"endTime":endTime,"productInfo":productInfo,"fapiaoStatus":fapiaoStatus,"youjiStatus":youjiStatus,
			"agreementText":agreementText,"payMethod":payMethod};
	$.post('public/ht/addErrorInfo.action',data,function(res){
		
	})
	//更新合同状态
	$.post('public/ht/notAllowUpdateContract.action',{"id":id2},function(res){
		if(res.success==true){
			$.messager.alert('提示：',res.msg);
			$.get('public/ht/contractList.action',function(result){
				$('#container').html(result);
			})
		}else{
			$.messager.alert('提示：',res.msg);
		}
	}) 
})

$("#pass").click(function(){
	var id2 = $("#htNum2").text();
	//更新合同状态
	$.post('public/ht/allowUpdateContract.action',{"id":id2},function(res){
		if(res.success==true){
			$.messager.alert('提示：',res.msg);
			/* $.get('public/ht/contractList.action',function(result){
				$('#container').html(result);
			}) */
			$.get('public/ht/contractDetail.action',{id:id2},function(result){
				$('#container').html(result);
			})
		}else{
			$.messager.alert('提示：',res.msg);
		}
	}) 
})
</script>
