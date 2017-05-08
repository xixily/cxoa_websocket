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
<link href="/app/views/hetong/css/space.css" rel="stylesheet" type="text/css" />
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
					<input id="guidangfenshu" type="text" name="textfield" class="fidtext" value="${contract.guidangNum }"/>
				</li>
				<li>
					<div class="quy_tit">合同份数：</div>
					<input type="text" name="textfield" class="fidtext" value="${contract.agreementNumber }"/>
				</li>
				<li>
					<div class="quy_tit">归档编号：</div>
					<input id="guidangNum" type="text" name="textfield" class="fidtext" disabled="disabled" value="${contract.guidangCode }" />
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
				<%-- <a class="addbnt icons" href="javascript:void(0)"></a>
				<div style="display:none" class="Modify">
				<input id="dengjiTime" type="text" name="textfield" class="fidtext" />
				<!-- <input type="button" name="button" value="确定" class="bnt" /> --></div>
				<p class="text">${contractVO.dengjiTime }</p> --%>
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
				<div class="quy_tit">部门名称:</div>
				<input type="text" name="textfield" class="fidtext"  value="${customerDepart.secondLevel }"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">小组/细胞核:</div>
				<input type="text" name="textfield" class="fidtext" value="${customerDepart.fourthLevel }"/>
			</td>
			
			
			<td valign="top">
				<div class="quy_tit">项目负责人:</div>
				<input type="text" name="textfield" class="fidtext" value="${customerDepart.charger }"/>
				<!-- <a class="addbnt icons" href="javascript:void(0)"></a> -->
				<!-- <div style="display:none" class="Modify">
				<input id="shenqingren" type="text" name="textfield" class="fidtext" />
				<input type="button" name="button" value="确定" class="bnt" /></div> -->
				<%-- <p class="text">${contractVO.operator }</p> --%>
			</td>
			<td valign="top">
				<div class="quy_tit">联系方式：</div>
				<input type="text" name="textfield" class="fidtext" value="${renshiUserName.phoneNumber }"/>
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
			<table id="tableAboutFapiao" width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="6%">序号</th>
				<th width="10%">开票日期</th>
				<th width="8%">开票公司</th>
				<th width="8%">开票单位</th>
				<th width="8%">发票类型</th>
				<th width="8%">发票品名</th>
				<th width="7%">金额</th>
				<th width="7%">回款情况</th>
				<!-- <th width="7%">申请人</th> -->
				<th width="10%">回款日期</th>
				<th width="7%">备注</th>
				<th width="8%">合同编号</th>
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
				<td>${f.huiKuan}</td>
				<td>${f.receivedpaymentsdate}</td>
				<td>${f.remark}</td>
				<td>${f.hetongNumber}</td>
				<td>${f.fundType}</td>
				<td>${f.account}</td>
				<td><a id='${f.id}' class='dele' href='javascript:;' onclick='updateFapiaoXingzheng(${f.id},this)'>修改</a></td>
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
				<!-- <th width="9%">收件单位</th> -->
				<th width="12%">收件地址</th>
				<th width="10%">联系电话</th>
				<th width="8%">发件日期</th>
				<!-- <th width="7%">发件人</th> -->
				<!-- <th width="13%">发件地址</th> -->
				<th width="7%">快递内容</th>
				<th width="8%">快递公司</th>
				<th width="13%">快递单号</th>
				<th width="7%">合同编号</th>
				<th width="7%">操作</th>
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
				<td>${f.hetongCode}</td>
				<td><a id='${f.orderid}' class='dele' href='javascript:;' onclick='updateFahuo(${f.orderid},this)'>修改</a></td>
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
		
		<!-- <div class="tareaTable">
			<h3>问题描述：</h3>
			<textarea style="height:60px;"></textarea>
		</div> -->
	</form>
	</div>
	<div class="ract_bottom">
		<a id="pass" class="bnt_yi" href="javascript:void(0)">通过</a>
		<a id="noPass" class="bnt_er" href="javascript:void(0)">未通过</a>
		<a id="cancel" class="bnt_sa" href="javascript:void(0)">取消</a>
	</div>
	
	
	
<div class="maskLayer"></div>	
<div class="popwindow pop_invoice" style="display:none;">
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
        
    	<li class="li05">
       		<label>发票品名：</label>
			<input id="pinming" type="text" name="textfield" class="fidtext" />
        </li>
        
    	<li class="li02">
       		<label>开票日期：</label>
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="kaipiaoDate" name="textfield" class="fidtext"></input>
			
        </li>
    	<li class="li02">
       		<label>回款日期：</label>
			
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="huikuanDate" name="textfield" class="fidtext" ></input>
			
        </li>
    	<li class="li02">
       		<label>回款金额：</label>
			<input id="huikuanAmount" type="text" name="textfield" class="fidtext"/>
        </li>
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
        
    	<!--  <li class="li02">
       		<label>财务月份：</label>
			<input id ="" type="text" name="textfield" class="fidtext" disabled='disabled'/>
        </li>  -->
    	<li class="li03">
       		<label>备　　注：</label>
			<textarea id="remarkAboutFapiao"></textarea>
        </li>
        <input style="display:none;" id="fapiaoId" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04"><input id="sureAboutFapiao" type="button" name="button" value="确定" class="bnt" />
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
        	<label >合同编号：</label><div id="htNumRelationFaHuo"></div>
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
			<input id="receiveAddress" type="text" name="textfield" class="fidtext" />
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
				  <option value="EMS">申通快递</option>
				  <option value="EMS">圆通快递</option>
				  <option value="EMS">中通快递</option>
				  <option value="EMS">韵达快递</option>
				  <option value="EMS">顺丰快递</option>
				  <option value="EMS">汇通快递</option>
				  <option value="EMS">天天快递</option>
				  <option value="EMS">宅急送</option>
			</select>
        </li> 
        
        
    	<li class="li02">
       		<label>快递编号：</label>
			<input id="expressNum" type="text" name="mailno" class="fidtext" />
        </li> 
        
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
   
</div>	
	
	
	
	
	
</div>

<script type="text/javascript" src="/app/views/hetong/js/popwindow.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/hetongCreate.js?i=5"></script>
<script  type="text/javascript" src="/app/views/hetong/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(function(){
		$('select').selectlist({
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
        var guidangCode = $("#guidangNum").val();//归档编号
        var guidangNum = $("#guidangfenshu").val();//归档份数
		 $.post('public/ht/updateContractXingzheng.action',{"id":id,"yinhuashui":yinhuashui,"guidangDate":guidangDate,"huaizhangAmount":huaizhangAmount,"company":company,"guidangCode":guidangCode,"guidangNum":guidangNum},function(res){
		 }) 
		
		 /*  $.get('public/ht/deleteErrorInfo.action',{"id":id},function(res){
			  
		 })  */
		
		 $.post('public/ht/updateContractCondition.action',{"id":id},function(res){
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
	$.post('public/ht/addErrorInfo.action',data,function(res){
		
	})
	
	 $.post('public/ht/updatecontractNoPass.action',{"id":id},function(res){
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

$("#cancel").click(function(){
	$.get('public/ht/contractList.action',function(result){
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
	
 
//更新发票(回显)
function updateFapiaoXingzheng(id,obj){
	tr = $(obj).parent().parent();
	
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFapiao").attr("style","display:none");
	$("#updateFapiao").attr("style","display:inline");
	$("#deleteFapiao").attr("style","display:inline");
	var data ={"fapiaoId":id}
	
	//合同编号
	var id = $("#contractId").text();
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
	$("#HtForFapiao").text(id);
	$("#applicationTimeAboutFapiao").text(currentTime);
	
	$.post('public/ht/toUpdateFaPiao.action',data,function(res){
		
		 if(res.success==true){
		 var fapiao = res.obj;
		 var date2 = new Date(fapiao.date).Format("yyyy-MM-dd");
		 
		 if(fapiao.receivedpaymentsdate==null){
			 $("#huikuanDate").val("");
		 }else{
			 var receivedpaymentsdate2 = new Date(fapiao.receivedpaymentsdate).Format("yyyy-MM-dd")
			 $("#huikuanDate").val(receivedpaymentsdate2);
		 }
			/*var hetongNumber =$("#htNum").text();//合同编号
			var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间*/
			$("#kaipiaoAmount").val(fapiao.money);
			$("#daxieAmount").val(fapiao.capitalMoney);
			$("#kaipiaoCompany").val(fapiao.company);
			$("#kaipiaodDanwei").val(fapiao.departMement);
			/* $("#fapiaoType").val(fapiao.type); */
			$("#pinming").val(fapiao.name);
			$("#kaipiaoDate").val(date2);
			$("#remarkAboutFapiao").val(fapiao.remark);
			$("#fapiaoId").attr("value",fapiao.id);
			$("#fapiaoType .select-button").attr("value",fapiao.type);
			
			$("#huikuanAmount").val(fapiao.huiKuan);
			$("#zijin .select-button").val(fapiao.fundType);
			$("#zhanghu .select-button").val(fapiao.account);
			
		 }
	}); 
	
	$(".maskLayer").show();		
	if($(".maskLayer").css('display')=='block'){
		$(".maskLayer").fadeTo('fast',0.7);
		$('.maskLayer').height($(document).height());
	}		
	$(".pop_invoice").show();
	$('.pop_invoice').css({left:function(){
		return ($(window).width()-$(this).width())/2;
	},top:function(){
		return ($(window).height()-$(this).height())/2+$(window).scrollTop();	
	}
});
return false;
}


//修改发票
$("#updateFapiao").click(function(){
	
	var hetongNumber =$("#contractId").text()
	/*var hetongNumber =$("#htNum").text();//合同编号
	var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间*/
	var fapiaoID = $("#fapiaoId").val();
	var money =$("#kaipiaoAmount").val();
	var capitalMoney =$("#daxieAmount").val();
	var company =$("#kaipiaoCompany").val();
	var departMement =$("#kaipiaodDanwei").val();
	var type =$("#fapiaoType .select-button").val();
	var name =$("#pinming").val();
	var date =$("#kaipiaoDate").val();
	var remark = $("#remarkAboutFapiao").val();
	var huiKuan = $("#huikuanAmount").val();//回款金额
	var receivedpaymentsdate = $("#huikuanDate").val();//回款日期
	
	
	var fundType = $("#zijin .select-button").val();//资金类型
	var account = $("#zhanghu .select-button").val();//账户
	debugger;
	if(fundType==undefined){
		fundType = "";
	}
	console.log(fundType);
	if(account==undefined){
		account = "";
	}
	console.log(account);

	var data ={"money":money,"capitalMoney":capitalMoney,
			"company":company,"departMement":departMement,"type":type,"name":name,"date":date,
			"remark":remark,"fapiaoID":fapiaoID,"receivedpaymentsdate":receivedpaymentsdate,"huiKuan":huiKuan,"fundType":fundType,"account":account};
	
	$.post('public/ht/doUpdateFapiao.action',data,function(res){
		
		 if(res.success==true){
			 $.messager.alert('提示：',res.msg);
			
			 tr.children('td').eq(0).text(fapiaoID);
			 tr.children('td').eq(1).text(date);
			 tr.children('td').eq(2).text(company);
			 tr.children('td').eq(3).text(departMement);
			 tr.children('td').eq(4).text(type);
			 tr.children('td').eq(5).text(name);
			 tr.children('td').eq(6).text(money);
			 tr.children('td').eq(7).text(huiKuan);
			 tr.children('td').eq(8).text(receivedpaymentsdate);
			 tr.children('td').eq(9).text(remark);
			 tr.children('td').eq(10).text(hetongNumber);
			 tr.children('td').eq(11).text(fundType);
			 tr.children('td').eq(12).text(account);
			 
			 $(".pop_invoice").hide();
			 $(".maskLayer").hide();
			 //遍历整个表格获取回款情况总和
			  var total = 0; //总回款金额
			  var contractId = $("#contractId").text();
		      var trList = $("#tableAboutFapiao").find("tr");
		      var latestDate;//最新日期
		     /*  for (var i=0;i<trList.length;i++) {
			        var tdArr = trList.eq(0).find("td");//一行所有的td
			        latestDate = tdArr.eq(8).text();
			    } */
		      
		     var latestDate = $("#tableAboutFapiao").find("tr:last").children().eq(8).text();
		      
		     var latestDate2 = new Date(Date.parse(latestDate));
		    debugger;
		    for (var i=0;i<trList.length;i++) {
		        var tdArr = trList.eq(i).find("td");//一行所有的td
		        var huikuanAmount = tdArr.eq(7).text();
		        var total = Number(huikuanAmount)+Number(total);
		        var huikuanDate = tdArr.eq(8).text();
		        var huikuanDate2 = new Date(Date.parse(huikuanDate));
		        if(huikuanDate2>latestDate2){
		        	latestDate2 = huikuanDate2;
		        }
		        var latestDate_ = latestDate2.Format("yyyy-MM-dd");
		    }
			 var data={"contractId":contractId,"total":total,"latestDate2":latestDate_};
		    $.post('public/ht/updateHuikuanAndDate.action',data,function(res){
				 if(res.success==true){
					console.log("更新回款金额和日期成功!")
					
				 }
				 
			}); 
		    
	        
		 }else{
			$.messager.alert('提示：',res.msg);
		 }
	});
})


 
</script>
