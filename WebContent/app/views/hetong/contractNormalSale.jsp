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
	height: initial;
}
</style>
<div class="main">
	<!-- <div class="location">首页 &gt; <a href="#">合同信息</a></div> -->
	<div class="contract">
    <form id="form1" name="form1" method="post" action="">
		<div class="tract_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td valign="top" width="25%">
				<div class="quy_tit">合同编号：</div>
				<div id="htNum" class="leftF"></div>
				<!-- <p class="text">所属公司所属公司</p> -->
			</td>
		<!-- 	<td valign="top" width="25%">
				<div class="quy_tit">项目名称：</div>
				<input type="text" name="textfield" class="fidtext" />
				<p class="text">所属公司所属公司</p>
			</td> -->
			<td valign="top" width="25%">
				<div class="quy_tit">所属公司：</div>
				<input id="company" type="text" name="textfield" class="fidtext" />
				<!-- <p class="text">所属公司所属公司</p> -->
			</td>
			<td valign="top" width="25%">
				<div class="quy_tit">单位名称：</div>
				<select id="danwei" name="danwei" class="leftF" style="width: 130px;height: 18px">
				<c:forEach var="c" items="${companyList }">
					<option value="${c.id}">${c.customerName }</option>
				</c:forEach>
				</select>
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
				<input id="userId" type="text" name="textfield" class="fidtext" disabled="disabled"/>
			</td>
			
			<td valign="top">
				<div class="quy_tit">单位ID：</div>
				<input id="danweiId" type="text" name="textfield" class="fidtext" disabled="disabled"/>
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
		  </tr>
		  
		  <tr>
			<!-- <td valign="top">
				<div class="quy_tit">申 请 人：</div>
				<input type="text" name="textfield" class="fidtext" />
			</td> -->
			<!-- <td valign="top">
				<div class="quy_tit">联系方式：</div>
				<input id="telephone" type="text" name="textfield" class="fidtext" />
			</td> -->
			<td valign="top">
				<div class="quy_tit">合同金额：</div>
				<input  id="contractAmount" type="text" name="textfield" class="fidtext" onBlur="validateContractAmount();"/>
				<p class="text" id="contractAmountError"></p>
			</td>
			<!-- <td valign="top">
				<div class="quy_tit">催账时间：</div>
				<input type="text" name="textfield" class="fidtext" />
				<div id="htNum" class="leftF"></div>
			</td> -->
		  </tr>
		  
		  <tr>
			
			<td valign="top">
				<div class="quy_tit">合同份数：</div>
				<input id="contractCount" type="text" name="textfield" class="fidtext" />
			</td>
			
			<td valign="top">
				<div class="quy_tit">项目结束时间：</div>
				<!-- <input id="endTime" type="text" name="textfield" class="fidtext" /> -->
				<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endTime" name="textfield" class="fidtext"></input>
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
			 <!--  <tr>
				<td bgcolor="#f5f6f6">1023</td>
				<td bgcolor="#f5f6f6">读秀学术搜索</td>
				<td bgcolor="#f5f6f6">100000.00</td>
				<td bgcolor="#f5f6f6">2年</td>
				<td bgcolor="#f5f6f6">2017-3-31</td>
				<td bgcolor="#f5f6f6">2018-3-31</td>
				<td bgcolor="#f5f6f6"><a class="dele" href="#">修改</a></td>
			  </tr> -->
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
				<th width="9%">回款情况</th>
				<!-- <th width="6%">申请人</th> -->
				<th width="9%">回款日期</th>
				<th width="7%">备注</th>
				<th width="6%">操作</th>
			  </tr>
			
			  <!-- <tr>
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
				<td bgcolor="#f5f6f6"><a class="dele" href="#">删除</a></td>
			  </tr> -->
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
				<th width="9%">发件日期</th>
				<th width="7%">快递内容</th>
				<th width="9%">快递公司</th>
				<th width="15%">快递单号</th>
				<th width="6%">操作</th>
			  </tr>
			 <!--  <tr>
				<td bgcolor="#f5f6f6">1023</td>
				<td bgcolor="#f5f6f6">王微微</td>
				<td bgcolor="#f5f6f6">世纪超星</td>
				<td bgcolor="#f5f6f6">上地七街</td>
				<td bgcolor="#f5f6f6">17800000000</td>
				<td bgcolor="#f5f6f6">2017-3-31</td>
				<td bgcolor="#f5f6f6">画册</td>
				<td bgcolor="#f5f6f6">申通快递</td>
				<td bgcolor="#f5f6f6">001290993021901</td>
				<td bgcolor="#f5f6f6"><a class="dele" href="#">修改</a></td>
			  </tr> -->
			  <!-- <tr>
				<td>1023</td>
				<td>王微微</td>
				<td>世纪超星</td>
				<td>上地七街</td>
				<td>17800000000</td>
				<td>2017-3-31</td>
				<td>李松宋</td>
				<td>上帝五街</td>
				<td>画册</td>
				<td>申通快递</td>
				<td>001290993021901</td>
				<td><a class="dele" href="#">删除</a></td>
			  </tr> -->
			</table>
		</div>
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
        
		<!-- <div class="tareaTable">
			<h3>问题描述：</h3>
			<textarea style="height:60px;">所属公司：公司名称错误</textarea>
		</div> -->
	</form>
	</div>
	<div class="ract_bottom">
	       <!-- 保存状态行政人员不可见 -->
		<a id="contractSave" class="bnt_yi" href="javascript:void(0)">保存</a>
		   <!-- 提交给行政人员审批 -->
		<a id="contractSubmit" class="bnt_yi" href="javascript:void(0)">提交</a>
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
        
         <li class="li06">
       		<label>产品金额：</label>
			<input id="productMoney" type="text" name="textfield" class="fidtext" />
        </li> 
    	<li class="li06">
       		<label>时间/数量：</label>
			<input id="productAmount" type="text" name="textfield" class="fidtext" />
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
			<input id="huikuanDate" type="text" name="textfield" class="fidtext" disabled='disabled'/>
        </li>
    	<li class="li02">
       		<label>回款金额：</label>
			<input id="huikuanAmount" type="text" name="textfield" class="fidtext" disabled='disabled'/>
        </li>
    	<!-- <li class="li02">
       		<label>资金类型：</label>
			<select id="zijin" name="zijin" class="leftF" >
				<option value="0">教图湖北市场</option>
				<option value="1">教图湖北市场</option>
			</select>
        </li>
    	<li class="li02">
       		<label>账　　户：</label>
			<select id="zhanghu" name="zhanghu" class="leftF" >
				<option value="0">教图湖北市场</option>
				<option value="1">教图湖北市场</option>
			</select>
        </li>  -->
    <!-- 	 <li class="li02">
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


<script type="text/javascript" src="/app/views/hetong/js/popwindow.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/hetongCreate.js?i=5"></script>
<script  type="text/javascript" src="/app/views/hetong/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	$(function(){
		$('#danwei').selectlist({
			width: 130,
			height: 18,
			//选择项目单位携带出对应的用户ID和单位ID
		 	onChange: function(){
				/* var depart = $("#danwei .select-button").val(); */
				var yonghuId = $($("#danwei").children("input").get(0)).val()
				var data ={"yonghuId":yonghuId};
				  $.get('public/ht/getUserAndDepartId.action',data,function(res){
					        var customerDepart = res.obj;
					        var userId = customerDepart.dId;
					        var danweiId = customerDepart.id;
					        $("#userId").val(userId);
					        $("#danweiId").val(danweiId);
				});  
			} 
		});		
	})
	
	$(function(){
		$('select').selectlist({
			width: 130,
			height: 18,
			
		});		
	})
	
	
	//创建合同(保存,仅销售人员可见)
	$("#contractSave").click(function(){
		var flag = true;
		var b = validateContractAmount();
		if(b==false){
			flag = false;
		}
		if(flag == false){
			return;
		}
		
		var id = $("#htNum").text();//合同编号
		var company = $("#company").val();
		var depart = $("#danwei .select-button").val();
		var cid = $("#userId").val();
		var didNum = $("#danweiId").val();
		/* var telephone = $("#telephone").val(); */
		var contractMoney = $("#contractAmount").val();
		var agreementNumber = $("#contractCount").val();
		var endTime = $("#endTime").val();
		var agreementText = $("#contractContent").val(); //合同内容
		var remarksText = $("#remark").val();  //备注
		var payMethod = $("#payMethod").val();
		var yonghuId = $($("#danwei").children("input").get(0)).val();//用户ID
		
		/* var data ={"company":company,"depart":depart,"cid":cid,"didNum":didNum,"contractMoney":contractMoney,
				"agreementNumber":agreementNumber,"endTime":endTime}; */
		var data={"id":id,"company":company,"depart":depart,"cid":cid,"didNum":didNum,
						"contractMoney":contractMoney,
						"agreementNumber":agreementNumber,"endTime":endTime,"agreementText":agreementText,
						"remarksText":remarksText,"payMethod":payMethod,"yonghuId":yonghuId};		
		 $.post('public/ht/addContractNormal.action',data,function(res){
				if(res.success==true){
					$.messager.alert('提示：',res.msg);
					$.get('public/ht/contractListSale.action',function(result){
						$('#container').html(result);
						})
				}else{
					$.messager.alert('提示：',res.msg);
				 }
		}); 
		
	})
	
	
	
		//创建合同(提交)
	$("#contractSubmit").click(function(){
		var flag = true;
		var b = validateContractAmount();
		if(b==false){
			flag = false;
		}
		if(flag == false){
			return;
		}
		
		
		var id = $("#htNum").text();//合同编号
		
		var company = $("#company").val();
		var depart = $("#danwei .select-button").val();
		
		var cid = $("#userId").val();
		var didNum = $("#danweiId").val();
		/* var telephone = $("#telephone").val(); */
		var contractMoney = $("#contractAmount").val();
		var agreementNumber = $("#contractCount").val();
		var endTime = $("#endTime").val();
		var agreementText = $("#contractContent").val(); //合同内容
		var remarksText = $("#remark").val();  //备注
		var payMethod = $("#payMethod").val();
		var yonghuId = $($("#danwei").children("input").get(0)).val();//用户ID
		/* var data ={"company":company,"depart":depart,"cid":cid,"didNum":didNum,"contractMoney":contractMoney,
				"agreementNumber":agreementNumber,"endTime":endTime}; */
		var data={"id":id,"company":company,"depart":depart,"cid":cid,"didNum":didNum,
						"contractMoney":contractMoney,
						"agreementNumber":agreementNumber,"endTime":endTime,"agreementText":agreementText,
						"remarksText":remarksText,"payMethod":payMethod,"yonghuId":yonghuId};		
		 $.post('public/ht/addContractNomalSubmit.action',data,function(res){
				if(res.success==true){
					$.messager.alert('提示：',res.msg);
					$.get('public/ht/contractListSale.action',function(result){
						$('#container').html(result);
						})
				}else{
					$.messager.alert('提示：',res.msg);
				 } 
		}); 
		
	})
	
	//取消按钮
	$("#contractCancel").click(function(){
		var id = $("#htNum").text();//合同编号
		
		$.messager.confirm("提示","您确认要放弃当前操作吗？",function(){
			
			//如果没有生成合同，直接回到合同列表页面;如果已经生成合同，提示 要放弃当前操作吗?  删除当前合同和合同所属的产品,发票,快递
			if(id!=null&&id!=""){
				var data = {"id":id};
				$.post('public/ht/contractNormalCancel.action',data,function(res){
					if(res.success==true){
						$.get('public/ht/contractListSale.action',function(result){
							$('#container').html(result);
							})
					   }
					})
			}else{
				$.get('public/ht/contractListSale.action',function(result){
					$('#container').html(result);
					})
			 }
		  })
	  })
	
	
//点添加产品时，创建合同对象
$("#addProduct").click(function(){
	$("#sureAboutProduct").attr("style","display:inline;"); 
	$("#updateProduct").attr("style","display:none;");
	$("#deleteProduct").attr("style","display:none;");
	
	$("#productMoney").val("");
	$("#productAmount").val("");
	$("#effectiveDate").val("");
	$("#endDate").val("");
	
	
	var id = $("#htNum").text();//合同编号
	if(id==""||null==id){
		 $.get('public/ht/addRealContract.action',function(res){
			 if(res.success==true){
				var htId =  res.obj;
				console.log(htId);
				$("#htNum").text(htId);
			 }
			 
		}); 
	}else{
	}
})
//点击新增快递	
$("#addFahuo").click(function(){
	$("#sureAboutFaHuo").attr("style","display:inline;");
	$("#updateFahuo").attr("style","display:none;");
	$("#deleteFahuo").attr("style","display:none;");
	
	$("#receiver").val("");
	$("#tel").val("");
	/* $("#receiveCom").val(""); */
	$("#email").val("");
	$("#receiveAddress").val("");
	$("#post").val("");
	$("#postAddress").val("");
	$("#postDate").val("");
	$("#expressCom .select-button").val("EMS");
	$("#expressNum").val("");
	$("#expressContent").val("");
	//$("#remarkAboutExpress").val("");
})	

//点击增开发票
$("#addFapiao").click(function(){

	
	$("#sureAboutFapiao").attr("style","display:inline;");
	$("#updateFapiao").attr("style","display:none;");
	$("#deleteFapiao").attr("style","display:none;");
	
	$("#kaipiaoAmount").val("");
	$("#daxieAmount").val("");
	$("#kaipiaoCompany").val("");
	$("#kaipiaodDanwei").val("");
	$("#fapiaoType").val("");
	$("#pinming").val("");
	$("#kaipiaoDate").val("");
	$("#remarkAboutFapiao").val("");
	$("#kaipiaoAmountError").text("");
})	


	
	
$("#addFapiao").click(function(){
	//合同编号
	var id = $("#htNum").text();
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
	//所属公司
	var company = $("#company").val();
	//单位名称
	var danwei = $("#danwei .select-button").val();
	$("#HtForFapiao").text(id);
	$("#applicationTimeAboutFapiao").text(currentTime);
	$("#kaipiaoCompany").val(company);
	$("#kaipiaodDanwei").val(danwei);

})


$("#addFahuo").click(function(){
	//合同编号
	var id = $("#htNum").text();
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
	//获取发件地
	//获取所属公司
	var company = $("#company").val();
	if(company!=""){
		$.post('public/ht/getFajiandi.action',{"company":company},function(res){
			 if(res.success==true){
				
				var companyInfo = res.obj;
				$("#postAddress").val(companyInfo.address);
			 }else{
				$("#postAddress").val("");
			 }
			 
		}); 
	}
	
	
	$("#htNumRelationFaHuo").text(id);
	$("#applicationTimeAboutFahuo").text(currentTime);
})	

//验证合同金额是否合法
function validateContractAmount(){
     var reg = new RegExp("^[0-9]*$");
     var contractAmount = $("#contractAmount").val();
     if(reg.test(contractAmount)){
    	 $("#contractAmountError").text("");
     }else{
    	 $("#contractAmountError").text("合同金额请输入阿拉伯数字");
    	 return false;
     }
		
	}

	//验证发票金额是否合法
	function validateFapiaoAmount(){
	    var reg = new RegExp("^[0-9]*$");
	 	var id = $("#htNum").text();//合同编号
		var kaipiaoAmount = $("#kaipiaoAmount").val();//开票金额
		var htTotalAmount = $("#contractAmount").val();//合同总金额
		
	     var total = 0;
		 var trList = $("#tableAboutFapiao").find("tr");
		    for (var i=0;i<trList.length;i++) {
		        var tdArr = trList.eq(i).find("td");
		        var fapiaoAmount = tdArr.eq(6).text();
		        var total = Number(fapiaoAmount)+Number(total);
		    }
		 
		 if(Number(kaipiaoAmount)+Number(total)>Number(htTotalAmount)){
			 $("#kaipiaoAmountError").text("*开票总金额不能大于合同金额");
			 return false;
		 }
		
			/* 	 $.get('public/ht/getTotalFapiaoAmount.action',{"id":id},function(res){
		 if(res.success==true){
			var a = res.obj;
			debugger;
			var total = Number(a)+Number(kaipiaoAmount);
			 if(parseInt(total)>parseInt(htTotalAmount)){
				 $("#kaipiaoAmountError").text("*开票总金额不能大于合同金额");
				 return false;
			 }
		 }
		 
	}); */ 
		
		
	     if(reg.test(kaipiaoAmount)){
	    	 $("#kaipiaoAmountError").text("");
	     }else{
	    	 $("#kaipiaoAmountError").text("*开票金额请输入阿拉伯数字");
	    	 return false;
	     }
	     
	     if(parseInt(kaipiaoAmount)>parseInt(contractAmount)){
	    	 $("#kaipiaoAmountError").text("*开票金额不能大于合同金额");
	    	 return false;
	     }
		}	

</script>
<script type="text/javascript">
Date.prototype.Format = function(format){ 

	var o = { 

	"M+" : this.getMonth()+1, //month 

	"d+" : this.getDate(), //day 

	"h+" : this.getHours(), //hour 

	"m+" : this.getMinutes(), //minute 

	"s+" : this.getSeconds(), //second 

	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 

	"S" : this.getMilliseconds() //millisecond 

	}

	if(/(y+)/.test(format)) { 

	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 

	}

	for(var k in o) { 

	if(new RegExp("("+ k +")").test(format)) { 

	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 

	 } 

	} 

	return format; 

	}
</script>

