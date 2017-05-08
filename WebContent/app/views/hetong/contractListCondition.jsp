<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->

<link href="/app/views/hetong/css/global.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/style.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet" type="text/css" />

<style>
.main * {
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
}

.main {
    width: inherit;
}

.divtable {
    width: inherit;
}
</style>
<div app-data="body">
	<div class="main">
		<div class="untreated"><span>未处理合同：<a href="#">${unhandledCount }</a>份</span><!-- <span>未处理发票：<a href="#">15</a>张</span> --></div>
		<div class="query">
		<form id="form1" name="form1" method="post" action="">
			<h2>查询条件</h2>
			<div class="query_list">
				<ul>
					<!-- <li>
						<div class="quy_tit">合同信息：</div>
						<input type="text" name="textfield" class="fidtext" />
					</li> -->
					<li>
						<div class="quy_tit">年　　份：</div>
						<select id="edu" name="edu" class="leftF">
							<option value="0">2017年</option>
							<option value="1">2016年</option>
							<option value="2">2015年</option>
						</select>
					</li>
					<!-- <li style="width:468px;">
						<div class="quy_tit">起止日期：</div>
						<input type="text" name="textfield" class="fidtext" />
						<span class="leftF" style=" margin:0 10px;">至</span>
						<input type="text" name="textfield" class="fidtext" />
					</li> -->
					<li>
						<div class="quy_tit">购买单位：</div>
						<input id="purchaseCom" type="text" name="textfield" class="fidtext" />
					</li>
					<li>
						<div class="quy_tit">所属公司：</div>
						<select id="gongsi" name="gongsi" class="leftF">
							<option value="0">全部</option>
							<option value="1">超星</option>
						</select>
					
					</li>
					<li>
						<div class="quy_tit">单位性质：</div>
						<select id="danwei" name="danwei" class="leftF">
							<option value="0">全部</option>
							<option value="1">超星</option>
						</select>
					</li>
					<li>
						<div class="quy_tit">产品名称：</div>
						<select id="chanpin" name="chanpin" class="leftF">
							<option value="0">全部</option>
							<option value="1">超星</option>
						</select>
					</li>
					<li>
						<div class="quy_tit">省　　份：</div>
						<select id="shengfen" name="shengfen" class="leftF">
							<option value="0">全国</option> <option value="2">广东</option>
							<option value="1">北京</option> <option value="2">黑龙江</option>
							<option value="2">上海</option> <option value="2">吉林</option>
						</select>
					</li>
					<li>
						<div class="quy_tit">小组/细胞核：</div>
						<input id="group" type="text" name="textfield" class="fidtext" />
					</li>
					<li>
						<div class="quy_tit">项目负责人：</div>
						<input id="responsibility" type="text" name="textfield" class="fidtext" />
					</li>
					<li>
						<div class="quy_tit">用户ID：</div>
						<input id="userId" type="text" name="textfield" class="fidtext" />
					</li>
				<!-- 	<li>
						<div class="quy_tit">合同金额：</div>
						<input type="text" name="textfield" class="fidtext" style="width:47px;" />
						<span class="leftF" style=" margin:0 2px;">至</span>
						<input type="text" name="textfield" class="fidtext" style="width:47px;" />
					</li> -->
					<li>
						<div class="quy_tit">合同状态：</div>
						<select id="zhuangtai" name="zhuangtai" class="leftF">
							<option value="0">全部</option>
							<option value="1">款项</option>
						</select>
					</li>
					<!-- <li>
						<div class="quy_tit">回帐情况：</div>
						<select id="huizhang" name="huizhang" class="leftF">
							<option value="0">全部</option>
							<option value="1">款项</option>
						</select>
					</li> -->
				</ul>
				<div class="clear"></div>
			</div>
			<div class="query_bnt">
				<input id="search" type="button" name="button" value="搜索" class="bnt" /><input type="reset" name="button" value="重置" class="bnt" style="background:#c9c9c9;" />
			</div>
		</form>
		</div>
		<div class="contract">
			<div class="divcheck">
				<!-- <label class="leftF"><input type="checkbox" name="checkbox" />全选</label> -->
				<!-- <a class="rease leftF" href="#">+新增合同</a>
				<a class="dele leftF" href="#">删除合同</a> -->
				<a class="expor leftF" href="/file/exportContractExcel.action">导出</a>
				<span class="leftF"><b class="add icons"></b>坏账合同</span>
				<span class="leftF"><b class="rec icons"></b>待处理合同</span>
				<label class="leftF"><input id="undeal" type="checkbox" name="checkbox" />只显示未处理</label>
			</div>
			<div class="divtable">
				<div style="width:1500px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<th width="4%">序号</th>
					<th width="8%">合同编号</th>
					<!-- <th width="11%">合同名称</th> -->
					<th width="8%">购买单位</th>
					<th width="8%">单位性质</th>
					<th width="5%">省份</th>
					<th width="10%">小组/细胞核</th>
					<th width="6%">负责人</th>
					<th width="9%">提交日期</th>
					<th width="10%">所含产品</th>
					<th width="8%">所属公司</th>
					<th width="7%">合同金额</th>
					<th width="7%">回款金额</th>
					<th width="7%">回款时间</th>
					<th width="7%">坏账</th>
					<th width="8%">处理状态</th>
					<th width="4%">详情</th>
				  </tr>
				  
			
				  
				   
				    
				    <c:forEach var="c" items="${pageBean.list }" >
				   <%--  <c:if test="${c.dealConditon=='0'}">
				    <tr>
					<td bgcolor="#dcfcd3">1</td>
					<td bgcolor="#dcfcd3">${c.id }</td>
					<td bgcolor="#dcfcd3">${c.depart }</td>
					<td bgcolor="#dcfcd3">${c.company_property }</td>
					<td bgcolor="#dcfcd3">${c.province }</td>
					<td bgcolor="#dcfcd3">${c.fourthLevel }</td>
					<td bgcolor="#dcfcd3">${c.operator }</td>
					<td bgcolor="#dcfcd3">${c.dengjiTime }</td>
					<td bgcolor="#dcfcd3">${c.pingming }</td>
					<td bgcolor="#dcfcd3">${c.company }</td>
					<td bgcolor="#dcfcd3">${c.contractMoney }</td>
				    <td bgcolor="#dcfcd3">未处理</td>
					<td bgcolor="#dcfcd3"><a href="/public/ht/contractDetail.action?id=${c.id }">详情</a></td>
			        </tr>
				    </c:if> --%>
				    
				    
				    <tr>
					<td>1</td>
					<td>${c.id }</td>
					<td>${c.depart }</td>
					<td>${c.company_property }</td>
					<td>${c.province }</td>
					<td>${c.fourthLevel }</td>
					<td>${c.operator }</td>
					<td>${c.dengjiTime }</td>
					<td>${c.pingming }</td>
					<td>${c.company }</td>
					<td>${c.contractMoney }</td>
				   <%-- <c:if test="${c.dealConditon==''||c.dealConditon==null}"><td>未知</td></c:if> --%>
				   <c:if test="${c.dealConditon=='0'}"><td>未处理</td></c:if>
                   <c:if test="${c.dealConditon=='1'}"><td>审核未通过</td></c:if>
                   <c:if test="${c.dealConditon=='2'}"><td>审核已通过</td></c:if>
                   <c:if test="${c.dealConditon=='3'}"><td>合同完结</td></c:if>
				   <td ><a class="detail" id="${c.id }" href="javascript:void(0)">详情</a></td>
				   </tr>
				   
				   	<!--   <tr>
					<td bgcolor="#dcfcd3">1</td>
					<td bgcolor="#dcfcd3">cx10285</td>
					<td bgcolor="#dcfcd3">吉大尔雅项目</td>
					<td bgcolor="#dcfcd3">吉林大学</td>
					<td bgcolor="#dcfcd3">教图</td>
					<td bgcolor="#dcfcd3">吉林</td>
					<td bgcolor="#dcfcd3">王允亚</td>
					<td bgcolor="#dcfcd3">王允亚</td>
					<td bgcolor="#dcfcd3">2017-3-31</td>
					<td bgcolor="#dcfcd3">中文发现、读秀</td>
					<td bgcolor="#dcfcd3">世纪超星</td>
					<td bgcolor="#dcfcd3">300，000</td>
					<td bgcolor="#dcfcd3"><a href="/public/ht/contractDetail">详情</a></td>
				  </tr> -->
				  </c:forEach>
				</table>
				</div>
			</div>
			
			<!--  <tr>
					<td>$velocityCount</td>
					<td>$!{c.id}</td>
					<td>$!{c.depart}</td>
					<td>$!{c.company_property}</td>
					<td>$!{c.province}</td>
					<td>$!{c.fourthLevel}</td>
					<td>$!{c.operator}</td>
					<td>$!{c.dengjiTime}</td>
					<td>$!{c.pingming}</td>
					<td>$!{c.company}</td>
					<td>$!{c.contractMoney}</td>
				  </tr> -->
			<div class="paging">
				<!-- <span class="leftF">每页 <b>50</b> 条</span>
				<a href="#">首页</a><a href="#">上一页</a><span>第</span>
				<a href="#">1</a>
				<a class="cur" href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">5</a>
				<a href="#">6</a>
				<a href="#">7</a>
				<a href="#">8</a>
				<span>......</span><a href="#">14</a>
				<span>页</span><a href="#">下一页</a><a href="#">尾页</a> -->
			</div>
		</div>
	</div>
	</div>
	
	<!-- <script type="text/javascript" src="/app/views/hetong/js/jquery-1.7.2.min.js"></script> -->
	<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
	<script type="text/javascript" src="/app/views/hetong/js/pageUtil.js"></script>
	<script type="text/javascript">
		$(function(){
			$('select').selectlist({
				width: 130,
				height: 18
			});		
		})
		
 // 分页  
$(document).ready(function(){
	//alert($pageBean.currentPage);
	page.showPage(${pageBean.currentPage},${pageBean.totalPages},"changeMethod");
});	
	
		
function changeMethod(nextPage){
			/* location.href="/public/ht/contractList.action?page="+nextPage; */
			
			$.get('public/ht/contractListCondition.action',{page:nextPage},function(result){
				$('#container').html(result);
				})
		}

/* $(function(){
	var flag = $("#undeal").is(":checked");
	if(flag){
		alert("1111");
		$.ajax({
			type:'Post',
			url:'/public/ht/getUndealcontractList.action',
			data:formdata,
			dataType:'json',
			success:function(result){
				
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) { alert("网络异常"); } 
			
		}) 
	}
}) */	


	$(".detail").click(function(){
		var id = $(this).attr("id");
		$.get('public/ht/contractDetail.action',{id:id},function(result){
			$('#container').html(result);
			})
	})

	
$("#search").click(function(){
 	var year = $("#edu .select-button").val(); 
    var purchaseCom = $("#purchaseCom").val();//购买单位
	var danwei = $("#danwei .select-button").val();//单位性质
	var gongsi = $("#gongsi .select-button").val();//所属公司
	var chanpin = $("#chanpin .select-button").val();//产品
	var shengfen = $("#shengfen .select-button").val();//省份
	var group = $("#group").val();//小组
	var responsibility = $("#responsibility").val();//责任人
	var zhuangtai = $("#zhuangtai .select-button").val();//状态 
	var userId = $("#userId").val(); //用户ID
	/* var data = {"year":year,"purchaseCom":purchaseCom,"danwei":danwei,"gongsi":gongsi,"chanpin":chanpin,"shengfen":shengfen,"group":group,"responsibility":responsibility,
		"zhuangtai":zhuangtai,"userId":userId};  */
	var data = {"purchaseCom":purchaseCom};
	  $.post_('public/ht/contractListCondition.action',data,function(result){
			$('#container').html(result);
			}) 
	
 		/* $.ajax({
			type:'GET',
			url:'/public/ht/contractListCondition.action',
			data:data,
			dataType:'json',
			success:function(result){
				$('#container').html(result);
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) { alert("网络异常1111"); } 
			
		});  */
		/* $.get('public/ht/contractListCondition.action',formdata, function(result){
		console.log(result);})
		/* var formdata = new FormData($("#form1")[0]);
		formdata.append("year",year);
		formdata.append("purchaseCom",purchaseCom);
		formdata.append("danwei",danwei);
		formdata.append("gongsi",gongsi);
		formdata.append("chanpin",chanpin);
		formdata.append("shengfen",shengfen);
		formdata.append("group",group);
		formdata.append("responsibility",responsibility);
		formdata.append("zhuangtai",zhuangtai);
		formdata.append("userId",userId); */
	
	})	
	</script>
	
