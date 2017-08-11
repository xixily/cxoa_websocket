<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/app/views/hetong/css/global.css?v=1" rel="stylesheet" type="text/css" />
<!-- <link href="/app/views/hetong/css/space.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/jquery-ui.css" rel="stylesheet" type="text/css" /> -->
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
    border: 1px solid #ccc;
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
    width: initial;
}
</style>
<div class="main">
	<div class="contract">
    <form id="form1" name="form1" method="post" action="">
		<div class="tract_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">合同编号：</div>
				<div id="htNum" class="leftF"></div>
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">所属公司：</div>
				<input id="company" type="text" name="textfield" class="fidtext" />
			</td>
			
			<td valign="top" width="25%">
				<div class="quy_tit">单位名称：</div>
				<select id="danwei" name="danwei" class="leftF" style="width: 130px;height: 18px">
				<c:forEach var="c" items="${companyList }">
					<option value="${c.id}">${c.customerName }</option>
				</c:forEach>
				</select>
			</td>
		  </tr>
		  <tr>
			<td valign="top">
				<div class="quy_tit">用户ID：</div>
				<input id="userId" type="text" name="textfield" class="fidtext" disabled="disabled"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">单位ID：</div>
				<input id="danweiId" type="text" name="textfield" class="fidtext" disabled="disabled"/>
			</td>
			<td valign="top">
				<div class="quy_tit">合同金额：</div>
				<input  id="contractAmount" type="text" autocomplete="off" name="textfield" class="fidtext" onBlur="validateContractAmount();"/>
				<p class="text" id="contractAmountError"></p>
			</td>
		  </tr>
		  <tr>
			<td valign="top">
				<div class="quy_tit">合同份数：</div>
				<input id="contractCount" type="text" autocomplete="off" name="textfield" class="fidtext" />
			</td>
			<td valign="top">
				<div class="quy_tit">合同到期时间：</div>
				<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endTime" name="textfield" class="fidtext"></input>
			</td>
		  </tr>
		</table>
		</div>
	<!-- 下面为三个列表 -->
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
			</table>
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
				<th width="10%">预计回款时间</th>
				<!-- <th width="9%">回款情况</th>
				<th width="9%">回款日期</th> -->
				<th width="7%">备注</th>
				<th width="6%">操作</th>
			  </tr>
			</table>
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
				<th width="7%">快递内容</th>
				<th width="9%">快递公司</th>
				<!-- <th width="15%">快递单号</th> -->
				<th width="6%">操作</th>
			  </tr>
			</table>
		</div>
	<!-- 尾部三个输入框 -->
		<div class="tareaTable">
			<h3>合同概要：</h3>
			<textarea id="contractContent" style="height:60px;"></textarea>
		</div>
		<div class="tareaTable">
			<h3>付款方式：</h3>
			<textarea id="payMethod"></textarea>
		</div>
		<div class="tareaTable">
			<h3>备　　注：</h3>
			<textarea id="remark" style="height:60px;"></textarea>
		</div>
        
	</form>
	</div>
	<div class="ract_bottom">
		<a id="contractSave" class="bnt_yi" href="javascript:void(0)">保存</a>
		<a id="contractSubmit" class="bnt_yi" href="javascript:void(0)">提交</a>
		<a id="contractCancel" class="bnt_sa" href="javascript:void(0)">取消</a>
	</div>
</div>

<!-- 下面为三个弹框 -->
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
			   <option value="其他">其他</option>
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
			<input id="productMoney" type="text" name="textfield" autocomplete="off" class="fidtext" />
			<p id="productMoneyError" class="text"></p>
        </li> 
    	<li class="li06">
       		<label>时间/数量：</label>
			<input id="productAmount" type="text" name="textfield" autocomplete="off" class="fidtext" />
        </li>
    	<li class="li06">
       		<label>生效日期：</label>
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="effectiveDate" name="textfield" class="fidtext"></input>
        </li>
    	<li class="li06">
       		<label>截止日期：</label>
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endDate" name="textfield" class="fidtext"></input>
        </li>
         <input style="display:none;" id="productID" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04">
    	<input id="sureAboutProduct" type="button" name="button" value="确定" class="bnt"/>
    	<input id="updateProduct" type="button" name="button" value="修改" class="bnt" style="display:none;"/>
    	<input id="deleteProduct" type="button" name="button" value="删除" class="bnt" style="display:none;"/>
    	<a id="cancelAboutProduct" class="bnt" href="#">取消</a></li>
    </ul>
    
</div>

<div id="windowAboutFapiao" class="popwindow pop_invoice" style="display:none;">
	<div class="delete"style="position: absolute; top: 0; left: 0;  right: 0; bottom: 0; z-index:0;"></div>
    <h3>新增 / 编辑 / 查看发票</h3>
    <form id="form1" name="form1" method="post" action="">
    <ul class="courier_list" style="position: relative; z-index:1;">
    	<li class="li01">
        	<label>合同编号：</label><div id="HtForFapiao"></div>
        </li>
    	<li class="li01">
			<label>申请时间：</label><div id="applicationTimeAboutFapiao"></div>
        </li>
    	
    	<li class="li02">
       		<label>开票金额：</label>
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
        
		<li class="li08">
       		<label style="width:128px">开票预计回款时间：</label>
			<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="yujihuikuanDate" name="textfield" class="fidtext"></input>
        </li>
    	
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
			<input id="receiver" type="text" name="textfield" autocomplete="off" class="fidtext" />
        </li>
    	<li class="li01">
       		<label>联系电话：</label>
			<input id="tel" type="text" name="textfield" autocomplete="off" class="fidtext" />
        </li>
    	<li class="li03">
       		<label>收件地址：</label>
			<input id="receiveAddress" type="text" autocomplete="off" name="textfield" class="fidtext" />
        </li>
    	<li class="li03">
       		<label>发 件 地：</label>
			<input id="postAddress" type="text" name="textfield" class="fidtext" />
        </li>
        <li class="li02">
       		<label>快递公司：</label>
			<select id="expressCom" name="xuanze" class="leftF" >
				  <option value="EMS">EMS</option>
				  <option value="EMS">顺丰快递</option>
				  <option value="EMS">中铁快运</option>
			</select>
        </li>
         <li class="li03">
     		<label>快递内容：</label>
     	<textarea id="expressContent"></textarea>

        <input style="display:none;" id="fahuoId" type="text" name="textfield" class="fidtext" value=""/>
    	<li class="li04">
    	<input id="sureAboutFaHuo" type="button" name="button" value="确定" class="bnt" />	
    	<input id="updateFahuo" type="button" name="button" value="修改" class="bnt" style="display:none;"/>
    	<input id="deleteFahuo" type="button" name="button" value="删除" class="bnt" style="display:none;"/>
    	<a class="bnt" href="#">取消</a></li> 
    </ul>
   <!--  </form> -->
</div>

<script type="text/javascript" src="/app/views/hetong/js/popwindow2.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/jquery-ui.js"></script>
<!-- <script type="text/javascript" src="/app/views/hetong/js/hetongCreateSale.js?v=3"></script> -->
<script type="text/javascript" src="/app/views/hetong/js/saleCommon.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/contractNormalSale.js"></script>
<script type="text/javascript" src="/app/views/hetong/My97DatePicker/WdatePicker.js"></script>
