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
<!-- <link href="/app/views/hetong/css/style.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/space.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet" type="text/css" /> -->
<style>
.pop_product {
    height: 380px;
}
.popwindow {
	height: initial;
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

.tract_list .fidtext, .courier_list .fidtext {
    border: solid #ccc 1px;
}
.courier_list textarea {
    border: solid #ccc 1px;
}
.tareaTable textarea {
    border: solid #ccc 1px;
}
a:hover{
    text-decoration: none;
}
.main {
    width: inherit;
}
</style>
<div class="main">
	<!-- <div class="location">首页 &gt; <a href="#">合同信息</a></div> -->
	<div class="contract">
	<form id="form1" name="form1" method="post" action="">
		<div class="query_list" style="border-bottom:solid #eee 1px; background:#def6fd;">
			<ul>
				<li>
					<div class="quy_tit">合同编号：</div>
					<div id="htNum" class="leftF">${contract.id }</div>
				</li>
				<li>
					<div class="quy_tit">用户ID：</div>
					<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.cid }"/>
				</li>
				<li>
					<div class="quy_tit">单位ID：</div>
					<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.didNum }"/>
				</li>
				<li>
					<div class="quy_tit">印花税情况：</div>
					<select id="yinhuashui" name="yinhuashui" class="leftF">
						<option id="weitie" value="未贴">未贴</option>
						<option id="yitie"value="已贴">已贴</option> 
					</select>
				</li>
				<li>
					<div class="quy_tit">会计分类：</div>
					<select id="kuaiji" name="huiji" class="leftF">
						<option value="服务">服务</option>
						<option value="广告">广告</option>
						<option value="加工">加工</option>
						<option value="销售">销售</option>
						<option value="租赁">租赁</option>
					</select>
				</li> 
				<li>
					<div class="quy_tit">归档时间：</div>
					<!-- <input type="text" name="textfield" class="fidtext"/> -->
					<input id="guidangDate" type="text"  name="textfield" class="fidtext" value="${contract.guidangDate }"></input>
				</li>
				 
				<li>
					<div class="quy_tit">归档份数：</div>
					<input id="guidangfenshu" type="text" name="textfield" class="fidtext" value="${contract.guidangNum }"/>
				</li>
				<li>
					<div class="quy_tit">合同份数：</div>
					<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.agreementNumber }"/>
					<%-- <a class="addbnt icons" href="javascript:void(0)"></a>
					<div style="display:none" class="Modify">
					<input id="agreementNumber" type="text" autocomplete="off" name="textfield" class="fidtext" />
					</div>
					<p class="text">${contractVO.agreementNumber }</p> --%>
				</li>
				<li>
					<div class="quy_tit">归档编号：</div>
					<input id="guidangNum" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.guidangCode }" />
				</li>
				<li>
					<div class="quy_tit">坏账金额：</div>
					<input type="text" name="textfield" class="fidtext" id="huaizhangAmount" value="${contract.year }"/>
				</li>
				<li>
					<div class="quy_tit">登记时间：</div>
					<input type="text" name="textfield" class="fidtext" id="dengjiTime"  value="${contract.dengjiTime}"/>
				</li>
				
				<li style="display:none">
					<div class="quy_tit">提交时间:</div>
					<input type="text" name="textfield" class="fidtext" id="tijiaoTime"  value="${contract.submitTime}"/>
				</li>
				
				<li class="ract_bottom">
					<a id="saveXingzheng" class="bnt_yi" href="javascript:void(0)">保存</a>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="tract_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">所属公司：</div>
				<input id="company2" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.company }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="company" type="text" autocomplete="off" name="textfield" class="fidtext" /></div>
				<p class="text">${contractVO.company }</p>
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">单位名称：</div>
				<input id="danweiName2" type="text" name="textfield" class="fidtext"  disabled="disabled" value="${contract.depart }"/>
				 <a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="danweiName" type="text" autocomplete="off" name="textfield" class="fidtext" />
				</div>
				<p class="text">${contractVO.depart }</p>
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">岗位性质：</div>
				<input type="text" name="textfield" class="fidtext"  disabled="disabled" value="${orgStructure.firstLevel }"/>
			</td>
			<td valign="top">
				<div class="quy_tit">省份：</div>
				<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${customerDepart.province }"/>
			</td>
		  </tr>
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">部门名称：</div>
				<input type="text" name="textfield" class="fidtext"  disabled="disabled" value="${orgStructure.secondLevel }"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">小组/细胞核：</div>
				<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${orgStructure.fourthLevel }"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">项目负责人：</div>
				<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${customerDepart.charger }"/>
				<!-- <a class="addbnt icons" href="javascript:void(0)"></a> -->
				<!-- <div style="display:none" class="Modify">
				<input id="shenqingren" type="text" name="textfield" class="fidtext" />
				<input type="button" name="button" value="确定" class="bnt" /></div> -->
				<%-- <p class="text">${contractVO.operator }</p> --%>
			</td>
			<td valign="top">
				<div class="quy_tit">联系方式：</div>
				<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${phoneNumber }"/>
				<!-- <a class="addbnt icons" href="javascript:void(0)"></a> -->
				<%-- <div style="display:none" class="Modify">
				<input id="telphone" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.lianxifangshi }</p> --%>
			</td>
		  </tr>
		  <tr>
			<td valign="top">
				<div class="quy_tit">合同金额：</div>
				<input id="contractAmount" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.contractMoney }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="contractAmountError" type="text" autocomplete="off" name="textfield" class="fidtext" />
				</div>
				<p class="text">${contractVO.contractMoney }</p>
			</td>
			<td valign="top">
				<div class="quy_tit">合同到期时间:</div>
				<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.endTime }"/>
				<a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="endTime" type="text" autocomplete="off" name="textfield" class="fidtext" />
				</div>
				<p class="text">${contractVO.endTime }</p>
			</td>
			<%-- <td valign="top">
				<div class="quy_tit">省份：</div>
				<input type="text" name="textfield" class="fidtext" disabled="disabled" value="${customerDepart.province }"/>
			</td> --%>
			
			<input id="dealCondition" type="text" style="display:none" name="textfield" class="fidtext"  value="${contract.dealConditon }"/>
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
		  </tr>
		</table>
		</div>
		
		<div class="tareaTable">
			<h3>产品信息：<a id="addProduct" class="product" href="javascript:void(0)">添加产品</a></h3>
			<table id="productTable" width="600" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="54">序号</th> 
				<th width="116">产品名称</th>
				<th width="133">产品金额（元）</th>
				<th width="99">数量和年限</th>
				<th width="98">生效日期</th>
				<th width="100">截止日期</th>
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
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="productInfo" type="text" autocomplete="off" name="textfield" class="fidtext" />
			</div>
			<p class="text" style="color:red">${contractVO.productInfo }</p>
			
		</div>
		<div class="tareaTable">
			
			<h3>发票情况：<a id="addFapiao" class="invoice" href="javascript:void(0)">增开发票</a></h3>
			<table id="tableAboutFapiao" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="6%">序号</th>
				<th width="10%">开票日期</th>
				<th width="8%">开票公司</th>
				<th width="8%">开票单位</th>
				<th width="8%">发票类型</th>
				<th width="8%">发票品名</th>
				<th width="7%">金额</th>
				<th width="7%">预计回款时间</th>
				<th width="7%">回款金额</th>
				<th width="10%">回款日期</th>
				<th width="7%">备注</th>
				<!-- <th width="8%">合同编号</th> -->
				<th width="8%">资金类型</th>
				<th width="8%">账户</th>
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
				<td>${f.yujihuikuanDate}</td>
				<td>${f.huiKuan}</td>
				<td>${f.receivedpaymentsdate}</td>
				<td>${f.remark}</td>
				<%-- <td>${f.hetongNumber}</td> --%>
				<td>${f.fundType}</td>
				<td>${f.account}</td>
				<td><a id='${f.id}' class='dele' href='javascript:;' onclick='updateFapiaoXingzheng(${f.id},this)'>修改</a></td>
			  </tr>
			  </c:forEach>
			</table>
			  <a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="fapiaoCondition" type="text"  autocomplete="off" name="textfield" class="fidtext" />
				</div>
				<p class="text" style="color:red">${contractVO.fapiaoStatus }</p>
		</div>
		<div class="tareaTable">
			<h3>快递情况：</h3>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="6%">序号</th>
				<th width="7%">收件人</th>
				<!-- <th width="9%">收件单位</th> -->
				<th width="12%">收件地址</th>
				<th width="10%">联系电话</th>
				<!-- <th width="8%">发件日期</th> -->
				<!-- <th width="7%">发件人</th> -->
				<!-- <th width="13%">发件地址</th> -->
				<th width="7%">快递内容</th>
				<th width="8%">快递公司</th>
				<th width="13%">快递单号</th>
				<!-- <th width="7%">合同编号</th> -->
				<th width="7%">邮寄日期</th>
				<th width="7%">操作</th>
			  </tr>
			  
			  <c:forEach var="f" items="${fahuoList }" >
			  <tr>
			    <td>${f.orderid}</td>
				<td>${f.d_contact}</td>
				<%-- <td>${f.d_company}</td> --%>
				<td>${f.d_address}</td>
				<td>${f.d_tel}</td>
				<%-- <td>${f.jDate}</td> --%>
				<td>${f.content}</td>
				<td>${f.postMethod}</td>
				<td>${f.mailno}</td>
				<%-- <td>${f.hetongCode}</td> --%>
				<td>${f.jDate}</td>
				<td><a id='${f.orderid}' class='dele' href='javascript:;' onclick='updateFahuoXinzheng(${f.orderid},this)'>修改</a></td>
			  </tr>
			  </c:forEach> 
			</table>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="kuaidiCondition" type="text" autocomplete="off" name="textfield" class="fidtext" />
			</div>
			<p class="text" style="color:red">${contractVO.youjiStatus }</p>
		</div>
		
		<div class="tareaTable">
			<h3>合同概要：</h3>
			<textarea id="contractContent" style="height:60px;" disabled="disabled">${contract.agreementText }</textarea>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="htgaiyao" type="text" autocomplete="off" name="textfield" class="fidtext" /></div>
			<p class="text" style="color:red">${contractVO.agreementText }</p>
		</div>
		<div class="tareaTable">
			<h3>付款方式：</h3>
			<textarea disabled="disabled">${contract.payMethod }</textarea>
			<a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify">
			<input id="payMethod" type="text" autocomplete="off" name="textfield" class="fidtext" /></div>
			<p class="text" style="color:red">${contractVO.payMethod }</p>
		</div>
		<div class="tareaTable">
			<h3>备　　注：</h3>
			<textarea style="height:60px;" disabled="disabled">${contract.remarksText }</textarea>
			<!-- <a class="addbnt icons" href="javascript:void(0)"></a>
			<div style="display:none" class="Modify"><input id="remark" type="text" name="textfield" class="fidtext" />
			<input type="button" name="button" value="确定" class="bnt" /></div> -->
		</div>
	</form>
	</div>
	<div class="ract_bottom">
		<a id="pass" class="bnt_yi" href="javascript:void(0)">通过</a>
		<a id="noPass" class="bnt_er" href="javascript:void(0)">未通过</a>
		<a id="cancel" class="bnt_sa" href="javascript:void(0)">取消</a>
	</div>
<input id='saleEmail' type="text" name="textfield" style="display:none;" class="fidtext"  value='${email}'/>

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
       <li class="li06" id="otherLi" style="display:none;">    
			<input id="other" placeholder="请输入产品名称" type="text" name="textfield" autocomplete="off" class="fidtext" style="width:120px;margin-left:80px"/>
        </li>
         <li class="li06">
       		<label>产品金额：</label>
			<input id="productMoney" type="text" autocomplete="off" name="textfield" class="fidtext" />
        </li> 
    	<li class="li06">
       		<label>时间/数量：</label>
			<input id="productAmount" type="number" autocomplete="off" name="textfield" class="fidtext" />
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

<div id="windowAboutFapiao2"class="popwindow pop_invoice" style="display:none;">
	<div class="delete" style="position: absolute; top: 0; left: 0;  right: 0; bottom: 0; z-index:0;"></div>
    <h3>新增 / 编辑 / 查看发票</h3>
    <form id="formAboutFapiao" name="formAboutFapiao" method="post" action="">
    <ul class="courier_list" style="position: relative; z-index:1;">
        <li class="li01">
        	<label>合同编号：</label>
			<input id="HtForFapiao" type="number" name="textfield" class="fidtext" />
        </li>
    	<li class="li01">
			<label>申请时间：</label><div id="applicationTimeAboutFapiao"></div>
        </li>
    	<li class="li02">
       		<label>开票金额 ：</label>
			<input id="kaipiaoAmount" type="text" name="textfield" class="fidtext" onBlur="validateFapiaoAmount()"/>
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
        
        <li class="li09" style="width:313px;">
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
        
    	<li class="li02" style="margin-left:110px;">
       		<label>开票日期：</label>
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="kaipiaoDate" name="textfield" class="fidtext"></input>
			
        </li>
       <li class="li02">
       		<label>回款金额：</label>
			<input id="huikuanAmount" type="text" name="textfield" class="fidtext"/>
        </li>
      <li class="li02">
       		<label>回款日期：</label>
			<input id="huikuanDate" type="text" name="textfield" class="fidtext" />
        </li>
    	<a id="btn_huikuanMailto" href="mailto:714757501@qq.com?body=邮件内容" style="width:60px;display:none;"  class="easyui-linkbutton">发送邮件</a>
    	<li class="li02">
       		<label>资金类型：</label>
			<select id="zijin" name="zijin" class="leftF" >
				<option value="0">现金</option>
				<option value="1">银行</option>
			</select>
        </li>
    	 <li class="li02">
       		<label>账　　户：</label>
			<select id="zhanghu" name="zhanghu" class="leftF" >
				<c:forEach var="z" items="${zhanghuList }">
					<c:if test="${z!=null }">
						<option value="1">${z.area_name }</option>
					</c:if>
				</c:forEach>
			</select>
        </li> 
    	<li class="li02">
       		<label>财务月份：</label>
			<input id ="caiwuyuefen" type="text" name="textfield" class="fidtext"/>
        </li> 
         <li id="yujihuikuanDateli" class="li02">
       		<label>开票预计回款时间:</label>
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="yujihuikuanDate" name="textfield" class="fidtext"></input>
        </li>
    	<li class="li03">
       		<label>备　　注：</label>
			<textarea id="remarkAboutFapiao"></textarea>
        </li>
        <input style="display:none;" id="fapiaoId" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04"><input id="sureAboutFapiaoXingzheng" type="button" name="button" value="确定" class="bnt" />
    	<input id="updateFapiao" type="button" name="button" value="修改" class="bnt" style="display:none;"/>
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
        	<label>合同编号:</label>
			<input id="htNumRelationFaHuo" type="text" name="textfield" class="fidtext" />
        </li>
    	<li class="li01">
			<label >申请时间：</label><div id="applicationTimeAboutFahuo"></div>
        </li>
    	<li class="li01">
       		<label>收 件 人：</label>
			<input id="receiver" type="text" name="textfield" class="fidtext" />
        </li>
    	<li class="li01">
       		<label>联系电话：</label>
			<input id="tel" type="text" name="textfield" class="fidtext" />
        </li>
    	<li class="li03">
       		<label>收件地址：</label>
			<input id="receiveAddress" type="text" name="textfield" class="fidtext" />
        </li>
    	<li class="li03">
       		<label>发 件 地：</label>
			<input id="postAddress" type="text" name="textfield" class="fidtext" />
        </li>
    	<li class="li02">
       		<label>邮寄日期：</label>
			<!-- <input id="postDate" type="text" name="textfield" class="fidtext" /> -->
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="postDate" name="textfield" class="fidtext"></input>
        </li>
        <li class="li02">
       		<label>快递公司：</label>
			<select id="expressCom" name="xuanze" class="leftF" >
				  <option value="EMS">EMS</option>
				  <option value="EMS">顺丰快递</option>
				  <option value="EMS">中铁快运</option>
			</select>
        </li> 
    	<li class="li02">
       		<label>快递编号：</label>
			<input id="expressNum" type="text" name="mailno" class="fidtext" />
        </li> 
        
         <li class="li03">
     		<label>快递内容：</label>
     	<textarea id="expressContent"></textarea>

        <input style="display:none;" id="fahuoId" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04">
    	<input id="sureAboutFaHuo" type="button" name="button" value="确定" class="bnt" />	
    	<input id="updateFahuoXingzheng" type="button" name="button" value="修改" class="bnt" style="display:none;"/>
    	<input id="deleteFahuo" type="button" name="button" value="删除" class="bnt" style="display:none;"/>
    	<a class="bnt" href="#">取消</a></li> 
    </ul>
   <!--  </form> -->
   
</div>	
	
</div>

<script type="text/javascript" src="/app/views/hetong/js/popwindow.js?i=1"></script>
<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/hetongAboutXingZheng.js"></script>
<script  type="text/javascript" src="/app/views/hetong/My97DatePicker/WdatePicker.js"></script>
<script  type="text/javascript">
$(function(){
	$('li[data-value="${contract.tiehuaStatus}"').trigger('click');
	$('li[data-value="${contract.kuaijifenlei}"').trigger('click');
})

</script>

