<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售人员创建合同</title> -->
<link href="/app/views/hetong/css/global.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/space.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet" type="text/css" />
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
</style>
<div class="main">
<<!-- input  type="button" name="button" value="返回" class="bnt"/> -->
	<!-- <div class="location">首页 &gt; <a href="#">合同信息</a></div> -->
	<div class="contract">
    <form id="form1" name="form1" method="post" action="">
		<div class="tract_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">合同编号：</div>
				<div id="htNum" class="leftF">${contract.id }</div>
				<!-- <p class="text">所属公司所属公司</p> -->
			</td>
		<!-- 	<td valign="top" width="25%">
				<div class="quy_tit">项目名称：</div>
				<input type="text" name="textfield" class="fidtext" />
				<p class="text">所属公司所属公司</p>
			</td> -->
			<td valign="top" width="25%">
				<div class="quy_tit">所属公司：</div>
				<input id="company" type="text" name="textfield" class="fidtext" value="${contract.company }"/>
				<p class="text">${ContractVO.company }</p>
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">单位名称：</div>
				<select id="danwei" name="danwei" class="leftF" style="width: 130px;height: 18px">
				<c:forEach var="c" items="${companyList }">
					<option value="${c.id}">${c.customerName }</option>
				</c:forEach>
				</select>
				<p class="text">${ContractVO.depart }</p>
				<!-- <input type="text" name="textfield" class="fidtext" /> -->
			</td>
		  </tr>
		  <tr>
			<!-- <td valign="top">
				<div class="quy_tit">岗位性质：</div>
				<select id="gangwei" name="gangwei" class="leftF">
					<option value="0">前场</option>
					<option value="1">前场</option>
				</select>
			</td> -->
			<!-- <td valign="top">
				<div class="quy_tit">省　　份：</div>
				
			</td> -->
			
			<td valign="top">
				<div class="quy_tit">用户ID：</div>
				<input id="userId" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.cid }"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">单位ID：</div>
				<input id="danweiId" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.didNum }"/>
			</td>
			<!-- <td valign="top">
				<div class="quy_tit">部门名称：</div>
				<select id="bumen" name="bumen" class="leftF">
					<option value="0">教图湖北市场</option>
					<option value="1">教图湖北市场</option>
				</select>
			</td> -->
			<!-- <td valign="top">
				<div class="quy_tit">小组/细胞核：</div>
				<input type="text" name="textfield" class="fidtext" />
			</td> -->
			<td valign="top">
				<div class="quy_tit">合同金额：</div>                                                                                                    
				<input  id="contractAmount" type="text" name="textfield" autocomplete="off" class="fidtext" value="${contract.contractMoney }" onBlur="validateContractAmount();"/>
				<p class="text">${ContractVO.contractMoney }</p> 
				<p class="text" id="contractAmountError"></p>
			</td>
		  </tr>
		  
		  <%-- <tr>
			<td valign="top">
				<div class="quy_tit">申 请 人：</div>
				<input type="text" name="textfield" class="fidtext" />
			</td> 
			<td valign="top">
				<div class="quy_tit">联系方式：</div>
				<input id="telephone" type="text" name="textfield" class="fidtext" />
			</td> 
			<td valign="top">
				<div class="quy_tit">合同金额：</div>                                                                                                    
				<input  id="contractAmount" type="text" name="textfield" autocomplete="off" class="fidtext" value="${contract.contractMoney }" onBlur="validateContractAmount();"/>
				<p class="text">${ContractVO.contractMoney }</p> 
				<p class="text" id="contractAmountError"></p>
			</td>
		  </tr> --%>
		  
		  <tr>
			
			<td valign="top">
				<div class="quy_tit">合同份数：</div>
				<input id="contractCount" type="text" name="textfield" autocomplete="off" class="fidtext" value="${contract.agreementNumber }"/>
				<p class="text">${ContractVO.agreementNumber }</p>
			</td>
			
			<td valign="top">
				<div class="quy_tit">合同到期时间：</div>
				<!-- <input id="endTime" type="text" name="textfield" class="fidtext" /> -->
				<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endTime" name="textfield" class="fidtext" value="${contract.endTime }"></input>
				<p class="text">${ContractVO.endTime }</p>
			</td>
			<!-- <td valign="top">
				<div class="quy_tit">归档时间：</div>
				<input type="text" name="textfield" class="fidtext" />
				<div id="htNum" class="leftF"></div>
			</td> -->
			<!-- <td valign="top">
				<div class="quy_tit">归档份数：</div>
				<input type="text" name="textfield" class="fidtext" />
				<div id="htNum" class="leftF"></div>
			</td> -->
		  </tr>
		  
		  
		  
		  
		</table>
		</div>
		
		<div class="tareaTable">
			<h3>产品信息：<a id="addProduct" class="product" href="javascript:void(0)">添加产品</a></h3>
			<table id="productTable" width="600" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="43">序号</th>
				<th width="163">产品名称</th>
				<th width="108">产品金额（元）</th>
				<th width="76">数量和年限</th>
				<th width="77">生效日期</th>
				<th width="76">截止日期</th>
				<th width="57">操作</th>
			  </tr>
			  <c:forEach var="a" items="${itemPriceList }" >
			  <tr>
				<td>${a.id}</td>
				<td>${a.name}</td>
				<td>${a.money}</td>
				<td>${a.amount}</td>
				<td>${a.begain}</td>
				<td>${a.end}</td>
				<td><a id='${a.id}' class='dele' href='javascript:;' onclick='updateProduct(${a.id},this)'>修改</a></td> 
				
			  </tr>
			  </c:forEach>
			</table>
			<p class="text" style="color:red;">${ContractVO.productInfo }</p> 
		</div>
		<div class="tareaTable">
			<h3>发票情况：<a id="addFapiao" class="invoice" href="javascript:void(0)">增开发票</a></h3>
			<table id="tableAboutFapiao" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="5%">序号</th>
				<th width="10%">开票日期</th>
				<th width="9%">开票公司</th>
				<th width="12%">开票单位</th>
				<th width="12%">发票类型</th>
				<th width="12%">发票品名</th>
				<th width="10%">金额</th>
				<th width="9%">回款情况</th>
				<th width="9%">回款日期</th>
				<th width="7%">备注</th>
				<th width="6%">操作</th>
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
				<td><a id='${f.id}' class='dele' href='javascript:;' onclick='updateFapiao2(${f.id},this)'>修改</a></td>
				
			  </tr>
			  </c:forEach>
			</table>
			<p class="text" style="color:red;">${ContractVO.fapiaoStatus }</p>
		</div>
		<div class="tareaTable">
			<h3>快递情况：<a id="addFahuo" class="courier" href="javascript:void(0)">新增快递</a></h3>
			<table id="tableAboutFahuo" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="5%">序号</th>
				<th width="6%">收件人</th>
				<!-- <th width="10%">收件单位</th> -->
				<th width="8%">收件地址</th>
				<th width="11%">联系电话</th>
				<th width="9%">发件日期</th>
				<th width="7%">快递内容</th>
				<th width="9%">快递公司</th>
				<th width="15%">快递单号</th>
				<th width="6%">操作</th>
			  </tr>
				  <c:forEach var="f" items="${fahuoList }" >
			  <tr>
				<td>${f.orderid}</td>
				<td>${f.d_contact}</td>
				<%-- <td>${f.d_company}</td> --%>
				<td>${f.d_address}</td>
				<td>${f.d_tel}</td>
				<td>${f.jDate}</td>
				<td>${f.content}</td>
				<td>${f.postMethod}</td>
				<td>${f.mailno}</td>
				<td><a id='${f.orderid}' class='dele' href='javascript:;' onclick='updateFahuo(${f.orderid},this)'>修改</a></td>
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
        
       
		<!-- <div class="tareaTable">
			<h3>问题描述：</h3>
			<textarea style="height:60px;">所属公司：公司名称错误</textarea>
		</div> -->
	</form>
	</div>
	<div class="ract_bottom">
	  <!-- <a id="contractSaveAgain" class="bnt_yi" href="javascript:void(0)">保存</a> -->
	  <a id="saleSubmitAgain" class="bnt_yi" href="javascript:void(0)">提交</a>
	  <a id="contractCancel" class="bnt_sa" href="javascript:void(0)">取消</a>
	</div>
	
</div>

<div class="maskLayer"></div>
<div id="popwindow pop_product" class="popwindow pop_product" style="display:none;">
    <h3>新增 / 编辑 / 添加产品</h3>
   
    <ul class="courier_list">
    	<li class="li06">
       		<label>选择产品：</label>
			<select id="chooseProduct" name="xuanze" class="leftF" >
			   <c:forEach var="p" items="${productList}">
				  <option value="${p }">${p }</option>
				  
			   </c:forEach>
			</select>
        </li>
        
         <li class="li06" id="disanfangLi" style="display:none;">
      		<label>合同概要：</label>
		    <textarea id="disanfangChanpin" placeholder="请输入单位名称、产品、金额、联系人电话" style="width:200px"></textarea>
         </li>
        
         <li class="li06">
       		<label>产品金额：</label>
			<input id="productMoney" autocomplete="off" type="number" name="textfield" class="fidtext" />
        </li> 
    	<li class="li06">
       		<label>时间/数量：</label>
			<input id="productAmount" autocomplete="off" type="number" name="textfield" class="fidtext" />
        </li>
    	<li class="li06">
       		<label>生效日期：</label>
			<!-- <input id="effectiveDate" type="text" name="textfield" class="fidtext" /> -->
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="effectiveDate" name="textfield" class="fidtext"></input>
        </li>
    	<li class="li06">
       		<label>截止日期：</label>
<!-- 			<input id="endDate" type="text" name="textfield" class="fidtext" />
 -->			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endDate" name="textfield" class="fidtext"></input>
			
        </li>
          
         <input style="display:none;" id="productID" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04">
    	<input id="sureAboutProduct" type="button" name="button" value="确定" class="bnt"/>
    	<input id="updateProduct" type="button" name="button" value="修改" class="bnt" style="display:none;"/>
    	<input id="deleteProduct" type="button" name="button" value="删除" class="bnt" style="display:none;"/>
    	<a id="cancelAboutProduct" class="bnt" href="#">取消</a></li>
    </ul>
    
</div>




<div id="windowAboutFapiao3" class="popwindow pop_invoice" style="display:none;">
    <h3>新增 / 编辑 / 查看发票</h3>
    <form id="form1" name="form1" method="post" action="">
    <ul class="courier_list">
    	<li class="li01">
        	<label>合同编号：</label><div id="HtForFapiao"></div>
        </li>
    	<li class="li01">
			<label>申请时间：</label><div id="applicationTimeAboutFapiao"></div>
        </li>
    	
    	<li class="li02">
       		<label>开票金额 ：</label>
			<input id="kaipiaoAmount" type="text" name="textfield" autocomplete="off" class="fidtext" onBlur="validateFapiaoAmount()"/>
            <p id="kaipiaoAmountError" class="text"></p>
        </li>
    	<li class="li02">
       		<label>大写金额：</label>
			<input id="daxieAmount" type="text" name="textfield" class="fidtext" />
        </li>
        
    	<li class="li02">
       		<label>开票公司：</label>
			<input id="kaipiaoCompany" type="text" name="textfield" class="fidtext" />
        </li>
    	<li class="li05">
       		<label>开票单位：</label>
			<input id="kaipiaodDanwei" type="text" name="textfield" class="fidtext" />
        </li>
    	<!-- <li class="li02">
       		<label>发票类型：</label>
			<input id="fapiaoType" type="text" name="textfield" class="fidtext" />
        </li> -->
		<li class="li02">
      		<label>发票类型：</label>
			<select id="fapiaoType" name="fapiaoType" class="fidtext">
				<option value="0">普票</option>
				<option value="1">增票</option>
			</select>
        </li>
        
        <li class="li09">
       		<label>发票品名：</label>
      	<div class="selectNav" id="nav">
              <p id="pinming" class="selectSet">点击选择栏目</p>
              <div class="selectNew">
                  <div style="margin-left:6px;"><input id="pinmingSearch" type="text" name="textfield" class="fidtext" style="width:200px; float:none;" placeholder="搜索..." /></div>
                  <ul id="pinmingUl">
                     
                  </ul>
              </div>
          </div>
         </li>
    	<li class="li02">
       		<label>开票日期：</label>
			<!-- <input id="kaipiaoDate" type="text" name="textfield" class="fidtext" /> -->
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="kaipiaoDate" name="textfield" class="fidtext"></input>
        </li>
        
         <li class="li08">
       		<label style="width:128px">开票预计回款时间：</label>
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="yujihuikuanDate" name="textfield" class="fidtext"></input>
        </li>
    	
    	<li class="li03">
       		<label>备　　注：</label>
			<textarea id="remarkAboutFapiao"></textarea>
        </li>
        <input style="display:none;" id="fapiaoId" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04"><input id="sureAboutFapiaoSale" type="button" name="button" value="确定" class="bnt" />
    	<input id="updateFapiaoSale" type="button" name="button" value="修改" class="bnt" style="display:none;"/>
    	<input id="deleteFapiao" type="button" name="button" value="删除" class="bnt" style="display:none;"/>
    	<a class="bnt" href="#">取消</a></li>
    </ul>
    </form>
</div>


<div class="popwindow pop_courier" style="display:none;">
	<!-- <a class="dele icons" href="#"></a> -->
    <h3>新增 / 编辑 / 查看快递</h3>
    <!-- <form id="form1" name="form1" method="post" action=""> -->
    <ul class="courier_list">
     	<li class="li01">
        	<label >合同编号：</label><div id="htNumRelationFaHuo"></div>
        </li>
    	<li class="li01">
			<label >申请时间：</label><div id="applicationTimeAboutFahuo"></div>
        </li>
    	<li class="li01">
       		<label>收 件 人：</label>
			<input id="receiver" type="text"  autocomplete="off" name="textfield" class="fidtext" />
        </li>
    	<li class="li01">
       		<label>联系电话：</label>
			<input id="tel" type="text" autocomplete="off" name="textfield" class="fidtext" />
        </li>
    	<!-- <li class="li01">
       		<label>收件单位：</label>
			<input id="receiveCom" type="text" name="textfield" class="fidtext" />
        </li> -->
    	<!-- <li class="li01">
       		<label>邮　　编：</label>
			<input id="email" type="text" name="textfield" class="fidtext" />
        </li> -->
    	<li class="li03">
       		<label>收件地址：</label>
			<input id="receiveAddress" type="text" autocomplete="off" name="textfield" class="fidtext" />
        </li>
    	<!-- <li class="li02">
       		<label>发 件 人：</label>
			<input id="post" type="text" name="textfield" class="fidtext" />
        </li> -->
    	<li class="li03">
       		<label>发 件 地：</label>
			<input id="postAddress" type="text" name="textfield" class="fidtext" />
        </li>
    	<li class="li02">
       		<label>邮寄日期：</label>
			<!-- <input id="postDate" type="text" name="textfield" class="fidtext" /> -->
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="postDate" name="textfield" class="fidtext"></input>
        </li>
    	<!-- <li class="li02">
       		<label>快递公司：</label>
			<input id="expressCom" type="text" name="textfield" class="fidtext" />
        </li>  -->
        <li class="li02">
      	<label>快递公司：</label>
		<select id="expressCom" name="xuanze" class="leftF" >
			  <option value="EMS">EMS</option>
			  <option value="EMS">顺丰快递</option>
			  <option value="EMS">中铁快运</option>
		</select>
        </li>
        
    	<!-- <li class="li02">
       		<label>快递编号：</label>
			<input id="expressNum" type="text" name="mailno" class="fidtext" />
        </li>  -->
        
       <!--  <li class="li02">
       		<label>快递内容：</label>
			<input id="expressContent" type="text" name="textfield" class="fidtext" />
        </li>  -->
         <li class="li03">
     		<label>快递内容：</label>
     	<textarea id="expressContent"></textarea>
        
        
    	<!-- <li class="li03">
       		<label>备　　注：</label>
			<textarea id="remarkAboutExpress"></textarea>
        </li> -->

        <input style="display:none;" id="fahuoId" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04">
    	<input id="sureAboutFaHuo" type="button" name="button" value="确定" class="bnt" />	
    	<input id="updateFahuo" type="button" name="button" value="修改" class="bnt" style="display:none;"/>
    	<input id="deleteFahuo" type="button" name="button" value="删除" class="bnt" style="display:none;"/>
    	<a class="bnt" href="#">取消</a></li> 
    </ul>
   <!--  </form> -->
 <input style="display:none;" id="state" type="text" name="textfield" class="fidtext" value="${state }"/>  
</div>

<script type="text/javascript" src="/app/views/hetong/js/popwindow2.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/hetongCreateZancunNopass.js?i=10"></script>
<script type="text/javascript" src="/app/views/hetong/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#danwei .select-button").val('${contract.depart}');
/*	$('li[data-value="${contract.depart}"').trigger('click'); */
})


</script>

